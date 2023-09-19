create table reservas
(
    id bigint not null auto_increment,
    fecha_entrada datetime not null,
    fecha_salida datetime not null,
    valor varchar(100),
    FormaPago varchar(100),

    primary key (id)
);