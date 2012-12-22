(ns {{name}}.app
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]))

;; Define here required connections (database, etc.)


;; Load views
(require '[{{name}}.view.home :refer [home-routes]]
         '[{{name}}.view.about :refer [about-routes]])


;; Site definitions
(defroutes site-handler
  (-> (routes home-routes
              about-routes
              (route/resources "/")
              (route/not-found "<h1>Page not found.</h1>"))
      (handler/site)))
