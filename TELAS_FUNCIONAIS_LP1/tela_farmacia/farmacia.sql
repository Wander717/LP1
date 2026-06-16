DROP DATABASE IF EXISTS farmacia;
CREATE DATABASE farmacia;
USE farmacia;

CREATE TABLE cliente (
id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_cliente VARCHAR(20),
idade_cliente INT
);

CREATE TABLE funcionario (
id_funcionario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_funcionario VARCHAR(20),
cargo_funcionario VARCHAR(20)
);

CREATE TABLE remedio (
id_remedio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_remedio VARCHAR(20),
tipo_remedio VARCHAR(10)
);


CREATE TABLE registro (
id_registro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_cliente INT NOT NULL,
id_remedio INT NOT NULL,
id_funcionario INT NOT NULL,
quantidade INT NOT NULL,
CONSTRAINT fk_pedido_cliente     FOREIGN KEY (id_cliente)     REFERENCES cliente(id_cliente),
CONSTRAINT fk_pedido_remedio       FOREIGN KEY (id_remedio)       REFERENCES remedio(id_remedio),
CONSTRAINT fk_pedido_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);