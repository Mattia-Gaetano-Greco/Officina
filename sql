CREATE TABLE Shop(
  nome varchar(50) NOT NULL,
  id_shop varchar(10) NOT NULL
  CONSTRAINT pk_nome PRIMARY KEY (id_shop)
);

CREATE TABLE Parte(
  nome varchar(30) NOT NULL,
  stato char(1) DEFAULT '0',
  id_parte varchar(10) NOT NULL, -- CAMBIARE IL NUMERO CON UNO REALISTICO!!!
  descrizione varchar(200),
  CONSTRAINT pk_id_parte PRIMARY KEY (id_parte)
);

CREATE TABLE Servizio(
  nome varchar(30) NOT NULL,
  status char(1) DEFAULT '0',
  ore_stimate float(20) DEFAULT 0.0,
  ore_impiegate float(20) DEFAULT 0.0,
  completato bool DEFAULT 0,
  id_servizio varchar(10) NOT NULL AUTO_INCREMENT,
  CONSTRAINT pk_id_servizio PRIMARY KEY (id_servizio)
);
