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

CREATE TABLE profesionalXservicio 
(
    id_profesional int not null,
    id_servicio int not null,
    primary key(id_profesional,id_servicio)
)ENGINE=InnoDB;

CREATE TABLE servicio 
(
	id_servicio int not null auto_increment,
    nombre varchar(45) not null,
    precio float not null,
    duracion int not null,  
    puntos int not null,
	primary key(id_servicio)
)ENGINE=InnoDB;

CREATE TABLE cliente
(
	id_cliente int NOT NULL AUTO_INCREMENT,
	nombre varchar(45) not null,
	apellido varchar(45),
	dni varchar(45),
    email varchar(45),
	telefono varchar(45),
	estado_cliente varchar(45),
	ultima_visita date,
	primary key (id_cliente)
)ENGINE=InnoDB;

CREATE TABLE sucursal 
(
	id_sucursal int not null auto_increment,
    nombre varchar(45) not null,
    idioma varchar(45) not null,
    calle varchar(45) not null, 
    altura int not null,
    primary key(id_sucursal)
)ENGINE=InnoDB;

CREATE TABLE usuario
(
	id_usuario int not null auto_increment,
    nombre_user varchar(45) not null,
    nombre varchar(45) not null,
    apellido varchar(45) not null,
    dni varchar(45) not null,
    email varchar(45) not null, 
    pass varchar(45),
    rol varchar(45),
    id_sucursal int, 
    estado_usuario varchar(45),
    primary key(id_usuario)
)ENGINE=InnoDB;

CREATE TABLE turno
(
	id_turno int NOT NULL AUTO_INCREMENT,
	fecha date not null,
	hora_inicio time not null,
    estado_turno varchar(45),
    precio decimal not null,
    monto_pagado decimal not null,
    puntaje int not null,
	id_promocion int not null, 
    id_sucursal int not null,
    id_cliente int not null,
   	primary key (id_turno)
)ENGINE=InnoDB;

CREATE TABLE caja 
(
	id_caja int not null auto_increment,
    fecha date not null,
    tipo_movimiento varchar(45),
    id_sucursal int not null, 
    id_turno int not null,
    detalle varchar(45),
    monto decimal, 
    primary key(id_caja)
)ENGINE=InnoDB;

CREATE TABLE detalle_turno 
(
	id_detalle_turno int not null auto_increment,
    hora_inicio time not null,
    hora_fin time not null,
    id_profesional int not null,
    id_servicio int not null, 
    id_turno int not null,
    primary key(id_detalle_turno)
)ENGINE=InnoDB;

CREATE TABLE promocion
(
	id_promocion int not null auto_increment,
    nombre varchar(45) not null,
    estado varchar(45) not null,
    precio decimal not null,
    multiplicacion int not null,
    primary key(id_promocion)
)ENGINE = InnoDB;

CREATE TABLE servicioXpromocion
(
	id_servicio int not null,
    id_promocion int not null,
    primary key(id_servicio, id_promocion)
)ENGINE = InnoDB;

alter table turno
add constraint fk_id_cliente
foreign key(id_cliente)
references cliente(id_cliente)
on update cascade on delete cascade;

alter table turno
add constraint fk_id_promocion
foreign key(id_promocion)
references promocion(id_promocion)
on update cascade on delete cascade;

alter table turno
add constraint fk_id_sucursal
foreign key(id_sucursal)
references sucursal(id_sucursal)
on update cascade on delete cascade;

alter table detalle_turno
add constraint fk_id_profesional
foreign key(id_profesional)
references profesional(id_profesional)
on update cascade on delete cascade;

alter table detalle_turno
add constraint fk_id_servicio
foreign key(id_servicio)
references servicio(id_servicio)
on update cascade on delete cascade;

alter table detalle_turno
add constraint fk_id_turno
foreign key(id_turno)
references turno(id_turno)
on update cascade on delete cascade;

alter table caja
add constraint fk_id_caja_turno
foreign key(id_turno)
references turno(id_turno)
on update cascade on delete cascade;

alter table caja
add constraint fk_id_caja_sucursal
foreign key(id_sucursal)
references sucursal(id_sucursal)
on update cascade on delete cascade;

alter table usuario
add constraint fk_id_user_sucursal
foreign key(id_sucursal)
references sucursal(id_sucursal)
on update cascade on delete cascade;

alter table profesional
add constraint fk_id_prof_sucursal
foreign key(id_sucursal)
references sucursal(id_sucursal)
on update cascade on delete cascade;

