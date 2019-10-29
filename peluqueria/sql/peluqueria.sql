DROP DATABASE IF EXISTS  peluqueria;
CREATE DATABASE peluqueria;
USE peluqueria;

CREATE TABLE profesional
(
  id_profesional int NOT NULL AUTO_INCREMENT,
  nombre varchar(45) NOT NULL,
  apellido varchar(45) NOT NULL,
  email varchar(45) not null,
  telefono varchar(45) not null,
  dni varchar(45) not null,
  id_sucursal int,
  estado_profesional varchar(45),
  PRIMARY KEY (id_profesional)
)ENGINE=InnoDB;

CREATE TABLE habilidad 
(
	id_habilidad int not null auto_increment,
    id_profesional int not null,
    id_servicio int not null,
    primary key(id_habilidad)
)ENGINE=InnoDB;

CREATE TABLE servicio 
(
	id_servicio int not null auto_increment,
    nombre varchar(45) not null,
    precio float,
	primary key(id_servicio)
)ENGINE=InnoDB;

CREATE TABLE cliente
(
	id_cliente int NOT NULL AUTO_INCREMENT,
	nombre varchar(45) not null,
	apellido varchar(45),
	email varchar(45),
	telefono varchar(45),
	dni varchar(45),
   `estado_cliente` varchar(45),
   
	primary key (id_cliente)
)ENGINE=InnoDB;

CREATE TABLE sucursal 
(
	id_sucursal int not null auto_increment,
    nombre varchar(45) not null,
    id_direccion int not null,
    primary key(id_sucursal)
)ENGINE=InnoDB;

CREATE TABLE direccion
(
	id_direccion int not null auto_increment,
    altura int not null, 
    calle varchar(45) not null,
    localidad varchar(45) not null, 
    provincia varchar(45) not null, 
    pais varchar(45) not null,
    primary key(id_direccion)
)ENGINE=InnoDB;

CREATE TABLE usuario
(
	id_usuario int not null auto_increment,
    nombre_user varchar(45) not null,
    email varchar(45) not null, 
    pass varchar(45),
    id_sucursal int, 
    primary key(id_usuario)
)ENGINE=InnoDB;

CREATE TABLE turno
(
	id_turno int NOT NULL AUTO_INCREMENT,
	fecha date not null,
	hora_inicio int not null,
	id_cliente int,
    id_profesional int,
     id_servicio int,
    id_sucursal int,
	estado_turno varchar(45),
   	primary key (id_turno)
)ENGINE=InnoDB;

CREATE TABLE pago (
	id_pago int not null auto_increment,
    fecha_pago date not null,
    estado varchar(45) not null,
    id_turno int not null,
    primary key(id_pago)
)ENGINE=InnoDB;

alter table turno
add constraint fk_id_cliente
foreign key(id_cliente)
references cliente(id_cliente)
on update cascade on delete cascade;

alter table turno
add constraint fk_id_profesional
foreign key(id_profesional)
references profesional(id_profesional)
on update cascade on delete cascade;

alter table turno 
add constraint fk_id_servicio
foreign key (id_servicio)
references servicio(id_servicio)
on update cascade on delete cascade;

alter table habilidad 
add constraint fk_id_servicio_habilidad
foreign key (id_servicio)
references servicio(id_servicio)
on update cascade on delete cascade;

alter table habilidad
add constraint fk_id_profesional_habilidad
foreign key (id_profesional)
references profesional(id_profesional)
on update cascade on delete cascade;

alter table pago
add constraint fk_id_turno
foreign key(id_turno)
references turno(id_turno)
on update cascade on delete cascade;

alter table sucursal 
add constraint fk_id_direccion
foreign key (id_direccion)
references direccion(id_direccion)
on update cascade on delete cascade;

alter table turno 
add constraint fk_id_sucursal
foreign key (id_sucursal)
references sucursal (id_sucursal)
on update cascade on delete cascade;

alter table profesional
add constraint fk_id_sucursal_profesional
foreign key (id_sucursal)
references sucursal (id_sucursal)
on update cascade on delete cascade;

alter table usuario 
add constraint fk_sucursal_usuario
foreign key (id_sucursal)
references sucursal (id_sucursal)
on update cascade on delete cascade;

 insert into direccion(altura, calle, localidad, provincia, pais) values("1238","Av. Eva Duarte de Perón","Grand Bourg","Buenos Aires","Argentina");
 insert into direccion(altura, calle, localidad, provincia, pais) values("933","Directorio","Tortuguitas","Buenos Aires","Argentina");
 insert into direccion(altura, calle, localidad, provincia, pais) values("1745","Av. Mitre","San Miguel","Buenos Aires","Argentina");
 insert into direccion(altura, calle, localidad, provincia, pais) values("128","Av. Rivadavia","Los Polvorines","Buenos Aires","Argentina");
 
insert into sucursal(nombre, id_direccion) values("Hair&Head 1", 1);
insert into sucursal(nombre, id_direccion) values("Hair&Head 2", 2);
insert into sucursal(nombre, id_direccion) values("Hair&Head 3", 3);
insert into sucursal(nombre, id_direccion) values("Hair&Head 4", 4);

