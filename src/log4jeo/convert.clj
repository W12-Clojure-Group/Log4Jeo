(ns log4jeo.convert)

(defn ipv4-to-ipv6 [ipv4]
  (def h (map #(format "%02x" %)
           (map #(Integer/parseInt %)
             (clojure.string/split ipv4 #"\."))))
  (str "2002:" (nth h 0) (nth h 1) ":" (nth h 2) (nth h 3) "::/48")
)