alter table profesionalXservicio
add constraint fk_id_profXserv_servicio
foreign key(id_servicio)
references servicio(id_servicio)
on update cascade on delete cascade;

alter table profesionalXservicio
add constraint fk_id_profXserv_profesional
foreign key(id_profesional)
references profesional(id_profesional)
on update cascade on delete cascade;

alter table servicioXpromocion
add constraint fk_id_servXprom_servicio
foreign key(id_servicio)
references servicio(id_servicio)
on update cascade on delete cascade;

alter table servicioXpromocion
add constraint fk_id_servXprom_promocion
foreign key(id_promocion)
references promocion(id_promocion)
on update cascade on delete cascade;



insert into sucursal(nombre,idioma,calle,altura) values("pepepelo","espaniol","velazquez",1635);
insert into sucursal(nombre,idioma,calle,altura) values("la peluqueria de juan","espaniol","peron",1635);
insert into sucursal(nombre,idioma,calle,altura) values("el recorte","espaniol","lacalle",1635);
insert into sucursal(nombre,idioma,calle,altura) values("jorge y asociados","espaniol","talcahuano",1635);

insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("pedro","sanchez","pedrito@gmail.com","252525","382348871",1,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("pia","del valle","iabarcae@yahoo.es","252525","38832871",1,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("lucas","burgos","osabarca@hotmail.com","252525","38918871",2,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("pedro","sanchez","pedrito@gmail.com","252525","382348871",1,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("sebastian","yupanqui","cabrigo@garmendia.cl","252525","38698871",2,"INACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("santiago","dias","Sb.nashxo.sk8@hotmail.com","252525","38038871",3,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("bruno","del valle","fran.afull@live.c","252525","38893871",3,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("marcos","rodriguez","carlosaguileram@hotmail.com","252525","38856871",4,"INACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("ignacio","wayne","ikis_rojo@hotmail.com","252525","38888871",4,"ACTIVO");
insert into profesional(nombre,apellido,email,telefono,dni,id_sucursal,estado_profesional) values("jorge","pedron","daniela_aguilera_m500@hotmail.com","252525","38849871",1,"ACTIVO");

insert into servicio(nombre,precio,duracion,puntos) values("corte",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("unias",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("masaje",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("extensiones",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("peinado",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("barba",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("reflejos",1000,30,300);
insert into servicio(nombre,precio,duracion,puntos) values("masaje completo",1000,60,300);


insert into profesionalXservicio(id_servicio,id_profesional) values(1,1);
insert into profesionalXservicio(id_servicio,id_profesional) values(2,1);
insert into profesionalXservicio(id_servicio,id_profesional) values(3,1);
insert into profesionalXservicio(id_servicio,id_profesional) values(4,1);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,2);
insert into profesionalXservicio(id_servicio,id_profesional) values(4,2);
insert into profesionalXservicio(id_servicio,id_profesional) values(2,2);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,3);
insert into profesionalXservicio(id_servicio,id_profesional) values(2,3);
insert into profesionalXservicio(id_servicio,id_profesional) values(5,3);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,4);
insert into profesionalXservicio(id_servicio,id_profesional) values(2,4);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,5);
insert into profesionalXservicio(id_servicio,id_profesional) values(3,5);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,6);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,7);
insert into profesionalXservicio(id_servicio,id_profesional) values(5,7);
insert into profesionalXservicio(id_servicio,id_profesional) values(6,8);
insert into profesionalXservicio(id_servicio,id_profesional) values(3,8);
insert into profesionalXservicio(id_servicio,id_profesional) values(1,9);

insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("julian","rodriguez","38888871","julian@gmail.com",46673857,"ACTIVO","2019-10-27");
insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("pedro","chavez","38957632","perrontres@gmail.com",46596857,"INACTIVO","2019-10-27");
insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("pedro","rodriguez","38788871","julian@gmail.com",46673857,"ACTIVO","2019-10-27");
insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("julian","rodriguez","38823871","julian@gmail.com",46673857,"ACTIVO","2019-10-27");
insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("marcos","altopu","38865871","marquitos@gmail.com",46673857,"ACTIVO","2019-10-27");
insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("julian","rodriguez","35998879","julian@gmail.com",46673857,"ACTIVO","2019-10-27");
insert into cliente(nombre,apellido,dni,email,telefono,estado_cliente,ultima_visita) values("alina","rodriguez","35998879","julian@gmail.com",46693557,"ACTIVO","2019-8-27");

insert into usuario(nombre_user,nombre,apellido,dni,email,pass,rol,id_sucursal,estado_usuario) values("churrok","Julian","Rodriguez","38888871","julianchurrok@gmail.com","root","ADMINISTRADOR",1,"ACTIVO");
insert into usuario(nombre_user,nombre,apellido,dni,email,pass,rol,id_sucursal,estado_usuario) values("karlux","Carlos","Figueroa","38888871","lumusika@gmail.com","root","ADMINISTRATIVO",1,"ACTIVO");
insert into usuario(nombre_user,nombre,apellido,dni,email,pass,rol,id_sucursal,estado_usuario) values("karlux2","Luciano","Figueroa","38888871","karlux@gmail.com","root","CONTADOR",1,"ACTIVO");
insert into usuario(nombre_user,nombre,apellido,dni,email,pass,rol,id_sucursal,estado_usuario) values("karlux3","Marcos","Vera","38888871","marcosGit@gmail.com","root","SUPERVISOR",1,"ACTIVO");

insert into promocion(nombre,estado,precio,multiplicacion) values("sin promocion","ACTIVO",0,0);
insert into promocion(nombre,estado,precio,multiplicacion) values("combo loco1","ACTIVO",1000,3);
insert into promocion(nombre,estado,precio,multiplicacion) values("combo loco2","ACTIVO",1000,3);
insert into promocion(nombre,estado,precio,multiplicacion) values("combo loco3","ACTIVO",1000,3);
insert into promocion(nombre,estado,precio,multiplicacion) values("combo loco4","ACTIVO",1000,3);



insert into servicioxpromocion(id_servicio,id_promocion) values(1,1);
insert into servicioxpromocion(id_servicio,id_promocion) values(2,1);
insert into servicioxpromocion(id_servicio,id_promocion) values(3,1);

insert into servicioxpromocion(id_servicio,id_promocion) values(2,2);
insert into servicioxpromocion(id_servicio,id_promocion) values(3,2);
insert into servicioxpromocion(id_servicio,id_promocion) values(4,2);

insert into servicioxpromocion(id_servicio,id_promocion) values(3,3);
insert into servicioxpromocion(id_servicio,id_promocion) values(4,3);
insert into servicioxpromocion(id_servicio,id_promocion) values(5,3);

insert into turno(fecha,hora_inicio,estado_turno,precio,monto_pagado,puntaje,id_promocion,id_sucursal,id_cliente) values("2019-11-28","18:30","OCUPADO",1000,0,100,1,1,1);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("18:30","19:00",1,1,1);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("19:30","19:30",1,2,1);

insert into turno(fecha,hora_inicio,estado_turno,precio,monto_pagado,puntaje,id_promocion,id_sucursal,id_cliente) values("2019-11-28","10:00","OCUPADO",1000,0,100,1,1,5);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("10:00","10:30",1,3,2);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("10:30","11:00",8,6,2);

insert into turno(fecha,hora_inicio,estado_turno,precio,monto_pagado,puntaje,id_promocion,id_sucursal,id_cliente) values("2019-11-28","18:30","CANCELADO",1000,0,100,1,1,2);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("18:30","19:00",1,1,3);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("19:30","19:30",1,2,3);

insert into turno(fecha,hora_inicio,estado_turno,precio,monto_pagado,puntaje,id_promocion,id_sucursal,id_cliente) values("2019-11-28","18:30","OCUPADO",1000,0,100,1,1,4);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("18:30","19:00",5,3,4);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("19:30","19:30",8,6,4);

insert into turno(fecha,hora_inicio,estado_turno,precio,monto_pagado,puntaje,id_promocion,id_sucursal,id_cliente) values("2019-11-28","18:30","OCUPADO",1000,0,100,1,1,7);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("18:30","19:00",8,3,5);
insert into detalle_turno(hora_inicio,hora_fin,id_profesional,id_servicio,id_turno) values("19:30","19:30",9,1,5);

select * from sucursal;

select * from cliente;

select * from turno;

select * from profesional;

select * from servicio;

select * from profesionalXservicio; 

select * from turno,detalle_turno where turno.id_turno = detalle_turno.id_turno;

select profesional.nombre,servicio.nombre from profesional,profesionalXservicio,servicio where profesional.id_profesional = profesionalXservicio.id_profesional and profesionalXservicio.id_servicio = servicio.id_servicio;