insert into servicio(nombre, precio) values("Corte de pelo",200);
insert into servicio(nombre, precio) values("Corte de barba",150);
insert into servicio(nombre, precio) values("Tintura",350);
insert into servicio(nombre, precio) values("Alisado",250);
insert into servicio(nombre, precio) values("Permanente",400);
insert into servicio(nombre, precio) values("Brushing",300);
insert into servicio(nombre, precio) values("Claritos",650);
insert into servicio(nombre, precio) values("Peinados",600);

insert into cliente(nombre,apellido,email,telefono,dni,estado_cliente) values("Cliente","casual","","","","ACTIVO"); 
insert into cliente(nombre,apellido,email,telefono,dni,estado_cliente) values("Carlos","Figueroa","carl@gmail.com","1155446688","44506206","ACTIVO"); 
insert into cliente(nombre,apellido,email,telefono,dni,estado_cliente) values("Marcos","Vera","marcv@gmail.com","115545658","445025406","ACTIVO"); 
insert into cliente(nombre,apellido,email,telefono,dni,estado_cliente) values("Sebastian","Gamarra","sebG@gmail.com","1155479888","44145206","ACTIVO");

insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Juan fernando", "Quintero", "quinterogoool@gmail.com","111222334","45578132", 1, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Lucas", "Pratto", "prattolucas@gmail.com","1561222334","45123132", 1, "INACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Gonzalo", "Martinez", "gonzamartinez@gmail.com","12312","454548132", 1, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Diaz", "Cesar", "dcar@gmail.com","1161089595","32454813",1, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Marquez", "Adrian", "maradri@gmail.com","1125364878","384548133", 1, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Gutierrez", "Jonas", "futboljg@gmail.com","1565789645","294548132", 2, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Sánchez", "Ricardo", "rsanchez@gmail.com","1522226451","354548234", 2, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Moyano", "Analía", "micorreo@gmail.com","1565984545","40454813", 2, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Barrionuevo", "Celeste", "celes2000@gmail.com","1578651245","36456313", 3, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Álvarez", "Aylén", "prisci15@gmail.com","1598652164","27458953", 3, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Cabrera", "Juliana", "jc1988@gmail.com","1545457823","32454813", 4, "ACTIVO");
insert into profesional(nombre, apellido, email, telefono, dni, id_sucursal, estado_profesional) values("Gómez", "Karen", "kary98@gmail.com","1556455645","28454945", 4, "ACTIVO");

insert into habilidad(id_profesional, id_servicio) values(1, 1);
insert into habilidad(id_profesional, id_servicio) values(1, 2);
insert into habilidad(id_profesional, id_servicio) values(1, 4);
insert into habilidad(id_profesional, id_servicio) values(2, 3);
insert into habilidad(id_profesional, id_servicio) values(2, 5);
insert into habilidad(id_profesional, id_servicio) values(3, 1);
insert into habilidad(id_profesional, id_servicio) values(3, 4);
insert into habilidad(id_profesional, id_servicio) values(4, 8);
insert into habilidad(id_profesional, id_servicio) values(4, 6);
insert into habilidad(id_profesional, id_servicio) values(4, 5);
insert into habilidad(id_profesional, id_servicio) values(5, 1);
insert into habilidad(id_profesional, id_servicio) values(5, 3);
insert into habilidad(id_profesional, id_servicio) values(6, 8);
insert into habilidad(id_profesional, id_servicio) values(6, 7);
insert into habilidad(id_profesional, id_servicio) values(7, 4);
insert into habilidad(id_profesional, id_servicio) values(8, 8);
insert into habilidad(id_profesional, id_servicio) values(8, 1);
insert into habilidad(id_profesional, id_servicio) values(9, 2);
insert into habilidad(id_profesional, id_servicio) values(9, 8);
insert into habilidad(id_profesional, id_servicio) values(10, 6);
insert into habilidad(id_profesional, id_servicio) values(11, 3);
insert into habilidad(id_profesional, id_servicio) values(11, 2);
insert into habilidad(id_profesional, id_servicio) values(12, 1);
insert into habilidad(id_profesional, id_servicio) values(12, 2);
insert into habilidad(id_profesional, id_servicio) values(12, 8);

insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-06", 9, 2, 1, 1, 1, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-06", 12, 3, 1, 5, 1, "CANCELADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-07", 17, 2, 1, 7, 2, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-07", 18, 1, 1, 6, 2, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-06", 11, 1, 2, 8, 3, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-07", 13, 1, 2, 3, 3, "CANCELADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-07", 15, 1, 2, 2, 4, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-06", 16, 2, 2, 1, 4, "DEMORADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-17", 9, 2, 1, 1, 1, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-17", 12, 3, 1, 5, 1, "CANCELADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-15", 17, 2, 1, 7, 1, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-24", 18, 1, 1, 6, 1, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-13", 11, 1, 2, 8, 3, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-18", 13, 1, 2, 3, 3, "CANCELADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-25", 15, 1, 2, 2, 4, "OCUPADO");
insert into turno(fecha, hora_inicio, id_cliente, id_profesional, id_servicio, id_sucursal, estado_turno) values("2019-10-09", 16, 2, 2, 1, 4, "DEMORADO");

select * from cliente;