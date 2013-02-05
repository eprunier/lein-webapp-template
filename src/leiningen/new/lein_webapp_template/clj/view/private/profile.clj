(ns {{name}}.view.private.profile
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :refer [restricted authenticated? wrap-layout]]))

(defn- page-body []
  [:div
   [:h1 "Profile"]])

(defn- render-page [request]
  (wrap-layout "Profile"
                      (page-body)))

(defroutes profile-routes
  (GET "/profile" request (restricted authenticated? render-page request)))