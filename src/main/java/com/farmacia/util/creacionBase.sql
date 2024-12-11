-- Table: ALERTAS
CREATE TABLE ALERTAS (
    id_alerta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    estado VARCHAR(15) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    fecha DATE NOT NULL,
    id_producto INT NOT NULL,
    CONSTRAINT ALERTAS_fk_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto) ON DELETE NO ACTION
);

-- Table: CLIENTES
CREATE TABLE CLIENTES (
    id_cliente serial  NOT NULL,
	ci varchar(20) not null,
    nombre varchar(25)  NOT NULL,
    apellidos varchar(25)  NOT NULL,
    telefono varchar(15)  NOT NULL,
    CONSTRAINT CLIENTES_pk PRIMARY KEY (id_cliente)
);

-- Table: DETALLE_VENTAS
CREATE TABLE DETALLE_VENTAS (
    id_detalle_venta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    id_producto INT NOT NULL,
    id_venta INT NOT NULL,
    cantidad INT NOT NULL,
    CONSTRAINT DETALLE_VENTAS_fk_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto) ON DELETE NO ACTION,
    CONSTRAINT DETALLE_VENTAS_fk_venta FOREIGN KEY (id_venta) REFERENCES VENTAS (id_venta) ON DELETE NO ACTION
);

-- Table: INVENTARIO
CREATE TABLE INVENTARIO (
    id_inventario SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    cantidad INT NOT NULL,
    id_producto INT NOT NULL,
    id_sucursal INT NOT NULL,
    CONSTRAINT INVENTARIO_fk_producto FOREIGN KEY (id_producto) REFERENCES PRODUCTO (id_producto) ON DELETE NO ACTION,
    CONSTRAINT INVENTARIO_fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES SUCURSAL (id_sucursal) ON DELETE NO ACTION
);

-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    id_producto SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    nombre VARCHAR(30) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    cod_barras VARCHAR(50) NOT NULL,
    stock INT NOT NULL,
    numero_lote VARCHAR(50) NOT NULL
);

-- Table: PROVEEDORES
CREATE TABLE PROVEEDORES (
    id_proveedor SERIAL PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

-- Table: MEDIDAS
CREATE TABLE MEDIDAS (
	id_medida serial primary key,
	medida varchar(10)
);

-- Table: INDICACIONES
CREATE TABLE INDICACIONES (
	id_indicacion serial primary key,
	indicacion varchar(100)
);

-- Table: RECETAS
create table RECETAS (
	id_receta serial primary key,
	id_cliente int references CLIENTES(id_cliente),
	id_producto int references PRODUCTO(id_producto),
	id_indicacion int references INDICACIONES(id_indicacion),
	id_medida int references MEDIDAS(id_medida),
	fecha_emision date,
	cantidad decimal(10,2)
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
    contraseña VARCHAR(200) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

-- Table: VENTAS
CREATE TABLE VENTAS (
    id_venta SERIAL PRIMARY KEY,  -- SERIAL para auto-incrementar el id
    monto DECIMAL(8,2) NOT NULL,
    fecha_venta DATE NOT NULL,
    cantidad INT NOT NULL,
    id_cliente INT NOT NULL,
    CONSTRAINT VENTAS_fk_cliente FOREIGN KEY (id_cliente) REFERENCES CLIENTES (id_cliente) ON DELETE NO ACTION
);
