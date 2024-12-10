-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-11-05 12:41:19.071

-- tables
-- Table: ALERTAS
CREATE TABLE ALERTAS (
    id_alerta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    estado VARCHAR(15) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    fecha DATE NOT NULL,
    id_producto INT NOT NULL,
    CONSTRAINT ALERTAS_fk_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto) ON DELETE CASCADE
);

-- Table: CLIENTES
CREATE TABLE CLIENTES (
    id_cliente SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    nombre VARCHAR(25) NOT NULL,
    apellidos VARCHAR(25) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

-- Table: DETALLE_VENTAS
CREATE TABLE DETALLE_VENTAS (
    id_detalle_venta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    id_producto INT NOT NULL,
    id_venta INT NOT NULL,
    cantidad INT NOT NULL,
    CONSTRAINT DETALLE_VENTAS_fk_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto) ON DELETE CASCADE,
    CONSTRAINT DETALLE_VENTAS_fk_venta FOREIGN KEY (id_venta) REFERENCES VENTAS (id_venta) ON DELETE CASCADE
);

-- Table: INVENTARIO
CREATE TABLE INVENTARIO (
    id_inventario SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    cantidad INT NOT NULL,
    id_producto INT NOT NULL,
    id_sucursal INT NOT NULL,
    CONSTRAINT INVENTARIO_fk_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto) ON DELETE CASCADE,
    CONSTRAINT INVENTARIO_fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES SUCURSAL (id_sucursal) ON DELETE CASCADE
);

-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    id_producto SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    nombre VARCHAR(30) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    cod_barras VARCHAR(50),
    stock INT NOT NULL,
    numero_lote VARCHAR(50),
    id_proveedor INT NOT NULL,
    CONSTRAINT PRODUCTO_fk_proveedor FOREIGN KEY (id_proveedor) REFERENCES PROVEEDORES (id_proveedor) ON DELETE CASCADE
);

-- Table: PROVEEDORES
CREATE TABLE PROVEEDORES (
    id_proveedor SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    nombre VARCHAR(30) NOT NULL
);

-- Table: RECETAS
CREATE TABLE RECETAS (
    id_receta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    detalle VARCHAR(50) NOT NULL,
    CONSTRAINT RECETAS_fk_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTES (id_cliente) ON DELETE CASCADE
);

-- Table: SUCURSAL
CREATE TABLE SUCURSAL (
    id_sucursal SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    direccion VARCHAR(40) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

-- Table: USUARIO
CREATE TABLE USUARIO (
    id_usuario SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    nombre VARCHAR(20) NOT NULL,
    contraseña VARCHAR(50) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

-- Table: VENTAS
CREATE TABLE VENTAS (
    id_venta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    monto DECIMAL(8,2) NOT NULL,
    fecha_venta DATE NOT NULL,
    cantidad INT NOT NULL,
    id_sucursal INT NOT NULL,
    id_cliente INT NOT NULL,
    CONSTRAINT VENTAS_fk_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTES (id_cliente) ON DELETE CASCADE,
    CONSTRAINT VENTAS_fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES SUCURSAL (id_sucursal) ON DELETE CASCADE
);

-- foreign keys
-- Reference: ALERTAS_PRODUCTO (table: ALERTAS)
ALTER TABLE ALERTAS ADD CONSTRAINT ALERTAS_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO (id_producto)
    ON DELETE CASCADE;

-- Reference: DETALLE_VENTAS_PRODUCTO (table: DETALLE_VENTAS)
ALTER TABLE DETALLE_VENTAS ADD CONSTRAINT DETALLE_VENTAS_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO (id_producto)
    ON DELETE CASCADE;

-- Reference: DETALLE_VENTAS_VENTAS (table: DETALLE_VENTAS)
ALTER TABLE DETALLE_VENTAS ADD CONSTRAINT DETALLE_VENTAS_VENTAS
    FOREIGN KEY (id_venta)
    REFERENCES VENTAS (id_venta)
    ON DELETE CASCADE;

-- Reference: INVENTARIO_SUCURSAL (table: INVENTARIO)
ALTER TABLE INVENTARIO ADD CONSTRAINT INVENTARIO_SUCURSAL
    FOREIGN KEY (id_sucursal)
    REFERENCES SUCURSAL (id_sucursal)
    ON DELETE CASCADE;

-- Reference: Inventario_PRODUCTO (table: INVENTARIO)
ALTER TABLE INVENTARIO ADD CONSTRAINT Inventario_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO (id_producto)
    ON DELETE CASCADE;

-- Reference: PRODUCTO_PROVEEDORES (table: PRODUCTO)
ALTER TABLE PRODUCTO ADD CONSTRAINT PRODUCTO_PROVEEDORES
    FOREIGN KEY (id_proveedor)
    REFERENCES PROVEEDORES (id_proveedor)
    ON DELETE CASCADE;

-- Reference: RECETAS_CLIENTES (table: RECETAS)
ALTER TABLE RECETAS ADD CONSTRAINT RECETAS_CLIENTES
    FOREIGN KEY (id_cliente)
    REFERENCES CLIENTES (id_cliente)
    ON DELETE CASCADE;

-- Reference: VENTAS_CLIENTES (table: VENTAS)
ALTER TABLE VENTAS ADD CONSTRAINT VENTAS_CLIENTES
    FOREIGN KEY (id_cliente)
    REFERENCES CLIENTES (id_cliente)
    ON DELETE CASCADE;

-- Reference: VENTAS_SUCURSAL (table: VENTAS)
ALTER TABLE VENTAS ADD CONSTRAINT VENTAS_SUCURSAL
    FOREIGN KEY (id_sucursal)
    REFERENCES SUCURSAL (id_sucursal)
    ON DELETE CASCADE;
