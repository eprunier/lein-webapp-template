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
  "Process account creation.
   if success : retuns 'ok'
   if error : returns a message to be displayed to the user"
  [request]
  ;; TODO : process sign up and return "ok" if success
  "ok")

(defn- login-page
  "Render the login page."
  [request]
  (wrap-layout
   "Log in"
   (stencil/render-file
    "{{sanitized}}/view/templates/login"
    {})))

(defn- auth
  "Initialise session with dummy data."
  [request]
  ;; TODO : replace with the authentication process
  (session/set-user! {:login "admin"
                      :type :admin}))

(defn- login
  "Process user login with username/password."
  [request]
  (auth request)
  "ok")

(defn- reset-pass-page
  "Render the reset password page."
  [request]
  (wrap-layout
   "Reset password"
   (stencil/render-file
    "{{sanitized}}/view/templates/reset-pass"
    {})))

(defn- reset-pass
  "Reset the user password.
   if success : retuns 'ok'
   if error : returns a message to be displayed to the user"
  [request]
  "ok")

(defn- check-session
  "Check session and returns 'valid' if it is."
  [request]
  (when (authenticated?)
    "valid"))

(defn- logout
  "Process user logout."
  [request]
  (session/logout)
  (response/redirect (wrap-context-root "/")))

(defroutes auth-routes
  (GET "/signup" request (signup-page request))
  (POST "/signup" request (signup request))
  (GET "/login" request (login-page request))
  (POST "/login" request (login request))
  (GET "/reset-pass" request (reset-pass-page request))
  (POST "/reset-pass" request (reset-pass request))
  (GET "/check-session" request (check-session request))
  (GET "/logout" request (logout request)))
