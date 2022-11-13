Create Database HotelAgencyPT
GO

use HotelAgencyPT
GO

Create Table User(
	iduser int PRIMARY KEY,
	nomeuser varchar(50),
	password varchar(20),
	tipouser char

);
GO

Create Table Cliente(
	idcliente int PRIMARY KEY,
	iduser int FOREIGN KEY REFERENCES User(iduser)

);
GO

Create Table Feedback(
	idfeedback int PRIMARY KEY,
	idcliente int FOREIGN KEY REFERECES Cliente(idcliente),
	descricao varchar(500)

);
GO

Create Table Gestor(
	idgestor int PRIMARY KEY,
	iduser int FOREIGN KEY REFERENCES User(iduser)

);
GO

Create Table Cartao(
	numcartao int PRIMARY KEY,
	datacriacao date,
	dataexp date
);
GO

Create Table Funcionario(
	idfuncionario int PRIMARY KEY,
	iduser int FOREIGN KEY REFERENCES User(iduser)
);
GO

Create Table RegEntrada(
	idregisto int PRIMARY KEY,
	local varchar(50),
	data date,
	numcartao int FOREIGN KEY REFERECES Cartao(numcartao)
);
GO

Create Table Quarto(
	idquarto int PRIMARY KEY,
	descricao varchar(200)
);
GO

Create Table Servico(
	idservico int PRIMARY KEY,
	descricao varchar (20)
);
GO

Create Table Reserva(
	idreserva int PRIMARY KEY,
	idcliente int FOREIGN KEY REFERECES Cliente(idcliente),
	idquarto int FOREIGN KEY REFERECES Quarto(idquarto),
	idservico int FOREIGN KEY REFERECES Servico(idservico),
	numcartao int FOREIGN KEY REFERECES Cartao(numcartao)
);
GO

Create Table CartaoServico(
	numcartao int,
	idservico int,
	PRIMARY KEY (numcartao, idservico),
	FOREIGN KEY (numcartao) REFERENCES Cartao(numcartao)
	FOREIGN KEY (idservico) REFERENCES Servico(idservico)
);
GO

Create Table Item(
	iditem int PRIMARY KEY,
	nomeitem varchar(50),
	estado varchar(10)
);
GO

Create Table Stock(
	iditem int PRIMARY KEY,
	quantidade int,
	FOREIGN KEY (iditem) REFERECES Item(iditem)
);
GO

Create Table Entrega(
	identrega int PRIMARY KEY,
	iditem int FOREIGN KEY REFERENCES Item(iditem),
	quantidade int
);
GO

Create Table Fornecedor(
	idfornecedor int PRIMARY KEY,
	nomefornecedor varchar(50),
	email varchar(50),
	telemovel numeric(9),
	morada varchar(50)
);
GO

Create Table FicheiroXML(
	idficheiro int PRIMARY KEY,
	descricao varchar(50)
);
GO
