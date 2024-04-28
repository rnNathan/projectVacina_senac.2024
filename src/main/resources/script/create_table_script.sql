create schema if not EXISTS vacina;

use vacina;

CREATE TABLE IF NOT EXISTS vacina.pessoa (
    id_pessoa INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dataNascimento DATE NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    tipoPessoaCadastrada INTEGER NOT null comment '1- Pesquisador; 2- Voluntário; 3- Público Geral',
    id int not null,
    FOREIGN KEY (id) REFERENCES vacina.paises (id)
);


CREATE TABLE IF NOT EXISTS vacina.vacinas (
    id_vacina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id int not null,
    id_pessoa INT NOT NULL,
    estagio INTEGER NOT null comment '1- Inicial; 2- Teste; 3- Aplicação em massa',
    dataInicioPesquisa DATE NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES vacina.pessoa (id_pessoa),
    FOREIGN KEY (id) REFERENCES vacina.paises (id)
);


create table if not exists vacina.aplicacao_vacina (
    id INT not null auto_increment primary key,
    id_pessoa int not null,
    id_vacina int not null,
    data_aplicacao date not null,
    avaliacao int not null comment 'valor entre 1 e 5',
 	foreign key (id_pessoa) references vacina.pessoa (id_pessoa),
    foreign key (id_vacina) references vacina.vacinas (id_vacina)
);

create table if not exists vacina.paises (
	id int not null auto_increment primary key,
	nome_pais varchar (56) not null,
	sigla varchar (2) not null
);


