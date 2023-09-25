create table usuarios
(
    id bigint not null auto_increment,
    userName       varchar(100) not null,
    password        varchar(300) not null,
    token        varchar(800),

    primary key (id)
);