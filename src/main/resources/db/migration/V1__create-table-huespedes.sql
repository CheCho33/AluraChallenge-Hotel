create table huespedes
(
    id bigint not null auto_increment,
    nombre       varchar(100) not null,
    apellido        varchar(100) not null,
    fecha_nacimiento datetime not null,
    nacionalidad varchar(100) not null,
    telefono varchar(100) not null,
    id_reserva    bigint,

    primary key (id)
);