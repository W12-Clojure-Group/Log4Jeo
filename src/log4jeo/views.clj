(ns log4jeo.views
  (:use [hiccup core page]
        [incanter core stats charts]
        [compojure response])
  (:import (java.io ByteArrayOutputStream
                    ByteArrayInputStream)))


(defn to-in-stream [chart]
	(let [out-stream (ByteArrayOutputStream.)]
		(save chart out-stream)
        (ByteArrayInputStream.
        	(.toByteArray out-stream))))

(defn gen-city-count-bar-chart [data]
  (let [chart (bar-chart (keys data) (vals data))
        in-stream (to-in-stream chart)]
    {:status 200
     :headers {"Content-Type" "image/png"}
     :body in-stream}
  ))

(defn gen-sample-city-count []
    (gen-city-count-bar-chart {:london 199 :moskow 22 :paris 212}))


(defn gen-samp-hist-png [size_s mean_s sd_s]
  (let [size (if (empty? size_s) 1000 (Integer. size_s))
          m (if (empty? mean_s) 1 (Integer. mean_s))
          s (if (empty? sd_s) 1 (Integer. sd_s))
          samp (sample-normal size
                    :mean m
                    :sd s)
          chart (histogram
                 samp
                 :title "Normal Sample"
                 :x-label (str "sample-size = " size
                               ", mean = " m
                               ", sd = " s))
          in-stream (to-in-stream chart)]
      {:status 200
       :headers {"Content-Type" "image/png"}
       :body in-stream})
)
