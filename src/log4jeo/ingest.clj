(ns log4jeo.ingest
  (:require [log4jeo.db :as db])
  ( :import [log4jeo.db geo-ip-data]))

(defn get-log-data-line [path-to-log-file line-number]
  (nth (slurp path-to-log-file) line-number))

(def geo-city-zip-url "http://geolite.maxmind.com/download/geoip/database/GeoLite2-City-CSV.zip")
(def geo-country-zip-url "http://geolite.maxmind.com/download/geoip/database/GeoLite2-Country-CSV.zip")

(def geo-ip-file "resources/data/max_mind/GeoLite2-City-Blocks-just-the-headers-plus-3-lines.csv")
;(defn import-city-data-file []
;  (def path-to-file "data/GeoLite2-City-Blocks.csv")
;  (def file (slurp path-to-file))
;  (def )
;  (def geoip-data-pattern #"(\b\d{2}\/\w{3}\/\d{4}\:\d{2}\:\d{2}\:\d{2} \+\d{4}\b)")
;
;)

;(defn get-geoip-data-headers [file]
;  (clojure.string/split (first file) #","))



(defn convert-geo-line-to-data-record [line]
  (def splitted (clojure.string/split line #","))
  (geo-ip-data. 
    (nth splitted 3)
    (nth splitted 0)
    (nth splitted 6)
    (nth splitted 7))
  )

(defn print-data-line [^geo-ip-data data-map]
  (println (str "ip: " (:ipv6 data-map)))
  (println (str "lat: " (:latitude data-map)))
  (println (str "long: " (:longitude data-map)))
  (println (str "city: " (:city-id data-map)))
  )

(defn ingest-geoip-data []
  (try
    (with-open [rdr (clojure.java.io/reader geo-ip-file)]

      ;This line pulls out the headers
      ;TODO Should check headers match expected values
      (def headers (line-seq rdr))

      (doseq [line (line-seq rdr)]
        (db/add-geo-ip-data (convert-geo-line-to-data-record line))
        ))
    (str "done")
    (catch Exception e
      (println "caught " e)))
  )
