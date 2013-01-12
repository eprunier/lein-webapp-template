(ns {{name}}.view.private.admin
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :as common]))

(defn- page-body []
  [:div
   [:h1 "Admin"]])

(defn- render-page [request]
  (if (common/admin?)
    (common/wrap-layout "Admin"
                        (page-body))
    (response/redirect "/")))

(defroutes admin-routes
  (GET "/admin" request (render-page request)))