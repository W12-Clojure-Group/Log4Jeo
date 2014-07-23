(defn report-marker
  "Build a Google Maps marker and give it an info window."
  [r]
  (let [lat (:lat r)
        lng (:lng r)
        pos (google.maps.LatLng. lat lng)
        marker (google.maps.Marker. (clj->js {"position" pos "title" (:description r)}))
        window (info-window-for-report r marker)]
    (google.maps.event.addListener marker "click" (fn [] (.open window *map* marker)))
    marker))
