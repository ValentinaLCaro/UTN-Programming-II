USE tfi_base_datos;

--==========
-- etapa 1
--==========
-- insercion correcta 1: usuario valido
INSERT INTO Usuarios (nombre, apellido, mail, contrasena, rol) 
VALUES ('Valentina', 'Caro', 'valentina@mail.com', 'clave123', 'USUARIO');

-- insercion correcta 2: pedido asociado a ese usuario (id_usuario = 1)
INSERT INTO Pedidos (fecha, estado, total, forma_pago, id_usuario) 
VALUES ('2026-06-18', 'PENDIENTE', 15500.50, 'TARJETA', 1);

-- insercion incorrecta 1: usuario con mail existente
INSERT INTO Usuarios (nombre, apellido, mail, contrasena, rol) 
VALUES ('Agustin', 'Caro', 'valentina@mail.com', 'otraClave', 'USUARIO');

--insercion incorrecta 2: total negativo
INSERT INTO Pedidos (fecha, estado, total, forma_pago, id_usuario) 
VALUES ('2026-06-18', 'PENDIENTE', -500.00, 'EFECTIVO', 1);

--==========
-- etapa 2
--==========
-- control de volumen total
SELECT 'Usuarios' AS Tabla, COUNT(*) AS Total_Registros FROM Usuarios
UNION ALL
SELECT 'Categorías', COUNT(*) FROM Categorias
UNION ALL
SELECT 'Productos', COUNT(*) FROM Productos
UNION ALL
SELECT 'Pedidos', COUNT(*) FROM Pedidos
UNION ALL
SELECT 'Detalles de Pedido', COUNT(*) FROM Detalles_Pedido;

-- auditoria de integridad referencial
-- Verificar que TODOS los productos pertenezcan a una categoría válida
SELECT COUNT(*) AS Productos_Huerfanos 
FROM Productos 
WHERE id_categoria NOT IN (SELECT id_categoria FROM Categorias);

-- verifica que todos los pedidos pertenezcan a un usuario existente
SELECT COUNT(*) AS Pedidos_Huerfanos 
FROM Pedidos 
WHERE id_usuario NOT IN (SELECT id_usuario FROM Usuarios);

-- verifica que todos los detalles apunten a pedidos y productos reales
SELECT COUNT(*) AS Detalles_Huerfanos 
FROM DetallesPedido 
WHERE id_pedido NOT IN (SELECT id_pedido FROM Pedidos)
   OR id_producto NOT IN (SELECT id_producto FROM Productos);

--verificacion de logica de negocio
SELECT COUNT(*) AS Pedidos_Desincronizados
FROM Pedidos p
JOIN (
    SELECT id_pedido, SUM(subtotal) AS suma_detalles
    FROM Detalles_Pedido
    GROUP BY id_pedido
) dp ON p.id_pedido = dp.id_pedido
WHERE ABS(p.total - dp.suma_detalles) > 0.01;

--==========
-- etapa 3
--==========
-- medicion con indices
-- rendimiento JOIN 
SELECT p.id_producto, p.nombre, c.nombre 
FROM Productos p
INNER JOIN Categorias c ON p.id_categoria = c.id_categoria 
WHERE p.eliminado = FALSE;

-- rendimiento rango 
SELECT ped.id_pedido, u.nombre, prod.nombre 
FROM Pedidos ped
INNER JOIN Usuarios u ON ped.id_usuario = u.id_usuario
INNER JOIN Detalles_Pedido dp ON dp.id_pedido = ped.id_pedido 
INNER JOIN Productos prod ON dp.id_producto = prod.id_producto
WHERE ped.fecha BETWEEN '2026-01-01 00:00:00' AND '2026-01-03 23:59:59' 
  AND ped.eliminado = FALSE;

-- rendimiento igualdad
SELECT id_producto, nombre, precio, stock 
FROM Productos 
WHERE id_categoria = 2 AND eliminado = FALSE;

-- medicion sin indices
-- rendimiento JOIN 
SELECT p.id_producto, p.nombre, c.nombre 
FROM Productos p IGNORE INDEX (idx_producto_categoria)
INNER JOIN Categorias c ON p.id_categoria = c.id_categoria 
WHERE p.eliminado = FALSE;

-- rendimiento rango 
SELECT ped.id_pedido, u.nombre, prod.nombre 
FROM Pedidos ped IGNORE INDEX (idx_pedido_fecha_total) 
INNER JOIN Usuarios u ON ped.id_usuario = u.id_usuario 
INNER JOIN Detalles_Pedido dp ON dp.id_pedido = ped.id_pedido 
INNER JOIN Productos prod ON dp.id_producto = prod.id_producto 
WHERE ped.fecha BETWEEN '2026-01-01 00:00:00' AND '2026-01-03 23:59:59' 
  AND ped.eliminado = FALSE; 

-- rendimiento igualdad
SELECT id_producto, nombre, precio, stock 
FROM Productos IGNORE INDEX (idx_producto_categoria)
WHERE id_categoria = 2 AND eliminado = FALSE;

-- validacion de vistas
SELECT * FROM v_resumen_operativo_pedidos WHERE estado_pedido = 'PENDIENTE';

--========
--etapa 4
--========

-- violacion de unicidad 
INSERT INTO Usuarios (nombre, apellido, mail, celular, contrasena, rol)
VALUES ('Valentina', 'Caro', 'valentina@mail.com', '3364123456', 'clave123', 'USUARIO');

-- violacion de rango CHECK 
INSERT INTO Productos (nombre, precio, descripcion, stock, id_categoria)
VALUES ('Teclado de Juguete Rompible', -150.00, 'Precio inválido', 10, 1);

--pruebas con el usuario operador_foodstore

-- acceso permitido
-- el operador tiene permisos SELECT sobre las vistas seguras
SELECT * FROM tfi_base_datos.v_usuarios_seguros LIMIT 5;

-- el operador tiene permisos INSERT/UPDATE sobre Categorías
INSERT INTO tfi_base_datos.Categorias (nombre, descripcion) 
VALUES ('Audio Profesional', 'Sistemas de sonido de alta fidelidad');

-- acceso restringido
-- intento malicioso de leer las contraseñas directamente de la tabla base 'Usuarios'
SELECT id_usuario, mail, contrasena FROM tfi_base_datos.Usuarios;

-- intento de borrar fisicamente un producto 
DELETE FROM tfi_base_datos.Productos WHERE id_producto = 1;