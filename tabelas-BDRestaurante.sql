CREATE TABLE Cliente(
	id_cliente SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL
);

CREATE TABLE Funcionario(
	id_funcionario SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	cargo VARCHAR NOT NULL
);

CREATE TABLE Prato(
	id_prato SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	descricao VARCHAR NOT NULL,
	preco NUMERIC(4, 2) NOT NULL
);

CREATE TABLE Pedido(
	id_pedido SERIAL PRIMARY KEY,
	data_pedido DATE NOT NULL,
	status VARCHAR NOT NULL,
	id_clientefk INTEGER NOT NULL,
	id_funcionariofk INTEGER NOT NULL,
	id_pratofk INTEGER NOT NULL,

	FOREIGN KEY (id_pratofk) REFERENCES Prato(id_prato),
	FOREIGN KEY (id_clientefk) REFERENCES Cliente(id_cliente),
	FOREIGN KEY (id_funcionariofk) REFERENCES Funcionario(id_funcionario)
);


CREATE TABLE ItemDePedido(
	id_item_de_pedido SERIAL PRIMARY KEY,
	quantidade INTEGER NOT NULL,
	id_pedidofk INTEGER NOT NULL,
	id_pratofk INTEGER NOT NULL,
	
	FOREIGN KEY (id_pedidofk) REFERENCES Pedido(id_pedido),
	FOREIGN KEY (id_pratofk) REFERENCES Prato(id_prato)
);

