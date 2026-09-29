CREATE DATABASE IF NOT EXISTS jl_suportes
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE jl_suportes;

CREATE TABLE IF NOT EXISTS clientes (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(120) NOT NULL,
 email VARCHAR(150),
 telefone VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS usuarios (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nome VARCHAR(120) NOT NULL,
 login VARCHAR(60) NOT NULL UNIQUE,
 senha VARCHAR(255) NOT NULL,
 tipo ENUM('Administrador','Técnico','Cliente') NOT NULL
);

CREATE TABLE IF NOT EXISTS chamados (
 id INT AUTO_INCREMENT PRIMARY KEY,
 cliente_id INT NOT NULL,
 descricao VARCHAR(500) NOT NULL,
 prioridade ENUM('Baixa','Média','Alta') NOT NULL,
 status ENUM('Aberto','Em atendimento','Resolvido') NOT NULL DEFAULT 'Aberto',
 tecnico_id INT,
 data_abertura DATE NOT NULL,
 CONSTRAINT fk_chamado_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
 CONSTRAINT fk_chamado_tecnico FOREIGN KEY (tecnico_id) REFERENCES usuarios(id)
);

INSERT IGNORE INTO usuarios (id,nome,login,senha,tipo) VALUES
(1,'João Técnico','joao','123','Técnico'),
(2,'Administrador','admin','123','Administrador');

INSERT IGNORE INTO clientes (id,nome,email,telefone) VALUES
(1,'Carlos Cliente','carlos@email.com','(19) 99999-0001'),
(2,'Maria Silva','maria@email.com','(19) 99999-0002');

INSERT IGNORE INTO chamados (id,cliente_id,descricao,prioridade,status,tecnico_id,data_abertura) VALUES
(1,1,'Impressora do setor não imprime','Alta','Aberto',1,CURDATE()),
(2,2,'Sem acesso ao sistema','Média','Em atendimento',1,CURDATE());
