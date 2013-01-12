(ns {{name}}.view.auth
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET]]
            [{{name}}.util.session :as session]
            [{{name}}.view.common :as common]))

(defn init-test-data
  "Initialise session with dummy data"
  []
  (session/set-user! {:login "admin"
                      :type :admin}))

(defn- login [request]
  (init-test-data)
  (response/redirect "/"))

(defn- logout [request]
  (session/logout)
  (response/redirect "/"))

(defroutes auth-routes
  (GET "/login" request (login request))
  (GET "/logout" request (logout request)))
