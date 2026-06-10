CREATE DATABASE IF NOT EXISTS barraquinha;
USE barraquinha;

CREATE TABLE cliente (
id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_cliente VARCHAR(100) NOT NULL
);

CREATE TABLE fruta (
id_fruta INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_fruta VARCHAR(50) NOT NULL
);

CREATE TABLE funcionario (
id_funcionario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nome_funcionario VARCHAR(100) NOT NULL
);

CREATE TABLE pedido (
id_pedido      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_cliente     INT NOT NULL,
id_fruta       INT NOT NULL,
id_funcionario INT NOT NULL,
quantidade     INT NOT NULL,
CONSTRAINT fk_pedido_cliente     FOREIGN KEY (id_cliente)     REFERENCES cliente(id_cliente),
CONSTRAINT fk_pedido_fruta       FOREIGN KEY (id_fruta)       REFERENCES fruta(id_fruta),
CONSTRAINT fk_pedido_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);

-- Funcionários iniciais
INSERT INTO funcionario (nome_funcionario) VALUES ('Ana'), ('Carlos'), ('Seu Zé');