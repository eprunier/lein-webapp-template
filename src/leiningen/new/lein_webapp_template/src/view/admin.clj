(ns {{name}}.view.admin
  (:require [compojure.core :refer [defroutes GET]]
            [stencil.core :as stencil]
            [{{name}}.service.security :refer [restricted admin?]]
            [{{name}}.view.common :refer [wrap-layout]]))

(defn- page-body []
  (stencil/render-file
   "{{sanitized}}/view/templates/admin"
   {}))

(defn- render-page [request]
  (wrap-layout "Admin"
               (page-body)))

(defroutes admin-routes
  (GET "/admin" request (restricted admin? render-page request)))
