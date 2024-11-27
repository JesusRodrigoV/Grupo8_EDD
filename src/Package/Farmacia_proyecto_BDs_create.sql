-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-11-05 12:41:19.071

-- tables
-- Table: ALERTAS
CREATE TABLE ALERTAS (
    id_alerta int  NOT NULL,
    estado varchar(15)  NOT NULL,
    tipo varchar(20)  NOT NULL,
    fecha date  NOT NULL,
    id_producto int  NOT NULL,
    CONSTRAINT ALERTAS_pk PRIMARY KEY (id_alerta)
);

-- Table: CLIENTES
CREATE TABLE CLIENTES (
    id_cliente int  NOT NULL,
    nombre varchar(25)  NOT NULL,
    apellidos varchar(25)  NOT NULL,
    telefono varchar(15)  NOT NULL,
    CONSTRAINT CLIENTES_pk PRIMARY KEY (id_cliente)
);

-- Table: DETALLE_VENTAS
CREATE TABLE DETALLE_VENTAS (
    id_detalle_venta int  NOT NULL,
    id_producto int  NOT NULL,
    id_venta int  NOT NULL,
    cantidad int  NOT NULL,
    CONSTRAINT DETALLE_VENTAS_pk PRIMARY KEY (id_detalle_venta)
);

-- Table: INVENTARIO
CREATE TABLE INVENTARIO (
    id_inventario int  NOT NULL,
    cantidad int  NOT NULL,
    id_producto int  NOT NULL,
    id_sucursal int  NOT NULL,
    CONSTRAINT INVENTARIO_pk PRIMARY KEY (id_inventario)
);

-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    id_producto int  NOT NULL,
    nombre varchar(30)  NOT NULL,
    precio decimal(10,2)  NOT NULL,
    descipcion varchar(50)  NOT NULL,
    fecha_produccion date  NOT NULL,
    fecha_vencimiento date  NOT NULL,
    id_proveedor int  NOT NULL,
    CONSTRAINT PRODUCTO_pk PRIMARY KEY (id_producto)
);

-- Table: PROVEEDORES
CREATE TABLE PROVEEDORES (
    id_proveedor int  NOT NULL,
    nombre varchar(30)  NOT NULL,
    CONSTRAINT PROVEEDORES_pk PRIMARY KEY (id_proveedor)
);

-- Table: RECETAS
CREATE TABLE RECETAS (
    id_receta int  NOT NULL,
    id_cliente int  NOT NULL,
    fecha date  NOT NULL,
    detalle varchar(50)  NOT NULL,
    CONSTRAINT RECETAS_pk PRIMARY KEY (id_receta)
);

-- Table: SUCURSAL
CREATE TABLE SUCURSAL (
    id_sucursal int  NOT NULL,
    direccion varchar(40)  NOT NULL,
    telefono varchar(15)  NOT NULL,
    CONSTRAINT SUCURSAL_pk PRIMARY KEY (id_sucursal)
);

-- Table: USUARIO
CREATE TABLE USUARIO (
    id_usuario int  NOT NULL,
    nombre varchar(20)  NOT NULL,
    contraseña varchar(50)  NOT NULL,
    rol varchar(30)  NOT NULL,
    CONSTRAINT USUARIO_pk PRIMARY KEY (id_usuario)
);

-- Table: VENTAS
CREATE TABLE VENTAS (
    id_venta int  NOT NULL,
    monto decimal(8,2)  NOT NULL,
    fecha_venta date  NOT NULL,
    cantidad int  NOT NULL,
    id_sucursal int  NOT NULL,
    id_cliente int  NOT NULL,
    CONSTRAINT VENTAS_pk PRIMARY KEY (id_venta)
);

-- foreign keys
-- Reference: ALERTAS_PRODUCTO (table: ALERTAS)
ALTER TABLE ALERTAS ADD CONSTRAINT ALERTAS_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO (id_producto)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: DETALLE_VENTAS_PRODUCTO (table: DETALLE_VENTAS)
ALTER TABLE DETALLE_VENTAS ADD CONSTRAINT DETALLE_VENTAS_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO (id_producto)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: DETALLE_VENTAS_VENTAS (table: DETALLE_VENTAS)
ALTER TABLE DETALLE_VENTAS ADD CONSTRAINT DETALLE_VENTAS_VENTAS
    FOREIGN KEY (id_venta)
    REFERENCES VENTAS (id_venta)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: INVENTARIO_SUCURSAL (table: INVENTARIO)
ALTER TABLE INVENTARIO ADD CONSTRAINT INVENTARIO_SUCURSAL
    FOREIGN KEY (id_sucursal)
    REFERENCES SUCURSAL (id_sucursal)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Inventario_PRODUCTO (table: INVENTARIO)
ALTER TABLE INVENTARIO ADD CONSTRAINT Inventario_PRODUCTO
    FOREIGN KEY (id_producto)
    REFERENCES PRODUCTO (id_producto)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: PRODUCTO_PROVEEDORES (table: PRODUCTO)
ALTER TABLE PRODUCTO ADD CONSTRAINT PRODUCTO_PROVEEDORES
    FOREIGN KEY (id_proveedor)
    REFERENCES PROVEEDORES (id_proveedor)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: RECETAS_CLIENTES (table: RECETAS)
ALTER TABLE RECETAS ADD CONSTRAINT RECETAS_CLIENTES
    FOREIGN KEY (id_cliente)
    REFERENCES CLIENTES (id_cliente)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: VENTAS_CLIENTES (table: VENTAS)
ALTER TABLE VENTAS ADD CONSTRAINT VENTAS_CLIENTES
    FOREIGN KEY (id_cliente)
    REFERENCES CLIENTES (id_cliente)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: VENTAS_SUCURSAL (table: VENTAS)
ALTER TABLE VENTAS ADD CONSTRAINT VENTAS_SUCURSAL
    FOREIGN KEY (id_sucursal)
    REFERENCES SUCURSAL (id_sucursal)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

