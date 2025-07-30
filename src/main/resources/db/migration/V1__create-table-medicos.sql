create table medicos(

    id bigint not null auto_increment,
    estado tinyint not null,
    titulo varchar(100) not null unique,
    mensaje varchar(200) not null unique,
    autor varchar(100) not null,
    curso varchar(100) not null,
    fechaCreacion datetime not null,

    primary key(id)

);

create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    contrasena varchar(255) not null,

    primary key(id)

);