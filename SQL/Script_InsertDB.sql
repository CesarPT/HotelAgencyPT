use [2022_E_LP3_G2]
go

INSERT INTO Cliente
VALUES (1);
INSERT INTO Cliente
VALUES (2);
go

INSERT INTO Quarto
VALUES ('Individual 1', 1, 50),
	   ('Individual 2', 1, 50),
	   ('Individual 3', 1, 50),
	   ('Individual 4', 1, 50),
	   ('Individual 5', 1, 50),
	   ('Individual 6', 1, 50),
	   ('Individual 7', 1, 50),
	   ('Duplo 1', 1, 75),
	   ('Duplo 2', 1, 75),
	   ('Duplo 3', 1, 75),
	   ('Duplo 4', 2, 75),
	   ('Duplo 5', 2, 75),
	   ('Duplo 6', 2, 75),
	   ('Duplo 7', 2, 75),
	   ('Duplo 8', 2, 75),
	   ('Duplo 9', 2, 75),
	   ('Duplo 10', 2, 75),
	   ('Familiar 1', 2, 100),
	   ('Familiar 2', 2, 100),
	   ('Familiar 3', 2, 100);
go

INSERT INTO Servico
VALUES ('gratis', 0)
go

INSERT INTO Cartao
VALUES ('2022-11-20', '2023-02-01')
go

INSERT INTO Reserva
VALUES (1, 1, 1, 1, '2022-11-20')
go

INSERT INTO RegEntrada
VALUES ('Quarto', '2022-11-22', 1)