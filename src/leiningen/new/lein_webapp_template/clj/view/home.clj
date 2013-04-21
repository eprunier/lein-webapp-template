(ns {{name}}.view.home
  (:require [compojure.core :refer [defroutes GET]]
            [stencil.core :as stencil]
            [{{name}}.view.common :refer [wrap-layout]]))

(defn- page-body []
  (stencil/render-file
   "{{sanitized}}/view/templates/home"
   {}))

(defn- render-page [request]
  (wrap-layout "Home"
               (page-body)))

(defroutes home-routes
  (GET "/" request (render-page request)))
