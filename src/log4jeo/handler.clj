(ns log4jeo.handler
  (:use compojure.core
        log4jeo.views
        log4jeo.convert
        log4jeo.ingest
        log4jeo.display
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/user/:id" [id]
       (str "<h1>Hello user " id "</h1>"))
  
  (GET "/" [] (load-view "index.html"))
  (GET "/sample-normal" [] (gen-samp-hist-png nil nil nil))
  (GET "/convert/ipv4to6/:ipv4" [ipv4] (ipv4-to-ipv6 ipv4))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
