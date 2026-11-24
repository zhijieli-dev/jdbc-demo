# JDBC Demo

Progetto Java di esempio che mostra come collegarsi a un database relazionale tramite JDBC e gestire i dati dei dipendenti con il pattern DAO.

## Funzionalità

- Visualizzare tutti i dipendenti
- Cercare un dipendente tramite ID
- Inserire un dipendente
- Aggiornare lo stipendio
- Eliminare un dipendente
- Gestire le connessioni con il connection pool Druid
- Eseguire operazioni transazionali con commit e rollback

## Tecnologie

- Java
- JDBC
- Maven
- Druid
- Lombok
- JUnit
- MySQL

## Configurazione

Crea il file `src/main/resources/db.properties` con i parametri di connessione al tuo database. Per esempio:

```properties
driverClassName=...
url=...
username=...
password=...
