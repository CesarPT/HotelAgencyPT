use [2022_E_LP3_G2]
go

Create Table Utilizador(
	iduser int PRIMARY KEY IDENTITY,
	nomeuser varchar(50),
	password text,
	tipouser char
);
GO

Create Table Cliente(
	idcliente int PRIMARY KEY IDENTITY,
	iduser int FOREIGN KEY REFERENCES Utilizador(iduser)
);
GO

Create Table Feedback(
	idfeedback int PRIMARY KEY IDENTITY,
	idcliente int FOREIGN KEY REFERENCES Cliente(idcliente),
	descricao varchar(500),
	tipofeedback char --(s = sugestao, r = reclamacao)
);
GO

Create Table Gestor(
	idgestor int PRIMARY KEY IDENTITY,
	iduser int FOREIGN KEY REFERENCES Utilizador(iduser)
);
GO

Create Table Cartao(
	numcartao int PRIMARY KEY IDENTITY,
);
GO

Create Table Funcionario(
	idfuncionario int PRIMARY KEY IDENTITY,
	iduser int FOREIGN KEY REFERENCES Utilizador(iduser)
);
GO

Create Table RegEntrada(
	idregisto int PRIMARY KEY IDENTITY,
	local varchar(50),
	data date,
	hora text,
	numcartao int FOREIGN KEY REFERENCES Cartao(numcartao)
);
GO

Create Table Quarto(
	idquarto int PRIMARY KEY IDENTITY,
	descricao varchar(200),
	piso tinyint,
	preco smallmoney,
	numcartao int FOREIGN KEY REFERENCES Cartao(numcartao)
);
GO

Create Table Servico(
	idservico int PRIMARY KEY IDENTITY,
	descricao varchar (20),
	preco smallmoney
);
GO

Create Table Reserva(
	idreserva int PRIMARY KEY IDENTITY,
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
	iditem int PRIMARY KEY IDENTITY,
	nomeitem varchar(50),
	estado varchar(10)
);
GO

create Table Stock(
product_identifier nvarchar(50),
product_description nvarchar(50),
quantidade int,
Primary key(product_identifier)
);
GO

create Table Entrega(
orderNumber Nvarchar(15),
data_entrega Date,
party_identifier Nvarchar(15),
empresa nvarchar(25),
rua nvarchar,
n_porta nvarchar(20),
cidade nvarchar(25),
cp nvarchar(10),
pais nvarchar(20)
Primary key (orderNumber)
);
GO

Create Table Fornecedor( 
id_ph int not NUll,
rua nvarchar(50),
n_porta nvarchar(15),
cidade nvarchar (25),
cp nvarchar(10),
pais nvarchar(15)
PRIMARY KEY (id_ph)
);
GO

Create Table FicheiroXML(
	idficheiro int PRIMARY KEY IDENTITY,
	descricao varchar(50)
);
GO
Create Table TipoUser(
	idtipo int PRIMARY KEY IDENTITY,
	tipouser varchar(50) NOT NULL,
	prefixo char NOT NULL
);
GO