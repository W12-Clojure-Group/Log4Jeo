(ns log4jeo.googlemap)

(def lat 51.512770)
(def lon -0.128924)

(declare *map*)

(defn ^:export create-map
  "Create a Google Map element, center it, and assign it to the *map* var."
  []
  (let [map-opts (clj->js {"center" (google.maps.LatLng. lat lon)
                           "zoom" 13
                           "mapTypeId" "roadmap"})]
    (set! *map* (google.maps.Map. (.getElementById js/document "map") map-opts))))
