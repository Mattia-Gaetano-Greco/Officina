-- TODO:
-- sono arrivato ad altezza di SHOP (compreso)
-- aggiungere nell'E/R la cardinalità della relazione ADMIN - SHOP (1:1)

-- CONSIDERAZIONI AGGIUNTIVE:
-- TemplateIspezione(id_shop) non può essere null, poichè siccome viene creato da un utente appartenente ad uno shop viene passato in automatico l'id dello shop
-- la maggior parte dei parametri all'interno delle query sono 'NOT NULL' poichè vengono inseriti in una form che richiede che tutti i parametri vengano inseriti
-- Admin(id_shop) non può essere null poiché l'admin viene creato dopo la creazione dello shop a cui fa riferimento

CREATE TABLE TemplateTask(
    nome varchar(50) NOT NULL,
    id_templ_task int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id_templ_task)
);

CREATE TABLE Shop(
  nome varchar(50) NOT NULL,
  id_shop int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_shop)
);

CREATE TABLE Admin(
    nome varchar(50) NOT NULL,
    cognome varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    id_admin int NOT NULL AUTO_INCREMENT,
    id_shop int NOT NULL,
    PRIMARY KEY (id_admin),
    FOREIGN KEY(id_shop) REFERENCES Shop(id_shop)
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

-- NON VERIFICATA!!!

CREATE TABLE Parte(
  nome varchar(30) NOT NULL,
  stato char(1) DEFAULT '0',
  id_parte varchar(10) NOT NULL, -- CAMBIARE IL NUMERO CON UNO REALISTICO!!!
  descrizione varchar(200),
  PRIMARY KEY (id_parte)
);

CREATE TABLE Servizio(
  nome varchar(30) NOT NULL,
  status char(1) DEFAULT '0',
  ore_stimate float(20) DEFAULT 0.0,
  ore_impiegate float(20) DEFAULT 0.0,
  completato bool DEFAULT 0,
  id_servizio int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id_servizio)
);
