drop database if exists dbpessoa;
create database dbpessoa;
use dbpessoa;

create table usuario (	
    id_usuario bigint auto_increment primary key,
    usuario varchar(100),
    senha varchar(100)
);

insert into usuario (usuario, senha) values ("admin", "admin");
insert into usuario (usuario, senha) values ("diego", "123");
insert into usuario (usuario, senha) values ("123", "123");

select * from usuario;

create table pessoa (
	
    id bigint auto_increment primary key,
    nome varchar(100),
    sobrenome varchar(255),
    telefone varchar(15),
    foto longblob 
);


