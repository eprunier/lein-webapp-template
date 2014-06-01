(ns {{name}}.service.security
  (:require [ring.util.response :as response]
            [{{name}}.middleware.context :refer [url]]
            [{{name}}.middleware.session :as session]))

(defn restricted
  "Function for restricted part of the Web site. 
   Takes a predicate function and the handler to execute if predicate is true."
  [predicate handler & args]
  (if (predicate)
    (apply handler args)
    (response/redirect (url "/"))))

(defn authenticated?
  "Sample authentication function. Test if current user is not null."
  []
  (not (nil? (session/current-user))))

(defn admin?
  "Sample authorization function. Test if current user it admin."
  []
  (if-let [user (session/current-user)]
    (= :admin (keyword (:type user)))))
