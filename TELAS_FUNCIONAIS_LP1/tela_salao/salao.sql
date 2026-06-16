CREATE DATABASE salao;

USE salao;
    
CREATE TABLE produto (
	id_produto INT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(50) NOT NULL,
    tipo_produto VARCHAR (20) NOT NULL,
    quantidade_produto INT(20) NOT NULL
);

CREATE TABLE funcionario (
	id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    nome_funcionario VARCHAR (40) NOT NULL
);
    
CREATE TABLE registro (
id_registro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
data_registro DATE,
id_produto INT NOT NULL,
id_funcionario INT NOT NULL,
CONSTRAINT fk_registro_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
CONSTRAINT fk_registro_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);

INSERT INTO funcionario (nome_funcionario) VALUES ('Francisco');
INSERT INTO funcionario (nome_funcionario) VALUES ('Juliana');
INSERT INTO funcionario (nome_funcionario) VALUES ('Roberto');

