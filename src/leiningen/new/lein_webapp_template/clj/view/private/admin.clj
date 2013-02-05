(ns {{name}}.view.private.admin
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :refer [restricted admin? wrap-layout]]))

(defn- page-body []
  [:div
   [:h1 "Admin"]])

(defn- render-page [request]
  (wrap-layout "Admin"
               (page-body)))

(defroutes admin-routes
  (GET "/admin" request (restricted admin? render-page request)))