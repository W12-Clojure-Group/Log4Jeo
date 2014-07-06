(ns log4jeo.ingest
  (:require [clj-time.format :as tf]))

(defn get-date [log-data]
  (def regex-pattern #"(\b\d{2}\/\w{3}\/\d{4}\:\d{2}\:\d{2}\:\d{2} \+\d{4}\b)")
  (def groups (re-find regex-pattern log-data))
  (def date-time (tf/parse (tf/formatter "dd/MMM/YYYY:HH:mm:ss Z") (nth groups 1)))
  (tf/unparse (tf/formatter "YYYY-MM-dd'T'HH:mm:ss.000'Z'") date-time)
)

(defn get-ipv4-address [log-data]
  (def regex-pattern #"(\b(\d{1,3}\.){3}\d{1,3}\b)")
  (def groups (re-find regex-pattern log-data))
  (str (nth groups 1))
)

(defn get-log-data-line [path-to-log-file line-number]
  (nth (slurp path-to-log-file) line-number)
)

