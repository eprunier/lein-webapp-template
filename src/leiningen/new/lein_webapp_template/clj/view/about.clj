(ns {{name}}.view.about
  (:require [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :as common]))

(defn- page-body [request]
  [:div
   [:h1 "About"]])

(defn- render-page [request]
  (common/wrap-layout "About"
                      (page-body request)))

(defroutes about-routes
  (GET "/about" request (render-page request)))

