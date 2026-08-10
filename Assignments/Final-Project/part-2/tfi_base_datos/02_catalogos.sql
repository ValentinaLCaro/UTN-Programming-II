USE tfi_base_datos;

INSERT INTO Usuarios (nombre, apellido, mail, celular, contrasena, rol) VALUES
('Valentina', 'Caro', 'valentina@mail.com', '3364123456', 'admin_123', 'ADMIN'),
('Agustin', 'Farris', 'agustin@mail.com', '3364654321', 'user_789', 'USUARIO');

INSERT INTO Usuarios (nombre, apellido, mail, celular, contrasena, rol)
WITH RECURSIVE GenUsuarios AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM GenUsuarios WHERE n < 1000
)
SELECT 
    CASE MOD(n, 4)
        WHEN 0 THEN 'Valentina'
        WHEN 1 THEN 'Agustin'
        WHEN 2 THEN 'Mariana'
        ELSE 'Carlos'
    END AS nombre,
    CASE MOD(n, 4)
        WHEN 0 THEN 'Caro'
        WHEN 1 THEN 'Farris'
        WHEN 2 THEN 'Gomez'
        ELSE 'Lopez'
    END AS apellido,
    CONCAT('usuario_', n, '@mail.com') AS mail,
    CONCAT('3364', LPAD(n, 6, '0')) AS celular,
    CONCAT('pass', n * 7) AS contrasena,
    CASE WHEN n <= 5 THEN 'ADMIN' ELSE 'USUARIO' END AS rol
FROM GenUsuarios;

-- Modificado: Categorías de Foodstore
INSERT INTO Categorias (nombre, descripcion) VALUES
('Almacén y Comestibles', 'Productos secos, aceites, harinas, pastas y conservas'),
('Bebidas y Cafetería', 'Aguas, gaseosas, jugos, cervezas, vinos y café en grano o molido'),
('Snacks y Delicatessen', 'Papas fritas, frutos secos, chocolates y productos gourmet');

-- Modificado: Productos de Foodstore
INSERT INTO Productos (nombre, precio, descripcion, stock, id_categoria)
WITH RECURSIVE GenProductos AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM GenProductos WHERE n < 100
)
SELECT 
    CASE MOD(n, 4)
        WHEN 0 THEN CONCAT('Café en Grano Premium Blend v', n)
        WHEN 1 THEN CONCAT('Aceite de Oliva Extra Virgen v', n)
        WHEN 2 THEN CONCAT('Papas Fritas Artesanales Corte Grueso v', n)
        ELSE CONCAT('Pasta Italiana Penne Rigate v', n)
    END AS nombre,
    ROUND(25000.00 + (MOD(n * 73, 19) * 12500.00), 2) AS precio,
    CONCAT('Descripción detallada de calidad e ingredientes para el producto de lote alimenticio número ', n * 2) AS descripcion,
    (MOD(n, 91) + 10) AS stock,
    (MOD(n, 3) + 1) AS id_categoria
FROM GenProductos;