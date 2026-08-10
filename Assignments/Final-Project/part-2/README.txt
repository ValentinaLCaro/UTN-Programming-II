========================================================================
 TRABAJO FINAL INTEGRADOR (TFI) - BASES DE DATOS I
 PROYECTO: Food Store - Gestión de Datos
========================================================================

ESTUDIANTE: Valentina Lucia Caro
LEGAJO: 18314
INSTITUCIÓN: Universidad Tecnológica Nacional (UTN)
CARRERA: Tecnicatura Universitaria en Programación a Distancia
FECHA DE ENTREGA: Junio de 2026
VERSION: 1.0.0 

------------------------------------------------------------------------
 DESCRIPCIÓN GENERAL
------------------------------------------------------------------------
Este repositorio contiene el ecosistema completo de scripts SQL para el
diseño, poblamiento masivo, optimización, seguridad y control transaccional
del sistema "Food Store". 

Todos los scripts son completamente IDEMPOTENTES, implementando cláusulas
de control preventivo ("DROP IF EXISTS") para permitir una ejecución limpia
y repetible desde cero en cualquier entorno MySQL compatible.

------------------------------------------------------------------------
 ORDEN ESTRICTO DE EJECUCIÓN SÚGURIDO
------------------------------------------------------------------------
Para garantizar la integridad referencial y evitar conflictos de claves 
foráneas o dependencias lógicas, los archivos deben ejecutarse en el 
siguiente orden secuencial:

01_esquema.sql
   -> Creación física del esquema de la base de datos "tfi_base_datos".
   -> Definición de tablas base con sus correspondientes restricciones
      de integridad estructural: PRIMARY KEY, FOREIGN KEY (con políticas 
      ON DELETE CASCADE donde aplica), UNIQUE y CHECK de rangos de negocio.

02_catalogos.sql
   -> Inserción de los registros maestros iniciales y catálogos base.
   -> Generación automatizada de 1.000 usuarios semilla y 100 productos 
      iniciales mediante Expresiones de Tabla Comunes (CTE) recursivas.

03_carga_masiva.sql
   -> Poblamiento masivo del sistema a escala realista (~191.000 filas).
   -> Generación de 40.000 pedidos y 150.000 detalles mediante bloques 
      WITH RECURSIVE de alta profundidad (cte_max_recursion_depth).
   -> Aplicación del operador matemático MOD para la asignación de FKs
      y sincronización final de montos financieros.

04_indices.sql
   -> Creación de índices optimizadores estructurados (árboles B+) sobre 
      columnas estratégicas para acelerar uniones de tablas (JOIN), 
      búsquedas de rangos y filtros de igualdad.

05_consultas.sql
   -> Desarrollo de la suite de reportes avanzados requeridos por el 
      negocio, implementando INNER JOINS estructurados, agregaciones 
      complejas (GROUP BY + HAVING) y subconsultas analíticas.

05_explain.sql
   -> Scripts de auditoría de rendimiento que aplican EXPLAIN ANALYZE 
      sobre las consultas previas para analizar el mapa de ruta del 
      optimizador y evaluar el costo de ejecución en el servidor.

06_vistas.sql
   -> Creación de la vista operativa de negocio encargada de simplificar 
      el acceso a los datos consolidados y aislar los registros que 
      fueron eliminados de forma lógica (Soft Delete).

07_seguridad.sql
   -> Implementación del Principio de Mínimos Privilegios: creación del 
      usuario 'operador_foodstore'@'localhost' con accesos acotados.
   -> Diseño de vistas de ocultamiento de datos sensibles (contraseñas).
   -> Creación de procedimiento almacenado parametrizado seguro para 
      búsquedas, neutralizando vulnerabilidades de inyección SQL.

08_transacciones.sql
   -> Guion interactivo diseñado para simular de forma cruzada escenarios 
      de alta contención en disco, generación manual de Deadlocks y 
      demostración práctica de los niveles de aislamiento del motor 
      (READ COMMITTED y REPEATABLE READ).

09_concurrencia_guiada.sql
   -> Implementación del procedimiento avanzado 'sp_registrar_pedido_seguro'.
   -> Cuenta con control transaccional completo (START TRANSACTION/COMMIT/ROLLBACK), 
      manejadores de excepciones genéricas (SQLEXCEPTION) y lógica 
      robusta de reintentos con pausa estratégica (Backoff de error 1213).

pruebas.sql
   -> Suite complementaria de pruebas globales. Contiene las inserciones 
      válidas/erróneas de constraints, controles de orfandad, corridas 
      de contraste de tiempos (con/sin índice) y validación de errores 
      1142 por accesos denegados del operador.
