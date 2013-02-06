(ns {{name}}.view.auth
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET POST]]
            [{{name}}.util.session :as session]
            [{{name}}.view.common :refer [wrap-context wrap-layout]]))

(defn init-test-data
  "Initialise session with dummy data"
  []
  (session/set-user! {:login "admin"
                      :type :admin}))

(defn- login-page-body [request]
  [:div.form-horizontal.login-form
   [:h2 "Please log in"]
   [:form {:method "post" :action (wrap-context "/login")}
    [:div.control-group
     [:input.input-block-level {:type "text" :name "login" :placeholder "Username or Email"}]]
    [:div.control-group
     [:input.input-block-level {:type "password" :name "password" :placeholder "Password"}]]
    [:div.control-group
     [:label.checkbox
      [:input {:type "checkbox" :value "remember-me"}
       "Remember me"]]]
    [:div.control-group
     [:input.btn.btn-primary.pull-right {:type "submit" :value "Log in"}]]]
   [:div.links
    [:span
     [:a {:href ""} "Register"] " - " [:a {:href ""} "Forgot password?"]]]]
)

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
