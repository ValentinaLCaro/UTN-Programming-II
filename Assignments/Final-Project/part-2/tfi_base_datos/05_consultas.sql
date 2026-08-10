USE tfi_base_datos;

-- JOIN - busqueda por igualdad
-- permite al operador visualizar el catalogo completo de productos activos 
-- junto al nombre de su categoria correspondiente, facilitando el control de stock 
-- y la auditoria visual sin mostrar elementos eliminados.
SELECT p.id_producto, p.nombre AS producto, p.precio, p.stock, c.nombre AS categoria
FROM Productos p
INNER JOIN Categorias c ON p.id_categoria = c.id_categoria
WHERE p.eliminado = FALSE AND c.eliminado = FALSE;

-- JOIN - buusqueda por rango temporal
-- reporte detallado de auditoria que cruza pedidos, clientes y productos. 
-- es importante ya que permite analizar que productos especificos 
-- compro cada usuario dentro de un rango de fechas de alta demanda.
SELECT ped.id_pedido, u.nombre, u.apellido, prod.nombre AS producto, dp.cantidad, dp.subtotal
FROM Pedidos ped
INNER JOIN Usuarios u ON ped.id_usuario = u.id_usuario
INNER JOIN Detalles_Pedido dp ON dp.id_pedido = ped.id_pedido
INNER JOIN Productos prod ON dp.id_producto = prod.id_producto
WHERE ped.fecha BETWEEN '2026-01-01' AND '2026-06-30'
  AND ped.eliminado = FALSE;

-- GROUP BY + HAVING - agregacion de Ventas
-- reporte de rendimiento comercial. agrupa los ingresos totales 
-- facturados por cada categoria, filtrando y mostrando unicamente aquellas 
-- categorias consideradas criticas o de alto rendimiento que superaron los $50.000.
SELECT c.nombre AS categoria, COUNT(dp.id_detalle) AS total_items_vendidos, SUM(dp.subtotal) AS recaudacion_total
FROM Categorias c
INNER JOIN Productos p ON p.id_categoria = c.id_categoria
INNER JOIN Detalles_Pedido dp ON dp.id_producto = p.id_producto
INNER JOIN Pedidos ped ON dp.id_pedido = ped.id_pedido
WHERE ped.estado = 'TERMINADO' AND ped.eliminado = FALSE
GROUP BY c.id_categoria, c.nombre
HAVING SUM(dp.subtotal) > 50000.00;

-- SUBCONSULTA - analisis de precios
-- identifica cuales productos del menu se encuentran por encima
-- del precio promedio general del negocio, lo cual 
-- ayuda a evaluar estrategias de descuento o promociones.
SELECT id_producto, nombre, precio, stock
FROM Productos
WHERE precio > (SELECT AVG(precio) FROM Productos WHERE eliminado = FALSE)
  AND eliminado = FALSE;