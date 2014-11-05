(ns log4jeo.handler
  (:use compojure.core
        compojure.route
        log4jeo.views
        log4jeo.convert
        log4jeo.ingest
        log4jeo.display
        log4jeo.charts
        log4jeo.db
        [hiccup.middleware :only (wrap-base-url)]
        [ring.middleware.content-type :only (wrap-content-type)]
        [ring.middleware.file :only (wrap-file)]
        [ring.middleware.file-info :only (wrap-file-info)]
        [ring.util.response :only (redirect)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/user/:id" [id]
    (str "<h1>Hello user " id "</h1>"))

  (GET "/" [] (load-view "index.html"))
  (GET "/sample-normal" [] (gen-samp-hist-png nil nil nil))
  (GET "/convert/ipv4to6/:ipv4" [ipv4] (ipv4-to-ipv6 ipv4))
  (GET "/convert/read-apache-log" [] (read-apache-log "test/fixtures/access_log"))
  (GET "/sample-city-count" [] (gen-sample-city-count))
  (GET "/access-log" [] (redirect "/data/access-log-data.json"))
  (GET "/current-dir" [] (System/getProperty "user.dir"))
  (GET "/slurp" [] (slurp "test/fixtures/access_log"))
  (GET "/read-log" [] (read-log "test/fixtures/access_log"))
  (GET "/charts" [] (charts-home-page))
  (GET "/ingest/geo-ip-data" [] (ingest-geoip-data geo-ip-file))
  (GET "/maps-example" [] (gen-maps-example))
  (GET "/load-db" [] (make-db))
  (route/resources "/")
  (route/not-found "Could not find it within Log4Jeo!"))

(def app
  (-> (handler/site app-routes)
    (wrap-file "resources")
    (wrap-file-info)
    (wrap-content-type)))
