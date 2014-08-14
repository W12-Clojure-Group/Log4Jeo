(ns log4jeo.db
  (:require [datomic.api :as d :refer [db q]])
  )

(defrecord geo-ip-data [city-id ipv6 latitude longitude])

(def uri "datomic:free://localhost:4334/log4jeo")

(defn make-db []
  (use '[datomic.api :only [q db] :as d])
  (def conn (d/connect uri))
  (d/delete-database uri)
  (d/create-database uri))

(defn add-geo-ip-data [^geo-ip-data data]
  ;  (println (:ipv6 data))
  (def conn (d/connect uri))
  (def schema-tx (read-string (slurp "resources/schemas/geo-ip-schema.edn")))
  (d/transact conn schema-tx)
  (d/transact conn [
                     (:db/id #db/id [:db.part/user]
                             :geo-ip/ip (:ipv6 data)
                             :geo-ip/latitude (:latitude data)
                             :geo-ip/longitude (:longitude data))
                     ])
  )

(defn get-geo-for-ip-address [ipv6-address]
  (use '[datomic.api :only [q db] :as d])
  (def conn (d/connect uri))
  (d/q '[:find ?e ?ip ?latitude ?longitude
         :where [?e :geoip/ip ipv6-address]
         [?e :geoip/laitude ?latitude]
         [?e :geoip/longitude ?longitude]
         ] (d/db (d/connect uri)))
  )
