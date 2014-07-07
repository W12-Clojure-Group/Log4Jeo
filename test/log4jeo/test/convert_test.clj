(ns log4jeo.test.convert_test
  (:use clojure.test
      log4jeo.convert)
)

(deftest can-convert-127-0-0-1-to-ipv6 []
  (is (= "::ffff:127.0.0.1" (ipv4-to-ipv6 "127.0.0.1")))
)

(deftest can-convert-192-0-2-5-to-ipv6 []
  (is (= "::ffff:192.0.2.5" (ipv4-to-ipv6 "192.0.2.5")))
)

(deftest can-convert-188-31-255-146-to-ipv6 []
  (is (= "::ffff:188.31.255.146" (ipv4-to-ipv6 "188.31.255.146")))
)

(deftest will-not-convert-invalid-ipv4-string-to-ipv6 []
  (is (thrown? NumberFormatException (ipv4-to-ipv6 "188.31.no.146")))
  )

(deftest will-not-convert-ipv4-string-with-too-many-numbers-to-ipv6 []
  (is (thrown-with-msg? Exception #"Expected 4 octets in IPv4 address not 5" (ipv4-to-ipv6 "188.31.233.146.12")))
  )

(deftest will-not-convert-ipv4-string-with-too-few-numbers-to-ipv6 []
  (is (thrown-with-msg? Exception #"Expected 4 octets in IPv4 address not 3" (ipv4-to-ipv6 "188.31.233")))
  )

(deftest will-not-convert-ipv4-string-with-too-few-numbers-to-ipv6 []
  (is (thrown-with-msg? Exception #"Expected 4 octets in IPv4 address not 3" (ipv4-to-ipv6 "188.31.233.")))
  )
