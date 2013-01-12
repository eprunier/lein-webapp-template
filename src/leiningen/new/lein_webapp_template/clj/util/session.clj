(ns {{name}}.util.session
  (:require [{{name}}.middleware.context :as context]))

(defn set-user! [user]
  (context/session-put! :user {:login "admin"
                               :type :admin}))

(defn current-user
  "Retrieve current user"
  []
  (context/session-get :user))

(defn logout
  "Reset session"
  []
  (context/session-clear))