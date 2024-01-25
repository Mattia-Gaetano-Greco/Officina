CREATE TABLE IF NOT EXISTS TemplateTask(
  nome varchar(50) NOT NULL,
  id_templ_task int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_templ_task)
);

CREATE TABLE IF NOT EXISTS Admin(
  nome varchar(50) NOT NULL,
  cognome varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  numero_telefono varchar(17) NOT NULL,
  password varchar(255) NOT NULL,
  id_admin int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_admin)
);

CREATE TABLE IF NOT EXISTS Shop(
  nome varchar(50) NOT NULL,
  id_shop int NOT NULL AUTO_INCREMENT,
  id_admin int,
  PRIMARY KEY (id_shop),
  FOREIGN KEY (id_admin) REFERENCES Admin(id_admin)
);

CREATE TABLE IF NOT EXISTS TemplateIspezione(
  nome varchar(50) NOT NULL,
  id_shop int NOT NULL,
  id_templ_ispezione int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_templ_ispezione),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop)
);

CREATE TABLE IF NOT EXISTS TemplateTask_TemplateIspezione(
  id_templ_ispezione int NOT NULL,
  id_templ_task int NOT NULL,
  FOREIGN KEY (id_templ_ispezione) REFERENCES TemplateIspezione(id_templ_ispezione),
  FOREIGN KEY (id_templ_task) REFERENCES TemplateTask(id_templ_task),
  PRIMARY KEY (id_templ_ispezione, id_templ_task)
);

CREATE TABLE IF NOT EXISTS Kanban(
  nome varchar(30) NOT NULL,
  posizione int NOT NULL,
  id_kanban int NOT NULL AUTO_INCREMENT,
  id_shop int NOT NULL,
  PRIMARY KEY (id_kanban),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop)
);

CREATE TABLE IF NOT EXISTS Cliente(
  nome varchar(50) NOT NULL,
  cognome varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  numero_telefono varchar(17) NOT NULL,
  password varchar(255) NOT NULL,
  id_cliente int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE IF NOT EXISTS Veicolo(
  modello varchar(50) NOT NULL,
  marca varchar(50) NOT NULL,
  anno_costruzione int NOT NULL,
  targa varchar(7) NOT NULL, -- funziona per unione europea e alcuni stati US
  num_telaio varchar(17) NOT NULL, -- convenzione internazionale
  PRIMARY KEY (targa, num_telaio)
);

CREATE TABLE IF NOT EXISTS Dipendente(
  nome varchar(50) NOT NULL,
  cognome varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  numero_telefono varchar(17) NOT NULL,
  password varchar(255) NOT NULL,
  id_dipendente int NOT NULL AUTO_INCREMENT,
  id_shop int,
  PRIMARY KEY(id_dipendente),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop)
);

CREATE TABLE IF NOT EXISTS Ordine(
  appuntamento_fissato DATETIME,
  commento_cliente varchar(500),
  data_creazione DATETIME,
  data_scadenza DATETIME,
  titolo varchar(100) NOT NULL,
  autorizzato bool NOT NULL DEFAULT 0,
  raccomandazione varchar(500),
  pagamento_effettuato bool NOT NULL DEFAULT 0,
  ricavo float,
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

CREATE TABLE IF NOT EXISTS NotaOrdine(
  testo varchar(500) NOT NULL,
  id_nota_ordine int NOT NULL AUTO_INCREMENT,
  id_ordine int NOT NULL,
  PRIMARY KEY (id_nota_ordine),
  FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine)
);

CREATE TABLE IF NOT EXISTS Ispezione(
  nome varchar(50) NOT NULL,
  id_ispezione int NOT NULL AUTO_INCREMENT,
  id_ordine int NOT NULL,
  PRIMARY KEY (id_ispezione),
  FOREIGN KEY (id_ordine) REFERENCES Ordine(id_ordine)
);

CREATE TABLE IF NOT EXISTS Task(
  nome varchar(50) NOT NULL,
  stato_pezzo enum('scarso','medio','buono') NOT NULL,
  completato bool NOT NULL DEFAULT 0,
  id_task int NOT NULL AUTO_INCREMENT,
  id_ispezione int NOT NULL,
  PRIMARY KEY (id_task),
  FOREIGN KEY (id_ispezione) REFERENCES Ispezione(id_ispezione)
);

CREATE TABLE IF NOT EXISTS NotaTask(
  testo varchar(500) NOT NULL,
  id_nota_ordine int NOT NULL AUTO_INCREMENT,
  id_task int NOT NULL,
  PRIMARY KEY (id_nota_ordine),
  FOREIGN KEY (id_task) REFERENCES Task(id_task)
);

CREATE TABLE IF NOT EXISTS Servizio(
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

CREATE TABLE IF NOT EXISTS Parte(
  nome varchar(30) NOT NULL,
  stato enum('scarso', 'medio', 'buono') NOT NULL,
  descrizione varchar(200),
  costo_acquisto float NOT NULL DEFAULT 0.0,
  id_parte varchar(20) NOT NULL,
  id_shop int NOT NULL,
  id_servizio int,
  PRIMARY KEY (id_parte),
  FOREIGN KEY (id_shop) REFERENCES Shop(id_shop),
  FOREIGN KEY (id_servizio) REFERENCES Servizio(id_servizio)
);
