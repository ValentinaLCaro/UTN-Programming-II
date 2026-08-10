USE tfi_base_datos;

DELIMITER //

DROP PROCEDURE IF EXISTS sp_registrar_pedido_seguro//

CREATE PROCEDURE sp_registrar_pedido_seguro(
    IN p_usuario_id INT,
    IN p_producto_id INT,
    IN p_cantidad INT,
    OUT p_resultado_msg VARCHAR(255)
)
BEGIN
    DECLARE v_intento INT DEFAULT 1;
    DECLARE v_max_intentos INT DEFAULT 3;
    DECLARE v_exito BOOLEAN DEFAULT FALSE;
    DECLARE v_deadlock_detectado BOOLEAN;
    
    -- variables auxiliares de negocio
    DECLARE v_dummy_sleep INT;
    DECLARE v_precio_producto DECIMAL(10, 2);
    DECLARE v_subtotal DECIMAL(10, 2);
    DECLARE v_nuevo_pedido_id INT;

    bucle_retry: WHILE v_intento <= v_max_intentos AND NOT v_exito DO
        SET v_deadlock_detectado = FALSE;
        
        BEGIN
            -- captura del error de Deadlock de MySQL 
            DECLARE EXIT HANDLER FOR 1213
            BEGIN
                SET v_deadlock_detectado = TRUE;
                ROLLBACK; 
            END;

            -- captura generica para cualquier otra anomalia de base de datos
            DECLARE EXIT HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK;
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Transacción abortada: Error de integridad, stock insuficiente o restricción de negocio.';
            END;

            START TRANSACTION;

            -- obtener el precio del producto y verificar su existencia
            SELECT precio INTO v_precio_producto 
            FROM Productos 
            WHERE id_producto = p_producto_id AND eliminado = FALSE;

            IF v_precio_producto IS NULL THEN
                SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El producto solicitado no existe o está de baja.';
            END IF;

            -- descontar stock (Lanza error si el CHECK stock >= 0 falla)
            UPDATE Productos 
            SET stock = stock - p_cantidad 
            WHERE id_producto = p_producto_id;

            -- insertar la cabecera del Pedido 
            INSERT INTO Pedidos (fecha, estado, total, forma_pago, id_usuario)
            VALUES (NOW(), 'PENDIENTE', 0.00, 'EFECTIVO', p_usuario_id);
            
            -- recuperar el ID autogenerado del pedido
            SET v_nuevo_pedido_id = LAST_INSERT_ID();

            -- calcular el subtotal e insertar el detalle del pedido
            SET v_subtotal = p_cantidad * v_precio_producto;

            INSERT INTO Detalles_Pedido (cantidad, subtotal, id_pedido, id_producto)
            VALUES (p_cantidad, v_subtotal, v_nuevo_pedido_id, p_producto_id);

            -- sincronizar el monto total en la cabecera del pedido
            UPDATE Pedidos 
            SET total = v_subtotal 
            WHERE id_pedido = v_nuevo_pedido_id;

            COMMIT;
            SET v_exito = TRUE;
            SET p_resultado_msg = CONCAT('Éxito: Pedido #', v_nuevo_pedido_id, ' procesado correctamente en el intento número ', v_intento);
        END;

        -- evaluacion del estado de concurrencia (Fuera del bloque atomico)
        IF v_deadlock_detectado THEN
            IF v_intento < v_max_intentos THEN
                SET v_dummy_sleep = SLEEP(0.2);
                SET v_intento = v_intento + 1;
            ELSE
                SIGNAL SQLSTATE '40001' SET MESSAGE_TEXT = 'Error Crítico: Límite de reintentos agotado por alta contención de Deadlocks.';
            END IF;
        END IF;

    END WHILE;
END //

DELIMITER ;