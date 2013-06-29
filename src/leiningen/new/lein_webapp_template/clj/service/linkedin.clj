(ns {{name}}.service.linkedin
    (:require [clojure.walk :as walk]
              [clj-http.client :as client]
              [cheshire.core :as json]))

(def ^:const API_KEY "")
(def ^:const API_SECRET "")

(def ^:const LOGIN_URI "http://localhost:8080/linkedin")
(def ^:const STATE_ITEMS "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")

(defn new-state
  []
  (apply str (take 30 (repeatedly #(rand-nth STATE_ITEMS)))))

(defn auth-uri
  [state]
  (str "https://www.linkedin.com/uas/oauth2/authorization?"
       "response_type=code"
       "&client_id=" API_KEY
       "&scope=r_network%20r_fullprofile%20r_emailaddress"
       "&state=" state
       "&redirect_uri=" LOGIN_URI))

(defn get-access-token
  [authorization-code]
  (-> (client/post (str "https://www.linkedin.com/uas/oauth2/accessToken?"
                        "grant_type=authorization_code"
                        "&code=" authorization-code
                        "&redirect_uri=" LOGIN_URI
                        "&client_id=" API_KEY
                        "&client_secret=" API_SECRET))
      :body
      json/parse-string
      (get "access_token")))

(defn fetch-user 
  [access-token]
  (-> (client/get (str "https://api.linkedin.com/v1/people/~"
                       "?oauth2_access_token=" access-token
                       "&format=json"))
      :body
      json/parse-string
      walk/keywordize-keys))
