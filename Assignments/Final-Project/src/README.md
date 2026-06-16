# TRABAJO PRÁCTICO INTEGRADOR - PROGRAMACIÓN 2

# FOOD STORE - SISTEMA DE GESTIÓN DE PEDIDOS DE COMIDA

- **ESTUDIANTE:** Valentina Lucia Caro
- **INSTITUCIÓN:** Universidad Tecnológica Nacional (UTN)
- **CARRERA:** Tecnicatura Universitaria en Programación
- **MODALIDAD:** Aplicación de consola basada en capas con persistencia en memoria

---

## 1. DESCRIPCIÓN DEL PROYECTO

Food Store es una aplicación de backend desarrollada en Java orientada a la gestión integral de productos, categorías, usuarios y pedidos de un negocio gastronómico.

El sistema implementa operaciones CRUD completas ejecutadas directamente desde menús interactivos por consola, aplicando los pilares de la Programación Orientada a Objetos (POO), manejo de interfaces, jerarquías de excepciones propias y estructuras de datos dinámicas (Colecciones) para la manipulación de datos en memoria en tiempo de ejecución.

---

## 2. ENLACES OBLIGATORIOS (ENTREGABLES)

### Video demostrativo (Permisos Públicos)

🔗 https://youtu.be/tu-enlace-al-video-aqui

> El video recorre el flujo CRUD completo, las reglas de negocio y explica el diseño de la arquitectura del software.

### Documentación académica (Informe PDF)

🔗 https://github.com/tu-usuario/tu-repositorio/blob/main/Informe_TPI_Caro.pdf

> El archivo PDF detallado también se encuentra adjunto de forma directa en el directorio raíz de este repositorio.

---

## 3. REQUISITOS DEL SISTEMA

Para compilar y ejecutar este proyecto correctamente desde la primera oportunidad, asegúrese de contar con el siguiente entorno configurado:

* **Kit de Desarrollo de Java:** Java JDK 21 (o versión superior instalada).
* **Entorno de Desarrollo Integrado (IDE):** Apache NetBeans, IntelliJ IDEA o Eclipse.
* **Herramienta de construcción:** Soporte nativo para proyectos Java Standard Edition (Java SE).
* **Gestor de Consola:** Terminal estándar del sistema o consola integrada del IDE (con soporte para entrada y salida mediante la clase `Scanner`).

---

## 4. INSTRUCCIONES DE EJECUCIÓN

Siga estos pasos para levantar el entorno localmente.

### Método A: Desde un Entorno de Desarrollo (IDE)

1. Descargue el archivo comprimido `.zip` de la entrega o clone el repositorio de GitHub.
2. Abra su IDE de preferencia (ej.: Apache NetBeans) y seleccione **Open Project**.
3. Navegue hasta la carpeta raíz `Caro-Valentina_TPI` y selecciónela.
4. El IDE reconocerá automáticamente la estructura de paquetes del proyecto.
5. Haga clic derecho sobre el nodo principal del proyecto y seleccione **Clean and Build** para compilar las clases.
6. Ejecute la aplicación seleccionando **Run** o abriendo directamente la clase `Menu.java` ubicada en `Main/` y ejecutando su método `main`.

### Método B: Desde la Consola de Comandos (Terminal)

1. Abra una terminal en el directorio raíz del proyecto (donde se encuentra la carpeta `/src`).
2. Compile todos los archivos ejecutando:

```bash
javac Main/Menu.java entities/*.java services/*.java exception/*.java enums/*.java interfaces/*.java utilities/*.java
```

3. Inicie la aplicación ejecutando:

```bash
java Main.Menu
```

---

## 5. ARQUITECTURA DEL CÓDIGO

El código fuente está modularizado por paquetes para asegurar una correcta separación de responsabilidades:

```text
src/
│
├── Main/         → Clase Menu.java (Gestión de buffers y flujos de consola mediante Scanner)
├── entities/     → Clases del dominio UML que extienden de la clase abstracta Base (Herencia)
├── services/     → Controladores de lógica y administración de colecciones ArrayList en memoria
├── exception/    → Jerarquía de excepciones controladas de negocio heredadas de NegocioException
├── enums/        → Constantes seguras (formas de pago, roles y estados)
├── interfaces/   → Contratos de comportamiento del dominio (Interfaz Calculable)
└── utilities/    → Componente Validador para tipado e inputs numéricos/genéricos
```

---

## Tecnologías utilizadas

* Java JDK 21
* Programación Orientada a Objetos (POO)
* Colecciones (`ArrayList`)
* Manejo de excepciones personalizadas
* Arquitectura por capas
* Menús interactivos por consola

---
