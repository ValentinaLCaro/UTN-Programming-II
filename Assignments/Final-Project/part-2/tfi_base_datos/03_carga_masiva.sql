USE tfi_base_datos;

SET @@cte_max_recursion_depth = 250000;

INSERT INTO Pedidos (fecha, estado, total, forma_pago, id_usuario)
WITH RECURSIVE SecuenciaPedidos AS (
    SELECT 1 AS n, CAST('2026-01-01 08:00:00' AS DATETIME) AS fecha_gen
    UNION ALL
    SELECT n + 1, DATE_ADD(fecha_gen, INTERVAL 8 MINUTE)
    FROM SecuenciaPedidos
    WHERE n < 40000
)
SELECT 
    fecha_gen,
    CASE MOD(n, 4)
        WHEN 0 THEN 'TERMINADO'
        WHEN 1 THEN 'CONFIRMADO'
        WHEN 2 THEN 'PENDIENTE'
        ELSE 'CANCELADO'
    END AS estado,
    0.00 AS total,
    CASE MOD(n, 3)
        WHEN 0 THEN 'TARJETA'
        WHEN 1 THEN 'TRANSFERENCIA'
        ELSE 'EFECTIVO'
    END AS forma_pago,
    (MOD(n, 1000) + 1) AS id_usuario
FROM SecuenciaPedidos;

INSERT INTO Detalles_Pedido (cantidad, subtotal, id_pedido, id_producto)
WITH RECURSIVE SecuenciaDetalles AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM SecuenciaDetalles WHERE n < 150000
)
SELECT 
    (MOD(n, 3) + 1) AS cantidad,
    0.00 AS subtotal,
    (MOD(n - 1, 40000) + 1) AS id_pedido,
    (MOD(FLOOR((n - 1) / 40000) + MOD(n, 100), 100) + 1) AS id_producto
FROM SecuenciaDetalles;

-- Sincronización y coherencia de montos
UPDATE Detalles_Pedido dp
JOIN Productos p ON dp.id_producto = p.id_producto
SET dp.subtotal = dp.cantidad * p.precio;

UPDATE Pedidos p
SET p.total = COALESCE((
    SELECT SUM(dp.subtotal)
    FROM Detalles_Pedido dp
    WHERE dp.id_pedido = p.id_pedido
), 0);