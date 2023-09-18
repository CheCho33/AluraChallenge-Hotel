create table huspedes
(
    id bigint not null auto_increment,
    nombre       varchar(100) not null,
    apellido        varchar(100) not null unique,
    fecha_nacimiento datetime not null,
    nacionalidad varchar(100) not null,
    telefono varchar(100) not null,
    id_reserva     varchar(100) not null,

    primary key (id)
);