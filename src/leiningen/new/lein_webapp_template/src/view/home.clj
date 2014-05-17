(ns {{name}}.view.home
    (:require [compojure.core :refer [defroutes GET]]
              [stencil.core :as stencil]
              [{{name}}.view.common :refer [wrap-layout authenticated?]]))

(defn- render-home [request]
  (stencil/render-file
   "{{sanitized}}/view/templates/home"
   {}))

(defn- render-index [request]
  (stencil/render-file
   "{{sanitized}}/view/templates/index"
   {}))

(defn- render-page [request]
  (wrap-layout "Home"
               (if (authenticated?)
                 (render-home request)
                 (render-index request))))

(defroutes home-routes
  (GET "/" request (render-page request)))
