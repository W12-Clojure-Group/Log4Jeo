(defproject log4jeo "0.1.0-SNAPSHOT"
  :description ""
  :url "https://github.com/W12-Clojure-Group/Log4Jeo"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2261"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [incanter "1.5.5"]
                 [clj-time "0.7.0"]
                 [com.datomic/datomic-free "0.9.4752"]
                 ]
  :plugins [[lein-ring "0.8.10"]
            [lein-datomic "0.2.0"]
            [lein-cljsbuild "1.0.3"]]
  :hooks [leiningen.cljsbuild]
  :ring {:handler log4jeo.handler/app}
  :profiles {
      :dev {
        :dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [alembic "0.1.1"]]
        :datomic {
             :config "resources/transactor.properties"
             :db-uri "datomic:free://localhost:4334/log4jeo"}
        }
   }
  :datomic {:schemas ["resources/schemas" ["geo-ip-schema.edn" ]]}
  :cljsbuild {
          :builds {
               :main {
                :source-paths ["src-cljs"],
                :compiler {
                   :pretty-print true,
                   :output-to "resources/script.js",
                   :externs ["google_maps_api_v3.js" "jquery-1.9.js"],
                   :optimizations :whitespace
                   }
                 }
             }
      }
  )
