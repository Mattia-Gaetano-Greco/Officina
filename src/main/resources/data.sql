--INSERT INTO Admin(nome, cognome, password) VALUES ('admin', 'admin', 'admin');
--INSERT INTO TemplateTask(nome) VALUES ('Controllo freni'), ('Controllo motore'), ('Controllo sterzo'), ('Controllo sospensioni'), ('Controllo ruote'), ('Controllo carrozzeria'), ('Controllo impianto elettrico'), ('Controllo impianto di raffreddamento'), ('Controllo impianto di scarico'), ('Controllo impianto di alimentazione'), ('Controllo impianto di illuminazione'), ('Controllo impianto di climatizzazione'), ('Controllo impianto di sicurezza'), ('Controllo impianto di trasmissione'), ('Controllo impianto di lubrificazione'), ('Controllo impianto di avviamento'), ('Controllo impianto di frenatura'), ('Controllo impianto di sterzo'), ('Controllo impianto di sospensione');

-- INSERT INTO TemplateIspezione(nome, id_shop) VALUES ('Ispezione di prova', 1);
-- INSERT INTO Kanban(nome, posizione, id_shop) VALUES ('Kanban di prova', 0, 1);
--INSERT INTO Cliente(telefono, nome, cognome, email, password) VALUES ('+39 1234567890', 'Cliente', 'di prova', 'test@email.com', 'password');
-- INSERT INTO Veicolo(modello, marca, anno_costruzione, targa, num_telaio) VALUES ('Modello di prova', 'Marca di prova', 2020, 'AA123BB', '12345678901234567');
-- INSERT INTO Dipendente(nome, cognome, email, numero_telefono, password, id_shop) VALUES ('Dipendente', 'di prova', 'test@email.com', '+39 1234567890', 'password', 1);
-- INSERT INTO Ordine(appuntamento_fissato, commento_cliente, data_creazione, data_scadenza, titolo, autorizzato, raccomandazione, pagamento_effettuato, ricavo, id_kanban, id_cliente, id_dipendente, targa, num_telaio) VALUES ('2020-01-01 00:00:00', 'Commento di prova', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'Titolo di prova', 0, 'Raccomandazione di prova', 0, 0.0, 1, 1, 1, 'AA123BB', '12345678901234567');
-- INSERT INTO NotaOrdine(testo, id_ordine) VALUES ('Nota di prova', 1);
-- INSERT INTO Ispezione(nome, id_ordine) VALUES ('Ispezione di prova', 1);
-- INSERT INTO Task(nome, stato_pezzo, completato, id_ispezione) VALUES ('Task di prova', 'buono', 0, 1);
-- INSERT INTO NotaTask(testo, id_task) VALUES ('Nota di prova', 1);
-- INSERT INTO Servizio(nome, status, ore_stimate, ore_impiegate, completato, id_ordine) VALUES ('Servizio di prova', 'buono', 0.0, 0.0, 0, 1);
-- INSERT INTO Parte(nome, stato, descrizione, costo_acquisto, id_parte, id_shop, id_servizio) VALUES ('Parte di prova', 'buono', 'Descrizione di prova', 0.0, 'AA123BB', 1, 1);

-- INSERT INTO Admin(id_admin, nome, cognome, email, numero_telefono, password) VALUES (14234, 'david', '123', 'david@gmail.com', '2131313111', '$2a$10$MYAHu0zJni9Y4SZGUN48zuI9/uiiZakDUQenpTx5AdavbDkufgKWW'); -- john2020
-- INSERT INTO Admin(id_admin, nome, cognome, email, numero_telefono, password) VALUES (513251, 'john', '2020' ,'john@yahoo.com', '1234567890', '$2a$10$9pmuW3N6WzN/qApVA09c5.4ovhf0426f.TF2GJPg2Qsa0lBL67x4S'); -- david123
--/*
INSERT INTO Shop(nome) VALUES ('Officina di prova');
INSERT INTO Admin(nome, cognome, email, numero_telefono, password) VALUES ('david', '123', 'david@gmail.com', '2131313111', '$2a$10$MYAHu0zJni9Y4SZGUN48zuI9/uiiZakDUQenpTx5AdavbDkufgKWW'); -- john2020
INSERT INTO Admin(nome, cognome, email, numero_telefono, password) VALUES ('john', '2020' ,'john@yahoo.com', '1234567890', '$2a$10$9pmuW3N6WzN/qApVA09c5.4ovhf0426f.TF2GJPg2Qsa0lBL67x4S'); -- david123
INSERT INTO Dipendente(nome, cognome, email, numero_telefono, password, id_shop) VALUES ('nam', 'codejava', 'nam@codejava.net', '1234567654', '$2a$10$5lGfg3fC3ZMP8uJ35/fJiOUz2aaPZ.ATmCkwYu3tu2RG.Wm2O/RUK', 1); -- nam2022
INSERT INTO Dipendente(nome, cognome, email, numero_telefono, password, id_shop) VALUES ('ravi', '2121', 'ravi@gmail.com', '1234547890', '$2a$10$RlQNI8Gu2f85HdAavVdFEO/C./NUDEaUPzoq40bSLC9cOUjCst9p2', 1); -- ravi2121*/