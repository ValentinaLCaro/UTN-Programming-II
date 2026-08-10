USE tfi_base_datos;

EXPLAIN ANALYZE
SELECT p.id_producto, p.nombre, c.nombre
FROM Productos p 
INNER JOIN Categorias c ON p.id_categoria = c.id_categoria
WHERE p.eliminado = FALSE;

EXPLAIN ANALYZE
SELECT ped.id_pedido, u.nombre, prod.nombre
FROM Pedidos ped
INNER JOIN Usuarios u ON ped.id_usuario = u.id_usuario
INNER JOIN Detalles_Pedido dp ON dp.id_pedido = ped.id_pedido
INNER JOIN Productos prod ON dp.id_producto = prod.id_producto
WHERE ped.fecha BETWEEN '2026-01-01' AND '2026-06-30';

EXPLAIN ANALYZE
SELECT c.nombre, SUM(dp.subtotal)
FROM Categorias c
INNER JOIN Productos p ON p.id_categoria = c.id_categoria
INNER JOIN Detalles_Pedido dp ON dp.id_producto = p.id_producto
GROUP BY c.id_categoria, c.nombre
HAVING SUM(dp.subtotal) > 50000.00;

EXPLAIN ANALYZE
SELECT id_producto, nombre, precio FROM Productos
WHERE precio > (SELECT AVG(precio) FROM Productos);