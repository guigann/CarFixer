DROP SCHEMA IF EXISTS oficina_mecanica;
CREATE SCHEMA IF NOT EXISTS oficina_mecanica;

USE oficina_mecanica;

CREATE TABLE usuario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    senha VARCHAR(72) NOT NULL,
    permission ENUM('Cliente','Funcionario','Admin') DEFAULT 'Cliente' NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE veiculo (
    id INT NOT NULL AUTO_INCREMENT,
    placa CHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(200) NOT NULL,
    tipo ENUM ('Carro', 'Moto') NOT NULL,
    id_cliente INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_id_cliente FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE horario (
    id INT NOT NULL AUTO_INCREMENT,
    status ENUM('Livre','Ocupado') DEFAULT 'Livre' NOT NULL,
    data datetime NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE agenda (
    id INT NOT NULL AUTO_INCREMENT,
    id_horario INT NOT NULL,
    id_veiculo INT NOT NULL,
    status	ENUM('Pendente', 'Aprovado', 'Reprovado', 'Em_Andamento', 'Concluido', 'Cancelado') DEFAULT 'Pendente' NOT NULL,
    dt_previsao DATE,
    dt_fim DATE,
    observacao VARCHAR(5000),
    PRIMARY KEY (id),
	CONSTRAINT FK_id_horario FOREIGN KEY (id_horario) REFERENCES horario(id),
    CONSTRAINT FK_id_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id)
);

CREATE TABLE produto (
    id INT NOT NULL AUTO_INCREMENT,
    id_agenda INT NOT NULL,
	descricao VARCHAR(5000),
	PRIMARY KEY (id),
	CONSTRAINT FK_id_agenda FOREIGN KEY (id_agenda) REFERENCES agenda(id)
);

CREATE TABLE servico (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
	descricao VARCHAR(5000),
	PRIMARY KEY (id)
);


CREATE TABLE servico_agenda (
	id_servico INT NOT NULL,
    id_agenda INT NOT NULL,
	observacao VARCHAR(5000),
	CONSTRAINT FK_id_servico_a FOREIGN KEY (id_servico) REFERENCES servico(id),
	CONSTRAINT FK_id_agenda_a FOREIGN KEY (id_agenda) REFERENCES agenda(id),
    
    PRIMARY KEY (id_servico, id_agenda)
);

#INSERTS
INSERT INTO usuario (nome, cpf, email, telefone, senha, permission) VALUES("Gustavo", "050.189.540-00", "gustavo@gmail.com", "11111111111", "123", "Cliente");
