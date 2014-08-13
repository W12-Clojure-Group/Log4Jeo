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

(defn map-layout [& content]
  (html5
      [:head
           [:meta {:http-equiv "Content-type"
                   :content "text/html; charset=utf-8"}]
           [:title "Maps Example"]
           (include-css "css/normalize.css")
           (include-css "css/foundation.min.css")
           (include-css "css/foundation.css")
           (include-css "css/application.css")
           [:link {:rel "stylesheet" :href "http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"}]
           [:script {:src "js/vendor/custom.modernizr.js" :type "text/javascript"}]
           [:script {:src "http://codeorigin.jquery.com/jquery-2.0.3.min.js" :type "text/javascript"}]
           [:script {:src "https://maps.googleapis.com/maps/api/js?v=3.exp" :type "text/javascript"}]
      ]
      [:body content]
  )
)

(defn gen-maps-example []
  (map-layout
       [:h1 {:class "top-bar"} [:a {:href "/index.html" :class "name"} "Maps Example"]]
       [:div {:class "small-12 columns" :id "map"} ]
       (include-js "script.js")
       [:script {:type "text/javascript"} "log4jeo.googlemap.create_map();" ]
    )
)
