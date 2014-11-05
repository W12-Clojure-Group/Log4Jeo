(ns log4jeo.convert
  (:require [clojure.string :as string] [clojure.data.json :as json])
)

(defn ipv4-to-ipv6 [ipv4]
  (def i (map #(Integer/parseInt %)
             (clojure.string/split ipv4 #"\.")))
  (if (not= 4 (count i)) (throw (NumberFormatException. (str "Expected 4 octets in IPv4 address not " (count i)))))
  (str "::ffff:" (nth i 0) "." (nth i 1) "." (nth i 2) "." (nth i 3)))


(defn read-apache-log [filepath]
  (let [log (slurp filepath)
        ip-address-regex #"[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}" 
        ip-address (re-find ip-address-regex log)
        timestamp-regex #"\[(.+)\]"
        timestamp (string/trim (first (re-find timestamp-regex log)))]
    (json/write-str {:ip ip-address :timestamp timestamp})))

