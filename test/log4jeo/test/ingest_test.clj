(ns log4jeo.test.ingest_test
  (:use clojure.test
        log4jeo.ingest)
  )

(def example-log-string "212.58.246.190 - - [01/Mar/2014:08:59:59 +0000] \"GET /images/live/p0/1n/62/p01n62xh.jpg HTTP/1.1\" 200  439836 \"http://www.bbc.com/future/story/20131211-ten-titans-of-transport\" \"Akamai Edge\" -:- deltaorigin.bbc.co.uk 45980 I:814 O:440225 R:\"-\"")

(deftest can-extract-date-from-log-data []
  (is (= "2014-03-01T08:59:59.000Z" (get-date example-log-string) ))
)

(deftest can-extract-ipv4-address-from-log-data []
  (is (= "212.58.246.190" (get-ipv4-address example-log-string)))
)

(deftest contract--headers-from-city-blocks-file-are-correct []
  (def path-to-file "resources/data/max_mind/GeoLite2-City-Blocks-just-the-headers-plus-3-lines.csv")

;  (is (= ("network_start_ip"
;           "network_prefix_length"
;           "geoname_id"
;           "registered_country_geoname_id"
;           "represented_country_geoname_id"
;           "postal_code"
;           "latitude"
;           "longitude"
;           "is_anonymous_proxy"
;           "is_satellite_provider")
  (ingest-geoip-data path-to-file)
;  ))
  )