# Food Store Data Management - Final Project (Part 2)

## Overview
This repository contains the database component (Part 2) of the Integrated Final Project for the Database I course at Universidad Tecnológica Nacional (UTN). It includes the complete ecosystem of SQL scripts designed for the database architecture, bulk data population, query optimization, security implementation, and concurrency control of the "Food Store" system.

To comply with course requirements, the project is split into two parts: Part 1 handles the application layer and backend logic, while Part 2 focuses entirely on the relational database foundation and script execution.

All SQL scripts are fully idempotent and implement preventive drop clauses (DROP IF EXISTS) to allow a clean and repeatable execution from scratch in any compatible MySQL environment.

## Student Information

- **Student:** Valentina Lucia Caro
- **Institution:** Universidad Tecnológica Nacional (UTN)
- **Program:** Associate Degree in Programming (Distance Learning)
- **Course:** Database I - Final Integrated Project
- **Date:** June 2026
- **Version:** 1.0.0

## Strict Execution Order

To maintain referential integrity and prevent foreign key conflicts or logical dependency errors, the scripts must be executed in the following sequential order:

- *01_esquema.sql*
Physical creation of the "tfi_base_datos" database schema. Defines base tables along with structural integrity constraints: Primary Keys, Foreign Keys (including ON DELETE CASCADE policies where applicable), Unique constraints, and domain Check constraints.

- *02_catalogos.sql*
Populates initial master lookup tables and base catalogs. Uses recursive Common Table Expressions (CTEs) to automatically generate 1,000 seed users and 100 initial products.

- *03_carga_masiva.sql*
Executes realistic bulk data insertion (~191,000 total rows). Generates 40,000 orders and 150,000 order details using deep WITH RECURSIVE blocks (cte_max_recursion_depth). Applies modulo operators for foreign key distribution and synchronizes total financial amounts.

- *04_indices.sql*
Creates B-Tree indexes on strategic columns to optimize table joins, range searches, and equality filters.

- *05_consultas.sql*
Contains the suite of advanced business reporting queries required by the project specifications, using structured INNER JOINs, complex aggregations (GROUP BY with HAVING), and analytical subqueries.

- *05_explain.sql*
Performance audit scripts applying EXPLAIN ANALYZE to the reporting queries to evaluate execution paths, query costs, and server performance.

- *06_vistas.sql*
Creates operational business views designed to simplify access to consolidated data and filter out logically deleted records (Soft Delete).

- *07_seguridad.sql*
Implements the Principle of Least Privilege by creating a restricted user ('operador_foodstore'@'localhost'). Includes views that mask sensitive data (passwords) and a secure parameterized stored procedure for search queries to prevent SQL Injection vulnerabilities.

- *08_transacciones.sql*
An interactive script designed to simulate high-concurrency disk contention, manual deadlock generation, and a practical demonstration of MySQL isolation levels (READ COMMITTED and REPEATABLE READ).

- *09_concurrencia_guiada.sql*
Defines the advanced stored procedure 'sp_registrar_pedido_seguro'. Includes full transaction control (START TRANSACTION, COMMIT, ROLLBACK), SQLEXCEPTION handlers, and a retry strategy with backoff logic for deadlock handling (Error 1213).

- *pruebas.sql*S
A comprehensive testing suite. Contains valid and invalid constraint insertion tests, orphan record checks, query performance comparisons (with and without indexes), and validation of permission-denied errors (Error 1142) under restricted user access.