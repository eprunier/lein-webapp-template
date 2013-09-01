(ns {{name}}.view.auth
    (:require [ring.util.response :as response]
              [compojure.core :refer [defroutes GET POST]]
              [stencil.core :as stencil]
              [{{name}}.util.session :as session]
              [{{name}}.util.flash :as flash]
              [{{name}}.view.common :refer [wrap-context-root wrap-layout authenticated?]]))

(defn- signup-page
  "Render the signup page."
  [request]
  (wrap-layout
   "Sign up"
   (stencil/render-file
    "{{sanitized}}/view/templates/signup"
    {})))

(defn- signup
  "Process account creation."
  [request]
  (wrap-layout
   "Sign up"
   (stencil/render-file
    "{{sanitized}}/view/templates/signup"
    {:signup-result "Sign up request recorded. Please check you emails to validate your account."})))

(defn- login-page
  "Render the login page."
  [request]
  (wrap-layout
   "Log in"
   (stencil/render-file
    "{{sanitized}}/view/templates/login"
    {})))

(defn- init-test-data
  "Initialise session with dummy data"
  []
  (session/set-user! {:login "admin"
                      :type :admin}))

(defn- login
  "Process login with username/password."
  [request]
  (init-test-data)
  (response/redirect (wrap-context-root "/")))

(defn check-session
  [request]
  (when (authenticated?)
    "active"))

(defn- logout
  [request]
  (session/logout)
  (response/redirect (wrap-context-root "/")))

(defroutes auth-routes
  (GET "/signup" request (signup-page request))
  (POST "/signup" request (signup request))
  (GET "/login" request (login-page request))
  (POST "/login" request (login request))
  (GET "/check-session" request (check-session request))
  (GET "/logout" request (logout request)))
