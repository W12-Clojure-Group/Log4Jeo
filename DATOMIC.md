# Datomic help

## Instructions to add a record to Datomic

### Possible useful Datomic clean up

* kill -9 `ps -ef | grep datomic |grep -v grep | cut -c11-16`

### On command line: in the project root dir

* lein datomic start
* lein datomic initialize
* lein repl

### in REPL

* (use '[datomic.api :only [q db] :as d])
* (def uri "datomic:free://localhost:4334/log4jeo")
* (d/delete-database uri)
* (d/create-database uri)
* (def conn (d/connect uri))
* (def schema-tx (read-string (slurp "resources/schemas/geo-ip-schema.edn")))
* @(d/transact conn schema-tx)
* @(d/transact conn [{:db/id #db/id[:db.part/user] :geo-ip/ip "::ffff::10.10.10.1" :geo-ip/latitude "31.123456" :geo-ip/longitude "50.987654"}])

