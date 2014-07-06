(ns log4jeo.test.convert_test
  (:use clojure.test
      log4jeo.convert)
)

(deftest can-convert-127-0-0-1-to-ipv6 []
  (is (= "2002:7f00:0001::/48" (ipv4-to-ipv6 "127.0.0.1")))
)

(deftest can-convert-192-0-2-5-to-ipv6 []
  (is (= "2002:c000:0205::/48" (ipv4-to-ipv6 "192.0.2.5")))
)

(deftest can-convert-188-31-255-146-to-ipv6 []
  (is (= "2002:bc1f:ff92::/48" (ipv4-to-ipv6 "188.31.255.146")))
)
