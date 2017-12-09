create table usuario(
	id SERIAL,
	nome varchar(200) not null,
	email varchar(180) not null,
	PRIMARY KEY(id)
);

create table filme(
	id SERIAL,
	nome varchar(150) not null,
	genero varchar(80) not null,
	ator_principal varchar(150) not null,
	ator_coadjuvante varchar(150),
	duracao varchar(100) not null,
	nome_diretor varchar(150) not null,
	data_lancamento date not null,
	PRIMARY KEY(id)
);

create table ator(
	id SERIAL,
	nome varchar(200) not null,
	PRIMARY KEY(id)
);

create table genero(
	id SERIAL,
	nome varchar(150),
	PRIMARY KEY(id)
);

create table preferencia_genero(
	id_usuario integer not null,
	id_genero integer not null,
	PRIMARY KEY(id_usuario, id_genero)
);

create table preferencia_ator(
	id_usuario integer not null,
	id_ator integer not null,
	PRIMARY KEY(id_usuario, id_ator)
);

create table filme_assistido(
	id_usuario integer not null,
	id_filme integer not null,
	PRIMARY KEY(id_usuario, id_filme)
);









