USE tfi_base_datos;

CREATE OR REPLACE VIEW v_resumen_operativo_pedidos AS
SELECT
    p.id_pedido AS id_pedido,
    p.fecha AS fecha_pedido,
    p.estado AS estado_pedido,
    p.forma_pago AS forma_pago,
    p.total AS monto_total,
    CONCAT(u.nombre, ' ', u.apellido) AS cliente_nombre,
    u.mail AS cliente_contacto
FROM Pedidos p
INNER JOIN Usuarios u ON p.id_usuario = u.id_usuario
WHERE p.eliminado = FALSE; -- garantiza consistencia ocultando soft-deletes