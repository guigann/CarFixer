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
    permission TINYINT(1) NOT NULL, #0- Cliente; 2- Funcionário/Admin;
    PRIMARY KEY (id_usuario)
);

CREATE TABLE veiculo (
    id_veiculo INT NOT NULL AUTO_INCREMENT,
    placa CHAR(7) NOT NULL UNIQUE,
    descricao VARCHAR(2000) NOT NULL,
    tipo TINYINT(1) NOT NULL, #0- Carro; 1- Moto;
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_veiculo),
    CONSTRAINT FK_usuario_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE agendamento (
    id_agendamento INT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    objetivo INT NOT NULL, #0- Revisão; 1- Conseto;
    unidade ENUM ('Timoteo, Coronel_Fabriciano, Ipatinga'),
    #unidade VARCHAR(2000) NOT NULL,    
    status	ENUM('Pendente, Aprovado, Reprovado, Em_Andamento, Concluido, Encerrado') DEFAULT 'Pendente',
    #status TINYINT(1) NOT NULL, #0- Pendente; 1- Aprovado; 2- Reprovado; 3- Em andamento; 4- Concluído, 5- Encerrado;
    id_veiculo INT NOT NULL,
    PRIMARY KEY (id_agendamento),
    CONSTRAINT FK_veiculo_id_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo)
);

CREATE TABLE servico(
	id_servico INT NOT NULL AUTO_INCREMENT,
    dataPrevEntrega DATE NOT NULL,
    observacao VARCHAR(2000),
    id_agendamento INT NOT NULL,
    PRIMARY KEY (id_servico),
	FOREIGN KEY (id_agendamento) REFERENCES agendamento(id_agendamento)
);

CREATE TABLE item_servico(
	id_item_servico INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(2000), 	
	valor DOUBLE,
    id_servico INT NOT NULL,
	PRIMARY KEY (id_item_servico),
    CONSTRAINT FK_servico_id_servico FOREIGN KEY (id_servico) REFERENCES servico(id_servico)	
);

#INSERTS
INSERT INTO usuario (nome, cpf, email, telefone, senha, perm) VALUES("Gustavo", "050.189.540-00", "gustavo@gmail.com", "11111111111", "123", 0);	
