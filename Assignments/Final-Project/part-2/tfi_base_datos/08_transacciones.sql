USE tfi_base_datos;

--====================
-- prueba de deadlock
--====================

-- sesion 1 (terminal A)
START TRANSACTION;
-- sesion 2 (terminal B)
START TRANSACTION;
-- sesion 1 (terminal A)
UPDATE Producto SET stock = stock - 1 WHERE id = 1;
-- sesion 2 (terminal B)
UPDATE Producto SET stock = stock - 2 WHERE id = 2;
-- sesion 1 (terminal A)
UPDATE Producto SET stock = stock - 1 WHERE id = 2;
-- sesion 2 (terminal B)
UPDATE Producto SET stock = stock - 3 WHERE id = 1;

--================
-- read committed
--================

-- sesion 1 (terminal A)
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
START TRANSACTION;
SELECT stock FROM Productos WHERE id_producto = 1; 

-- sesion 1 (terminal A)
START TRANSACTION;
UPDATE Productos SET stock = 10 WHERE id_producto = 1;
COMMIT;

-- sesion 1 (terminal A)
SELECT stock FROM Productos WHERE id_producto = 1; 
COMMIT;

--=================
-- repeatable read
--=================

-- sesion 1 (terminal A)
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
SELECT stock FROM Productos WHERE id_producto = 1; 

-- sesion 2 (terminal B)
START TRANSACTION;
UPDATE Productos SET stock = 99 WHERE id_producto = 1;
COMMIT;

-- sesion 1 (terminal A)
SELECT stock FROM Productos WHERE id_producto = 1; 
COMMIT;