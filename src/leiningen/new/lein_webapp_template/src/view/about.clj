(ns {{name}}.view.about
  (:require [clojure.java.io :as io]
            [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :refer [wrap-layout]]))

(defn- page-body [request]
  (slurp (io/resource "{{sanitized}}/view/templates/about.html")))

(defn- render-page [request]
  (wrap-layout "About"
               (page-body request)))

(defroutes about-routes
  (GET "/about" request (render-page request)))

