(ns log4jeo.test.display_test
  (:use clojure.test
        log4jeo.display)
)

(deftest load-file-test []

  (is (= "<h1>hello there</h1>" (load-view "hello-there.html")))
)