(ns maps-example.server
  (:require
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as response])
  (:use [hiccup.core]
           [compojure.core]))

(defn view-layout [& content]
  (html
      [:head
           [:meta {:http-equiv "Content-type"  :name "viewport"
                        :content "text/html; charset=utf-8"}]
           [:title "Maps Example"]
           [:link {:rel "stylesheet" :href "css/normalize.css"}]
           [:link {:rel "stylesheet" :href "css/foundation.min.css"}]
           [:link {:rel "stylesheet" :href "css/foundation.css"}]
           [:link {:rel "stylesheet" :href "css/application.css"}]
           [:link {:rel "stylesheet" :href "//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"}]
           [:script {:src "js/vendor/custom.modernizr.js" :type "text/javascript"}]
           [:script {:src "http://codeorigin.jquery.com/jquery-2.0.3.min.js" :type "text/javascript"}]
           [:script {:src "https://maps.googleapis.com/maps/api/js?v=3.exp" :type "text/javascript"}]
      ]
      [:body content]))

(defn view-content []
  (view-layout
       [:div {:class "small-12 columns" :id "map"} ]
       [:script {:src "map.js" :type "text/javascript"}]
       [:script {:type "text/javascript"} "maps_example.googlemap.create_map();" ]
       ))

(defroutes main-routes
  (GET "/" []
       (view-content))
      (route/resources "/"))

(def app (handler/site main-routes))
