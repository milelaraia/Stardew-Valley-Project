DROP DATABASE IF EXISTS E2;
CREATE DATABASE E2;
USE E2;

create table Inventario(
	idInventario int primary key auto_increment,
    capacidade int
);

DROP TABLE IF EXISTS Jogador;
create table Jogador(
	idJogador int primary key auto_increment,
    nome varchar(45),
    nome_fazenda varchar(45),
    animal_favorito varchar(20),
    coisa_favorita varchar(20),
    genero varchar(10),
    energia int,
    saude int,
    Inventario_idInventario int,
    CONSTRAINT fk_Jogador_Inventario FOREIGN KEY (Inventario_idInventario)
		REFERENCES Inventario(idInventario) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Aldeoes;
create table Aldeoes(
	idAldeoes int primary key auto_increment,
    nome varchar(45),
    hobby varchar(45),
    presente_que_ama varchar(45)
);

DROP TABLE IF EXISTS Animais;
create table Animais(
	idAnimais int primary key auto_increment,
    tipo varchar(45),
    nome varchar(45)
);

DROP TABLE IF EXISTS Habilidades;
create table Habilidades(
	idHabilidades int primary key auto_increment,
    cultivo int,
    mineracao int,
    coleta int,
    pesca int,
    combate int,
    Jogador_idJogador int,
    Inventario_idInventario int,
    CONSTRAINT fk_Habilidades_Jogador FOREIGN KEY (Jogador_idJogador)
		REFERENCES Jogador(idJogador) ON DELETE CASCADE,
	CONSTRAINT fk_Habilidades_Inventario FOREIGN KEY (Inventario_idInventario)
		REFERENCES Inventario(idInventario) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Itens;
create table Itens(
	idItens int primary key auto_increment,
    ferramentas varchar(45),
    armas varchar(45),
    aneis varchar(45),
    Inventario_idInventario int,
    CONSTRAINT fk_Itens_Inventario FOREIGN KEY (Inventario_idInventario)
		REFERENCES Inventario(idInventario) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Lavouras;
create table Lavouras(
	idLavouras int primary key auto_increment,
    flores varchar(45),
    frutas varchar(45),
    vegetais varchar(45),
    graos varchar(45),
    hortalicas varchar(45),
    tuberculos varchar(45)
);

CREATE TABLE Amizade(
    idAmizade INT PRIMARY KEY AUTO_INCREMENT,
    nivel VARCHAR(20),
    Jogador_idJogador INT,
    Aldeoes_idAldeoes INT,
    Animais_idAnimais INT,

    CONSTRAINT fk_amiz_jog FOREIGN KEY (Jogador_idJogador) REFERENCES Jogador(idJogador) ON DELETE CASCADE,
    CONSTRAINT fk_amiz_ald FOREIGN KEY (Aldeoes_idAldeoes) REFERENCES Aldeoes(idAldeoes) ON DELETE CASCADE,
    CONSTRAINT fk_amiz_anim FOREIGN KEY (Animais_idAnimais) REFERENCES Animais(idAnimais) ON DELETE CASCADE
);

INSERT INTO Inventario (capacidade) VALUES (20), (30), (40);

INSERT INTO Jogador (nome, nome_fazenda, animal_favorito, coisa_favorita, genero, energia, saude, Inventario_idInventario) VALUES
('Mile', 'Mar Azul', 'Gato', 'Peixe', 'Feminino', 250, 200, 1),
('Giu', 'Luar', 'Gato', 'Cristal', 'Feminino', 230, 210, 2),
('Amoeba', 'Molenga', 'Cachorro', 'Vinho', 'Masculino', 260, 190, 3);

INSERT INTO Aldeoes (nome, hobby, presente_que_ama) VALUES
('Abigail', 'Explorar minas', 'Ametista'),
('Sebastian', 'Programar', 'Lasanha'),
('Penny', 'Ensinar crianças', 'Livro');

INSERT INTO Animais (tipo, nome) VALUES
('Vaca', 'Bessie'),
('Galinha', 'Chico'),
('Cabra', 'Bernadete');

INSERT INTO Habilidades (cultivo, mineracao, coleta, pesca, combate, Jogador_idJogador, Inventario_idInventario) VALUES
(24, 80, 0, 0, 20, 1, 1),
(12, 0, 0, 70, 10, 2, 2),
(18, 50, 0, 40, 30, 3, 3);

INSERT INTO Itens (ferramentas, armas, aneis, Inventario_idInventario) VALUES
('Enxada', 'Espada de Ferro', 'Anel de Ouro', 1),
('Regador', 'Adaga de Aço', 'Anel de Prata', 2),
('Machado', 'Martelo Pesado', 'Anel de Safira', 3);

INSERT INTO Lavouras (flores, frutas, vegetais, graos, hortalicas, tuberculos) VALUES
('Rosa', 'Maçã', 'Cenoura', 'Trigo', 'Alface', 'Batata'),
('Tulipa', 'Pêssego', 'Tomate', 'Milho', 'Cebolinha', 'Inhame'),
('Girassol', 'Uva', 'Beterraba', 'Cevada', 'Espinafre', 'Nabo');

INSERT INTO Amizade (nivel, Jogador_idJogador, Aldeoes_idAldeoes, Animais_idAnimais) VALUES
('Alto', 1, 1, 1),
('Médio', 2, 2, 2),
('Baixo', 3, 3, 3);

-- calcular energia total (soma de energia e saúde do jogador)
DROP FUNCTION IF EXISTS fn_calcula_pontuacao_total;

DELIMITER $$

CREATE FUNCTION fn_calcula_pontuacao_total(p_jogador_id INT) RETURNS INT
DETERMINISTIC
BEGIN
  DECLARE v_energia INT;
  DECLARE v_saude INT;
  SELECT energia, saude INTO v_energia, v_saude 
  FROM Jogador 
  WHERE idJogador = p_jogador_id;
  RETURN (v_energia + v_saude);
END $$

CREATE PROCEDURE sp_atualiza_energia (
  IN p_jogador_id INT,
  IN p_nova_energia INT
)
BEGIN
  UPDATE Jogador SET energia = p_nova_energia WHERE idJogador = p_jogador_id;
END $$

CREATE TRIGGER trg_default_jogador
BEFORE INSERT ON Jogador
FOR EACH ROW
BEGIN
  IF NEW.energia IS NULL THEN SET NEW.energia = 200; END IF;
  IF NEW.saude IS NULL THEN SET NEW.saude = 150; END IF;
END $$

DELIMITER ;

-- resumo de amizades por jogador
CREATE OR REPLACE VIEW vw_resumo_amizades AS
SELECT 
    j.nome AS jogador,
    COUNT(a.idAmizade) AS qtd_amizades,
    GROUP_CONCAT(al.nome SEPARATOR ', ') AS aldeoes_amigos
FROM Jogador j
LEFT JOIN Amizade a ON j.idJogador = a.Jogador_idJogador
LEFT JOIN Aldeoes al ON al.idAldeoes = a.Aldeoes_idAldeoes
GROUP BY j.idJogador, j.nome;

UPDATE Jogador 
JOIN (SELECT idJogador FROM Jogador WHERE nome = 'Amoeba') AS alvo
ON Jogador.idJogador = alvo.idJogador
SET Jogador.energia = Jogador.energia + 20;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM Amizade WHERE nivel = 'Baixo';
SET SQL_SAFE_UPDATES = 1;

-- ALTER e DROP
ALTER TABLE Jogador ADD COLUMN ultima_conexao DATETIME;
UPDATE Jogador SET ultima_conexao = NOW() WHERE idJogador = 1;

CREATE USER IF NOT EXISTS 'agiu'@'localhost' IDENTIFIED BY 'Root';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, EXECUTE ON E2.* TO 'agiu'@'localhost';
FLUSH PRIVILEGES;

-- exemplos de uso
CALL sp_atualiza_energia(2, 300);
SELECT nome, fn_calcula_pontuacao_total(idJogador) AS pontuacao_total FROM Jogador;
SELECT * FROM vw_resumo_amizades;
SELECT * FROM Jogador;
select * from Inventario;
