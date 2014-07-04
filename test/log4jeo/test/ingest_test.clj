(ns log4jeo.test.ingest_test
  ;(:require [clj-time.core :as time]
  ;          [clj-time.format :as time-format]
  ;          )
  (:use clojure.test
        log4jeo.ingest)
  )

;(deftest ingest-string
;  (def log-string "212.58.246.190 - - [01/Mar/2014:08:59:59 +0000] \"GET /images/live/p0/1n/62/p01n62xh.jpg HTTP/1.1\" 200  439836 \"http://www.bbc.com/future/story/20131211-ten-titans-of-transport\" \"Akamai Edge\" -:- deltaorigin.bbc.co.uk 45980 I:814 O:440225 R:\"-\"")
;
;  (def expected "212.58.246.190")
;  (def actual (nth (log4jeo.ingest.get-date-from-log-data log-string) 1))
;  (is (= expected actual ))
;  )

(deftest ingest-string []
  (
    ;(def expected "212.58.246.190")
    ;(def actual "212.58.246.190")
    ;(is (= expected actual ))
    (is (= "212.58.246.190" "12.58.246.190" ))
  )
)


;(deftest test-app
;  (testing "ingest a string"
;    (let [response (app (request :get "/"))]
;      (is (= (:status response) 200))
;      (is (= (:body response) "Hello World"))))
;
;  (testing "not-found route"
;    (let [response (app (request :get "/invalid"))]
;      (is (= (:status response) 404)))))
