(ns log4jeo.display)

(defn load-view [view]
  (slurp (str "view/" view))
)
