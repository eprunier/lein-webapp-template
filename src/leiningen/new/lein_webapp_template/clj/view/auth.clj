(ns {{name}}.view.auth
    (:require [ring.util.response :as response]
              [compojure.core :refer [defroutes GET POST]]
              [stencil.core :as stencil]
              [{{name}}.util.session :as session]
              [{{name}}.util.flash :as flash]
              [{{name}}.view.common :refer [wrap-context-root get-context-root wrap-layout]]
              [{{name}}.service.linkedin :as linkedin]))

(def ^:const REDIRECT-TO (str "http://localhost:8080"))

(defn- login-page
  "Render the login page."
  [request]
  (wrap-layout
   "Log in"
   (stencil/render-file
    "{{sanitized}}/view/templates/auth"
    (let [context-root (get-context-root)
          linkedin-state (linkedin/new-state)]
      (session/put! :linkedin-state linkedin-state)
      {:context-root context-root
       :linkedin-url (linkedin/auth-uri linkedin-state)}))))

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

(defn- process-linkedin-error
  []
  (flash/put! :error "Authentication failed")
  (response/redirect (wrap-context-root "/login")))

(defn- process-linkedin-success
  [authorization-code]
  (let [access-token (linkedin/get-access-token authorization-code)
        {:keys [firstName lastName] :as user} (linkedin/fetch-user access-token)]
    (session/put! :linkedin-token access-token)
    (session/set-user! {:login (str firstName " " lastName)
                        :type :user}))
  (response/redirect (wrap-context-root "/")))

(defn- linkedin-login
  "Process LinkedIn authentication."
  [{:keys [params] :as request}]
  (let [{:keys [code state error error_description]} params
        session-state (session/get :linkedin-state)]
    (if (or error
            (and (not (nil? session-state))
                 (not= session-state state)))
      (process-linkedin-error)
      (process-linkedin-success code))))

(defn- twitter-login
  "Process Twitter authentication."
  [request]
  ;; TODO
  )

(defn- facebook-login
  "Process Facebook authentication."
  [request]
  ;; TODO
  )

(defn- logout
  [request]
  (session/logout)
  (response/redirect (wrap-context-root "/")))

(defroutes auth-routes
  (GET "/login" request (login-page request))
  (POST "/login" request (login request))

  (GET "/linkedin" request (linkedin-login request))
  (GET "/twitter" request (twitter-login request))
  (GET "/facebook" request (facebook-login request))
  
  (GET "/logout" request (logout request)))
