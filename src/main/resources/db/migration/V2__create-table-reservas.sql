create table reservas
(
    id_reserva bigint not null auto_increment,
    fecha_entrada datetime not null,
    fecha_salida datetime not null,
    valor varchar(100) not null,
    FormaPago varchar(100) not null,

    primary key (id)
);