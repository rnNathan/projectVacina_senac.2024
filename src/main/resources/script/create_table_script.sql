create schema if not EXISTS vacina;

use vacina;

 
CREATE TABLE IF NOT EXISTS vacina.pessoa (
    ID INTEGER AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dataNascimento DATE NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    tipoPessoa VARCHAR(15) NOT NULL
);
 
 
CREATE TABLE IF NOT EXISTS vacina.vacinas (
    idVacina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    paisOrigem VARCHAR(30) NOT NULL,
    idPessoa INT NOT NULL,
    estagio int NOT NULL,
    dataInicioPesquisa DATE NOT NULL,
    FOREIGN KEY (idPessoa) REFERENCES vacina.pessoa(id)
);
 
create table if not exists vacina.aplicacao_vacina (
    id INT not null auto_increment primary key,
    id_pessoa int not null,
    id_vacina int not null,
    data_aplicacao date not null,
    avaliacao int not null comment 'valor entre 1 e 5',
    constraint id_pessoa foreign key (id_pessoa) references vacina.pessoa (id_pessoa),
    constraint id_vacina foreign key (id_vacina) references vacina.vacinas (id_vacina)
);


