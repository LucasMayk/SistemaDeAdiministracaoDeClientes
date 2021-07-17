create database administracao_clientes;

use administracao_clientes;

CREATE TABLE cliente(
    id INT AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NULL,
    data_nascimento DATE NULL,
    data_cadastro DATE NULL,
    renda_familiar DECIMAL(10, 2) NULL,
    CONSTRAINT pk_cliente PRIMARY KEY(id),
    CONSTRAINT ck_cliente_sexo CHECK(sexo IN('F', 'M')),
    CONSTRAINT ck_cliente_cpf CHECK(LEN(cpf) IN(0, 11))
);

select * from cliente;
