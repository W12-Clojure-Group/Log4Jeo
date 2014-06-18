(ns log4jeo.handler
  (:use compojure.core
        log4jeo.views
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/user/:id" [id]
       (str "<h1>Hello user " id "</h1>"))
  
  (GET "/" [] "Hello World")
  (GET "/sample-normal" [] (gen-samp-hist-png nil nil nil))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
