(defproject maps-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2030"]
                 [compojure "1.1.5"]
                 [jayq "2.4.0"]
                 [hiccup "1.0.4"]
                 ]
  :source-paths ["src/clj"]
  :plugins [[lein-cljsbuild "0.3.4"]
            [lein-ring "0.8.7"]]

  :cljsbuild {
     :builds {
              :main  {
                 :source-paths ["src/cljs"],
                 :compiler {
                    :output-to "resources/public/map.js",
                    :externs ["google_maps_api_v3.js" "jquery-1.9.js"],
                    :optimizations :whitespace
                    }
                }
             }
    }

  :main maps-example.server
  :ring {:handler maps-example.server/app}

  )
