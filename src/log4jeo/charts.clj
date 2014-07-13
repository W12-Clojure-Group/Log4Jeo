(ns log4jeo.charts
  (:use [hiccup core element page]
        [hiccup.middleware :only (wrap-base-url)]
        [incanter core stats charts]
        [compojure response]))


(defn charts-home-page []
         (html5
           [:head
            [:title "Log4Jeo Charts"]]
           [:body
            [:h1 {:id "web-charts"} "Charts"]
            [:ol
             [:li [:a {:href "/data/access-log-data.json"}
                   "Log4Jeo access log charts"]]]
            (include-js "js/script.js")
            (javascript-tag "log4jeo.core.hello('from ClojureScript!');")]))
