(ns log4jeo.convert)

(defn ipv4-to-ipv6 [ipv4]
  (def i (map #(Integer/parseInt %)
             (clojure.string/split ipv4 #"\.")))
  (if (not= 4 (count i)) (throw (NumberFormatException. (str "Expected 4 octets in IPv4 address not " (count i)))))
  (str "::ffff:" (nth i 0) "." (nth i 1) "." (nth i 2) "." (nth i 3)))


(def filepath "test/fixtures/apache_log_head")
(def log (slurp filepath))
(def ip-address-regex
 #"(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])")
(def return-ip (re-find ip-address-regex log))
(def timestamp-regex #"[ ]\[(.+)\][ ]")
(def return-timestamp (first (re-find timestamp-regex log)))











