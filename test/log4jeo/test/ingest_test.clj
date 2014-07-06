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
