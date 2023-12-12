-- CONSIDERAZIONI AGGIUNTIVE:
-- TemplateIspezione(id_shop) non può essere null, poichè siccome viene creato da un utente appartenente ad uno shop viene passato in automatico l'id dello shop
-- la maggior parte dei parametri all'interno delle query sono 'NOT NULL' poichè vengono inseriti in una form che richiede che tutti i parametri vengano inseriti
-- Admin(id_shop) non può essere null poiché l'admin viene creato dopo la creazione dello shop a cui fa riferimento
-- Cliente(telefono) ha 15 cifre (la lunghezza massima di un numero telefonico internazionalmente) + 2 cifre per il prefisso internazionale

CREATE TABLE TemplateTask(
  nome varchar(50) NOT NULL,
  id_templ_task int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_templ_task)
);

CREATE TABLE Admin(
  nome varchar(50) NOT NULL,
  cognome varchar(50) NOT NULL,
  password varchar(255) NOT NULL,
  id_admin int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_admin)
);

CREATE TABLE Shop(
  nome varchar(50) NOT NULL,
  id_shop int NOT NULL AUTO_INCREMENT,
  id_admin int,
  PRIMARY KEY (id_shop),
  FOREIGN KEY (id_admin) REFERENCES Admin(id_admin)
);

CREATE TABLE TemplateIspezione(
  nome varchar(50) NOT NULL,
  id_shop int NOT NULL,
  id_templ_ispezione int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_templ_ispezione),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop)
);

CREATE TABLE TemplateTask_TemplateIspezione(
  id_templ_ispezione int NOT NULL,
  id_templ_task int NOT NULL,
  FOREIGN KEY (id_templ_ispezione) REFERENCES TemplateIspezione(id_templ_ispezione),
  FOREIGN KEY (id_templ_task) REFERENCES TemplateTask(id_templ_task),
  PRIMARY KEY (id_templ_ispezione, id_templ_task)
);

CREATE TABLE Kanban(
  nome varchar(30) NOT NULL,
  posizione int NOT NULL,
  id_kanban int NOT NULL AUTO_INCREMENT,
  id_shop int NOT NULL,
  PRIMARY KEY (id_kanban),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop)
);

CREATE TABLE Cliente(
  telefono varchar(17) NOT NULL,
  nome varchar(50) NOT NULL,
  cognome varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  id_cliente int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE Veicolo(
  modello varchar(50) NOT NULL,
  marca varchar(50) NOT NULL,
  anno_costruzione int NOT NULL,
  targa varchar(7) NOT NULL, -- funziona per unione europea e alcuni stati US
  num_telaio varchar(17) NOT NULL, -- convenzione internazionale
  PRIMARY KEY (targa, num_telaio)
);

CREATE TABLE Dipendente(
  nome varchar(50) NOT NULL,
  cognome varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  numero_telefono varchar(17) NOT NULL,
  password varchar(255) NOT NULL,
  id_dipendente int NOT NULL AUTO_INCREMENT, -- modificare nell'E/R da id_utente a id_dipendente
  id_shop int,
  PRIMARY KEY(id_dipendente),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop)
);

CREATE TABLE Ordine(
  appuntamento_fissato DATETIME,
  commento_cliente varchar(500),
  data_creazione DATETIME,
  ispezione_completata bool NOT NULL DEFAULT 0,
  titolo varchar(100) NOT NULL,
  autorizzato bool NOT NULL DEFAULT 0,
  raccomandazione varchar(500),
  id_ordine int NOT NULL AUTO_INCREMENT,
  id_kanban int NOT NULL,
  id_cliente int NOT NULL,
  id_dipendente int NOT NULL,
  targa varchar(7) NOT NULL,
  num_telaio varchar(17) NOT NULL,
  PRIMARY KEY (id_ordine),
  FOREIGN KEY (id_kanban) REFERENCES Kanban(id_kanban),
  FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
  FOREIGN KEY (id_dipendente) REFERENCES Dipendente(id_dipendente),
  FOREIGN KEY (targa, num_telaio) REFERENCES Veicolo(targa, num_telaio)
);

CREATE TABLE NotaOrdine(
  -- creatore??? controllare lo schema ER
  testo varchar(500) NOT NULL,
  id_nota_ordine int NOT NULL AUTO_INCREMENT,
  id_ordine int NOT NULL,
  PRIMARY KEY (id_nota_ordine),
  FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine)
);

CREATE TABLE Ispezione(
  nome varchar(50) NOT NULL,
  id_ispezione int NOT NULL AUTO_INCREMENT,
  id_ordine int NOT NULL,
  PRIMARY KEY (id_ispezione),
  FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine)
);

CREATE TABLE Task(
  nome varchar(50) NOT NULL,
  stato_pezzo enum('scarso','medio','buono') NOT NULL,
  completato bool NOT NULL DEFAULT 0,
  id_task int NOT NULL AUTO_INCREMENT,
  id_ispezione int NOT NULL,
  PRIMARY KEY (id_task),
  FOREIGN KEY (id_ispezione) REFERENCES Ispezione(id_ispezione)
);

CREATE TABLE NotaTask(
  -- creatore??? controllare lo schema ER
  testo varchar(500) NOT NULL,
  id_nota_ordine int NOT NULL AUTO_INCREMENT,
  id_task int NOT NULL,
  PRIMARY KEY (id_nota_ordine),
  FOREIGN KEY (id_task) REFERENCES Task(id_task)
);

CREATE TABLE Servizio(
  nome varchar(30) NOT NULL,
  status enum('scarso', 'medio', 'buono') NOT NULL,
  ore_stimate float DEFAULT 0.0,
  ore_impiegate float DEFAULT 0.0,
  completato bool DEFAULT 0,
  id_servizio int NOT NULL AUTO_INCREMENT,
  id_ordine int NOT NULL,
  PRIMARY KEY (id_servizio),
  FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine)
);

CREATE TABLE Parte(
  nome varchar(30) NOT NULL,
  stato enum('scarso', 'medio', 'buono') NOT NULL,
  descrizione varchar(200),
  id_parte varchar(20) NOT NULL,
  id_shop int NOT NULL,
  id_servizio int,
  PRIMARY KEY (id_parte),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop),
  FOREIGN KEY (id_servizio) REFERENCES Servizio(id_servizio)
);