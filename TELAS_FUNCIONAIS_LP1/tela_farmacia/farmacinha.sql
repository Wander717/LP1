CREATE DATABASE farmacia;

USE farmacia;

CREATE TABLE registros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_funcionario VARCHAR(100),
    nome_remedio VARCHAR(100),
    tipo_remedio VARCHAR(100),
    quantidade_remedio INT,
    nome_cliente VARCHAR(100),
    idade_cliente INT
);