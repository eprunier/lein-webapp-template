(ns {{name}}.view.private.profile
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :as common]))

(defn- page-body []
  [:div
   [:h1 "Profile"]])

(defn- render-page [request]
  (if (common/authenticated?)
    (common/wrap-layout "Profile"
                        (page-body))
    (response/redirect "/")))

(defroutes profile-routes
  (GET "/profile" request (render-page request)))