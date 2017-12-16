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
    telefone varchar(15)
);

insert into pessoa (nome, sobrenome, telefone) values ("Maria", "Silva", "(11)4141-9898");
insert into pessoa (nome, sobrenome, telefone) values ("João", "Pedro", "(21)4242-8989");
insert into pessoa (nome, sobrenome, telefone) values ("Pedro", "Mariano", "(15)4455-9856");

select * from pessoa;

