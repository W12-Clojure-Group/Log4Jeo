(ns log4jeo.convert)

(defn ipv4-to-ipv6 [ipv4]
  (def i (map #(Integer/parseInt %)
             (clojure.string/split ipv4 #"\.")))
  (if (not= 4 (count i)) (throw (NumberFormatException. (str "Expected 4 octets in IPv4 address not " (count i)))))
  (str "::ffff:" (nth i 0) "." (nth i 1) "." (nth i 2) "." (nth i 3) )
)
