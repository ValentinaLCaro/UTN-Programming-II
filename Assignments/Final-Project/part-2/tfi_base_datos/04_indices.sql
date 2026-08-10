USE tfi_base_datos;

-- indice para optimizar el JOIN entre Producto y Categoria
CREATE INDEX idx_producto_categoria ON Productos (id_categoria);

-- indice compuesto para optimizar filtros por fecha y ordenamiento
CREATE INDEX idx_pedido_fecha_total ON Pedidos (fecha, total);

-- indice en columna de rango para busquedas de precios
CREATE INDEX idx_producto_precio ON Productos (precio);