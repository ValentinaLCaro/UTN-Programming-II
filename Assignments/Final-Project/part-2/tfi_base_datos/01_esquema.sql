CREATE DATABASE IF NOT EXISTS tfi_base_datos;
USE tfi_base_datos;

DROP TABLE IF EXISTS Detalles_Pedido;
DROP TABLE IF EXISTS Pedidos;
DROP TABLE IF EXISTS Productos;
DROP TABLE IF EXISTS Categorias;
DROP TABLE IF EXISTS Usuarios;

CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    celular VARCHAR(20),
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_Usuarios PRIMARY KEY (id_usuario),
    CONSTRAINT UQ_Usuarios_Mail UNIQUE (mail),
    CONSTRAINT CK_Usuarios_Rol CHECK (rol IN ('ADMIN', 'USUARIO'))
);

CREATE TABLE Categorias (
    id_categoria INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_Categorias PRIMARY KEY (id_categoria),
    CONSTRAINT UQ_Categorias_Nombre UNIQUE (nombre)
);

CREATE TABLE Productos (
    id_producto INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    descripcion VARCHAR(255),
    stock INT NOT NULL DEFAULT 0,
    imagen VARCHAR(255),
    disponible BOOLEAN DEFAULT TRUE,
    id_categoria INT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_Productos PRIMARY KEY (id_producto),
    CONSTRAINT FK_Productos_Categorias FOREIGN KEY (id_categoria) REFERENCES Categorias (id_categoria),
    CONSTRAINT CK_Productos_Precio CHECK (precio >= 0),
    CONSTRAINT CK_Productos_Stock CHECK (stock >= 0)
);

CREATE TABLE Pedidos (
    id_pedido INT AUTO_INCREMENT,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL,
    total DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    forma_pago VARCHAR(20) NOT NULL,
    id_usuario INT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_Pedidos PRIMARY KEY (id_pedido),
    CONSTRAINT FK_Pedidos_Usuarios FOREIGN KEY (id_usuario) REFERENCES Usuarios (id_usuario),
    CONSTRAINT CK_Pedidos_Estado CHECK (estado IN ('PENDIENTE', 'CONFIRMADO', 'TERMINADO', 'CANCELADO')),
    CONSTRAINT CK_Pedidos_FormaPago CHECK (forma_pago IN ('TARJETA', 'TRANSFERENCIA', 'EFECTIVO')),
    CONSTRAINT CK_Pedidos_Total CHECK (total >= 0)
);

CREATE TABLE Detalles_Pedido (
    id_detalle INT AUTO_INCREMENT,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_Detalles PRIMARY KEY (id_detalle),
    CONSTRAINT FK_Detalles_Pedidos FOREIGN KEY (id_pedido) REFERENCES Pedidos (id_pedido) ON DELETE CASCADE,
    CONSTRAINT FK_Detalles_Productos FOREIGN KEY (id_producto) REFERENCES Productos (id_producto),
    CONSTRAINT CK_Detalles_Cantidad CHECK (cantidad > 0),
    CONSTRAINT CK_Detalles_Subtotal CHECK (subtotal >= 0),
    CONSTRAINT UQ_Detalles_Pedido_Producto UNIQUE (id_pedido, id_producto)
);

