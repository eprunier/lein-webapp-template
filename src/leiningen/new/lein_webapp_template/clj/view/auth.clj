(ns {{name}}.view.auth
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST]]
            [stencil.core :as stencil]
            [{{name}}.util.session :as session]
            [{{name}}.view.common :refer [wrap-context wrap-layout]]))

(defn init-test-data
  "Initialise session with dummy data"
  []
  (session/set-user! {:login "admin"
                      :type :admin}))

(defn- login-page-body [request]
  (stencil/render-file
   "{{name}}/view/templates/auth"
   {:form-action (wrap-context "/login")}))

(defn- login-page [request]
  (wrap-layout "Log in"
               (login-page-body request)))

(defn- login [request]
  (init-test-data)
  (response/redirect (wrap-context "/")))

(defn- logout [request]
  (session/logout)
  (response/redirect (wrap-context "/")))

(defroutes auth-routes
  (GET "/login" request (login-page request))
  (POST "/login" request (login request))
  (GET "/logout" request (logout request)))
