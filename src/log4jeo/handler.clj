(ns log4jeo.handler
  (:use compojure.core
        log4jeo.views
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/sample-normal" [] (gen-samp-hist-png nil nil nil))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
