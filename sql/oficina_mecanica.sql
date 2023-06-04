DROP SCHEMA IF EXISTS oficina_mecanica;
CREATE SCHEMA IF NOT EXISTS oficina_mecanica;

USE oficina_mecanica;

CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    senha VARCHAR(32) NOT NULL,
    permission ENUM('Cliente','Funcionario','Admin') DEFAULT 'Cliente' NOT NULL,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE veiculo (
    id_veiculo INT NOT NULL AUTO_INCREMENT,
    placa CHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(200) NOT NULL,
    tipo ENUM ('Carro', 'Moto') NOT NULL,
    id_cliente INT NOT NULL,
    PRIMARY KEY (id_veiculo),
    CONSTRAINT FK_id_cliente FOREIGN KEY (id_cliente) REFERENCES usuario(id_usuario)
);

CREATE TABLE horario (
    id_horario INT NOT NULL AUTO_INCREMENT,
    status ENUM('Livre','Ocupado') DEFAULT 'Livre' NOT NULL,
    data datetime NOT NULL,
	PRIMARY KEY (id_horario)
);

CREATE TABLE agenda (
    id_agenda INT NOT NULL AUTO_INCREMENT,
    id_horario INT NOT NULL,
    id_veiculo INT NOT NULL,
    status	ENUM('Pendente', 'Aprovado', 'Reprovado', 'Em_Andamento', 'Concluido', 'Cancelado') DEFAULT 'Pendente' NOT NULL,
    dt_previsao DATE,
    dt_fim DATE,
    observacao VARCHAR(5000),
    PRIMARY KEY (id_agenda),
	CONSTRAINT FK_id_horario FOREIGN KEY (id_horario) REFERENCES horario(id_horario),
    CONSTRAINT FK_id_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo)
);

CREATE TABLE produto (
    id_produto INT NOT NULL AUTO_INCREMENT,
    id_agenda INT NOT NULL,
	descricao VARCHAR(5000),
	PRIMARY KEY (id_produto),
	CONSTRAINT FK_id_agenda FOREIGN KEY (id_agenda) REFERENCES agenda(id_agenda)
);

CREATE TABLE servico (
    id_servico INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
	descricao VARCHAR(5000),
	PRIMARY KEY (id_servico)
);


CREATE TABLE servico_agenda (
	id_servico INT NOT NULL,
    id_agenda INT NOT NULL,
	observacao VARCHAR(5000),
	CONSTRAINT FK_id_servico_a FOREIGN KEY (id_servico) REFERENCES servico(id_servico),
	CONSTRAINT FK_id_agenda_a FOREIGN KEY (id_agenda) REFERENCES agenda(id_agenda),
    
    PRIMARY KEY (id_servico, id_agenda)
);

#INSERTS
INSERT INTO usuario (nome, cpf, email, telefone, senha, permission) VALUES("Gustavo", "050.189.540-00", "gustavo@gmail.com", "11111111111", "123", "Cliente");