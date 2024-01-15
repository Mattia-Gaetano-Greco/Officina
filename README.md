# Officina
Gestionale per autofficine, gestione ordini riparazioni (clone di Shopmonkey)

## Target del software
Autofficine di piccole / medie dimensioni che si occupano esclusivamente di autoriparazioni e/o servizio gommista, con uno o più dipendenti, con o senza rifornitori ufficiali

## Problemi che il software si pone l'obiettivo di risolvere
- Passare da salvare gli ordini di riparazione su carta a salvarli in digitale, in modo da archiviarli, ordinarli e modificarli digitalmente
- Ottenere bilanci sui guadagni di un dato periodo
- Ottenere un elenco completo dei propri clienti, degli ordini commissionati da ciascuno e il veicolo interessato dall'ordine
- Osservare la produttività dei singoli dipendenti e dell'autofficina
- Suddividere gli ordini in servizi e ispezioni, con ogni ispezione divisibile in sottotask, e tenere traccia dei compiti già svolti e quelli da svolgere; tenere traccia del loro stato e del tempo impiegato per il loro completamento; 
- Tenere traccia dei pezzi in magazzino con facilità
- Permettere ai clienti di visualizzare lo storico dei propri ordini e gli ordini attivi in maniera facile e veloce

## Lista funzionalità
- Dipendente:
  - Aggiungi / visualizza / aggiorna / rimuovi ordini con note, servizi, ispezioni, note e task delle singole ispezioni correlate ad essi; parti dal magazzino; kanban; clienti; veicoli; _Template ispezione_; _Template task_
  - Visualizza lo storico delle officine in cui ha lavorato e degli ordini svolti in ciascuna officina
  - Ottenere lo storico degli ordini di un cliente o di un veicolo
- Admin:
  - Tutti i privilegi di Dipendente
  - Rinomina lo shop
  - Aggiungi / visualizza / aggiorna / rimuovi dipendenti dello shop
- Cliente:
  - Visualizza lo storico degli ordini e quelli in corso con le relative informazioni (veicolo associato, commenti propri e dell'officina, appuntamenti...)

### Considerazioni aggiuntive sulle funzionalità:
- Il software offre la possibilità anche ai dipendenti, e non unicamente agli admin, di inserire numerosi dati anche sensibili e di estrema importanza (ad esempio i clienti) all'interno del database per una questione puramente produttiva, seppur vada a discapito della sicurezza: se ogni volta che il dipendente deve aggiungere al database un dato ha bisogno dell'intervento dell'admin, la produttività dell'officina scende e il software diventa d'intralcio più che di sostegno.
- Quando vengono aggiunti i dati al database vengono effettuati numerosi controlli come ad esempio la verifica della rilevanza dei dati o che non vengano aggiunti dei duplicati, in modo da rendere la base di dati il più uniforme possibile.
- Gli account admin vengono creati dal possessore del software tramite un processo non automatizzato, che prevede l'inserimento manuale all'interno del database dei dati dell'admin stesso: questa è una scelta di design atta a favorire la sicurezza del software (se il possessore del software avesse un account, e un malintenzionato dovesse entrare in possesso delle sue credenziali, il malintenzionato stesso avrebbe pieno controllo del software).

## Diagramma E/R

### Considerazioni aggiuntive:
- `Ordine` non è direttamente collegato a `Shop` poichè lo `Shop` è ottenibile dal `Kanbak` a cui è assegnato
- Ogni `Cliente` può essere associato anche a 0 `Ordine` (ad esempio nel caso in cui è appena stato creato)
- Ogni `Ispezione` può essere associato anche 0 `Task` (ad esempio caso in cui è appena stata creata)
- Ogni `TemplateIspezione` può essere associato anche 0 `Task` (ad esempio caso in cui è stata appena creata)
- Ogni `TemplateTask` può essere associato a 0 o più `TemplateIspezione` (0 ad esempio nel caso in cui è stato appena creato, più ad esempio nel caso in cui due ispezioni hanno entrambe "ispezione freni")
- Ogni `Servizio` può essere associato anche a 0 `Parte` (ad esempio nel caso in cui il servizio sia una semplice calibrazione, come gonfiaggio gomme o calibrazione convergenza / campanatura ruote)
- Ogni `Shop` può essere associato anche a 0 `Admin` (ad esempio nel caso in cui lo shop è stato appena creato, oppure si vuole cambiare admin dello shop)
- Ogni `Admin` può essere associato anche a 0 o più `Shop` (0 ad esempio nel caso in cui l'admin è stato appena creato o un altro admin ha preso in gestione la sua officina, più nel caso in cui gestisca più officine)
- Ogni `Dipendente` può essere associato anche a 0 o più `Shop` (ad esempio nel caso in cui sia stato appena creato, o appena licenziato, più nel caso in cui lavori per più officine)

![E/R](resources/SchemaER.png)

## Schema relazionale
**TemplateTask**(nome, **id_templ_task**)

**Admin**(nome, cognome, password **id_admin**)

**Shop**(nome, **id_shop**, **Admin_id_admin**)

**TemplateIspezione**(nome, **Shop_id_shop**, **id_templ_ispezione**)

**TemplateTask_TemplateIspezione**(**TemplateIspezione_id_templ_ispezione**, **TemplateTask_id_templ_task**)

**Kanban**(nome, posizione, **id_kanban**, **Shop_id_shop**)

**Cliente**(telefono, nome, cognome, email, password, **id_cliente**)

**Veicolo**(modello, marca, anno_costruzione, **targa**, **num_telaio**)

**Dipendente**(nome, cognome, email, numero_telefono, password, **id_dipendente**, **Shop_id_shop**);

**Ordine**(appuntamento_fissato, commento_cliente, data_creazione, ispezione_completata, titolo, autorizzato, raccomandazione, pagamento_effettuato, ricavo,**id_ordine**, **Kanban_id_kanban**, **Cliente_id_cliente**, **Dipendente_id_dipendente**, **Veicolo_targa**, **Veicolo_num_telaio**)

**NotaOrdine**(testo, **id_nota_ordine**, **Ordine_id_ordine**)

**Ispezione**(nome, **id_ispezione**, **Ordine_id_ordine**)

**Task**(nome, stato_pezzo, completato, **Task_id_task**, **Ispezione_id_ispezione**)

**NotaTask**(testo, **id_nota_task**, **Task_id_task**)

**Servizio**(nome, status, ore_stimate, ore_impiegate, completato, **id_servizio**, **Ordine_id_ordine**)

**Parte**(nome, stato, descrizione, costo_acquisto, **id_parte**, **Shop_id_shop**, **Servizio_id_servizio**)

## Come eseguire il programma
Clonare la repository sulla propria macchina, ed eseguire il comando `./mvnw spring-boot:run` (è richiesta la presenza di una JDK sulla propria macchina)

Come accedere ad H2 Console (per visualizzare / modificare il database): https://www.baeldung.com/spring-boot-h2-database (in `JDBC Url` inserire il valore dell'attributo `spring.datasource.url` contenuto in `main/resources/application.properties`)

## FAQ
Q: Perchè non è prevista una gestione più accurata del magazzino, con ad esempio l'acquisto di pezzi, la loro posizione nel magazino, valore dei pezzi eccetera? E la funzionalità che permette ai dipendenti di timbrare all'entrata e all'uscita della giornata lavorativa?

A: Queste funzioni non sono previste a causa dell'elevata complessità che introdurrebbe all'interno del progetto.
