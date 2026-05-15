CREATE DATABASE salao;

USE salao;

CREATE TABLE cliente (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    idade INT(5) NOT NULL
    );
    
CREATE TABLE produto (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo VARCHAR (20) NOT NULL,
    quantidade INT(20) NOT NULL
);

CREATE TABLE funcionario (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR (40) NOT NULL,
    sexo VARCHAR (10) NOT NULL,
    cargo VARCHAR (20) NOT NULL,
    idade int (5) NOT NULL
);
    
