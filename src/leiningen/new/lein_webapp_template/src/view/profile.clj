(ns {{name}}.view.profile
  (:require [compojure.core :refer [defroutes GET]]
            [stencil.core :as stencil]
            [{{name}}.service.security :refer [restricted authenticated?]]
            [{{name}}.view.common :refer [wrap-layout]]))

(defn- page-body []
  (stencil/render-file
   "{{sanitized}}/view/templates/profile"
   {}))

(defn- render-page [request]
  (wrap-layout "Profile"
               (page-body)))

(defroutes profile-routes
  (GET "/profile" request (restricted authenticated? render-page request)))
