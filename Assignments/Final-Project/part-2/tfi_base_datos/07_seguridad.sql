USE tfi_base_datos;

-- creacion de usuario con privilegios acotados
DROP USER IF EXISTS 'operador_foodstore'@'localhost';
CREATE USER 'operador_foodstore'@'localhost' IDENTIFIED BY 'FoodStore2026!Secure';

-- vista 1: oculta la contraseña y datos eliminados
CREATE OR REPLACE VIEW v_usuarios_seguros AS
SELECT      
    id_usuario, 
    nombre, 
    apellido, 
    mail, 
    celular, 
    rol, 
    created_at
FROM Usuarios
WHERE eliminado = FALSE; 

-- vista 2: oculta registros financieros de pedidos cancelados o eliminados
CREATE OR REPLACE VIEW v_pedidos_activos_operacion AS
SELECT 
    id_pedido, 
    fecha, 
    estado, 
    forma_pago, 
    total, 
    id_usuario, 
    created_at
FROM Pedidos
WHERE eliminado = FALSE AND estado != 'CANCELADO';

-- asignacion de privilegios minimos sobre tablas y vistas
GRANT SELECT ON tfi_base_datos.v_usuarios_seguros TO 'operador_foodstore'@'localhost';
GRANT SELECT ON tfi_base_datos.v_pedidos_activos_operacion TO 'operador_foodstore'@'localhost';

GRANT SELECT, INSERT, UPDATE ON tfi_base_datos.Categorias TO 'operador_foodstore'@'localhost';
GRANT SELECT, INSERT, UPDATE ON tfi_base_datos.Productos TO 'operador_foodstore'@'localhost';
GRANT SELECT, INSERT, UPDATE ON tfi_base_datos.Detalles_Pedido TO 'operador_foodstore'@'localhost';

GRANT INSERT, UPDATE ON tfi_base_datos.Pedidos TO 'operador_foodstore'@'localhost';

-- consulta segura mediante Procedimiento Almacenado 
DELIMITER //

DROP PROCEDURE IF EXISTS sp_buscar_usuario_por_email//
CREATE PROCEDURE sp_buscar_usuario_por_email(IN p_email VARCHAR(100))
BEGIN
    SELECT id_usuario, nombre, apellido, mail, celular, rol 
    FROM v_usuarios_seguros 
    WHERE mail = p_email;
END //

DELIMITER ;

-- asignacion de permiso de ejecucion 
GRANT EXECUTE ON PROCEDURE tfi_base_datos.sp_buscar_usuario_por_email TO 'operador_foodstore'@'localhost';

FLUSH PRIVILEGES;