use [2022_E_LP3_G2]
go

Create Table Utilizador(
	iduser int PRIMARY KEY,
	nomeuser varchar(50),
	password varchar(20),
	tipouser char
);
GO

Create Table Cliente(
	idcliente int PRIMARY KEY,
	iduser int FOREIGN KEY REFERENCES Utilizador(iduser)
);
GO

Create Table Feedback(
	idfeedback int PRIMARY KEY,
	idcliente int FOREIGN KEY REFERENCES Cliente(idcliente),
	descricao varchar(500),
	tipofeedback char --(s = sugestao, r = reclamacao)
);
GO

Create Table Gestor(
	idgestor int PRIMARY KEY,
	iduser int FOREIGN KEY REFERENCES Utilizador(iduser)
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
	iduser int FOREIGN KEY REFERENCES Utilizador(iduser)
);
GO

Create Table RegEntrada(
	idregisto int PRIMARY KEY,
	local varchar(50),
	data date,
	numcartao int FOREIGN KEY REFERENCES Cartao(numcartao)
);
GO

Create Table Quarto(
	idquarto int PRIMARY KEY,
	descricao varchar(200),
	piso tinyint,
	preco smallmoney
);
GO

Create Table Servico(
	idservico int PRIMARY KEY,
	descricao varchar (20),
	preco smallmoney
);
GO

Create Table Reserva(
	idreserva int PRIMARY KEY,
	idcliente int FOREIGN KEY REFERENCES Cliente(idcliente),
	idquarto int FOREIGN KEY REFERENCES Quarto(idquarto),
	idservico int FOREIGN KEY REFERENCES Servico(idservico),
	numcartao int FOREIGN KEY REFERENCES Cartao(numcartao),
	data date
);
GO

Create Table CartaoServico(
	numcartao int,
	idservico int,
	PRIMARY KEY (numcartao, idservico),
	FOREIGN KEY (numcartao) REFERENCES Cartao(numcartao),
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
	FOREIGN KEY (iditem) REFERENCES Item(iditem)
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
