(ns log4jeo.ingest)

;;; test setup - delete
(def filepath "/Users/lewisa29/Projects/Log4Jeo/test/fixtures/apache_log_head")
;;;

(defn read-apache-log [filepath] (slurp filepath))
(def apache-log (read-apache-log filepath))
(def ip-address-regex #"\b(?:\d{1,3}\.){3}\d{1,3}\b")
(defn return-ip [log] (re-find ip-address-regex log))

(def timestamp-regex #"[ ]\[(.+)\][ ]")
(defn return-timestamp [log] (clojure.string/trim (first (re-find timestamp-regex log))))

(defn get-log-data-line [path-to-log-file line-number]
  (nth (slurp path-to-log-file) line-number))

