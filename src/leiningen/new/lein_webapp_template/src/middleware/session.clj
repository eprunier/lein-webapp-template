(ns {{name}}.middleware.session
  (:refer-clojure :exclude [get remove]))

(def ^{:dynamic true :private true} *session*)
(def ^{:dynamic true :private true} *flash*)

;;
;; Session wrapper
;;

(defn wrap-session
  "Store session into a var for easy access."
  [handler]
  (fn [request]
    (binding [*session* (atom {})
              *flash* (atom {})]
      (reset! *session* (:session request))
      (reset! *flash* (:flash request))
      (let [response (handler request)]
        (-> response
            (assoc :session @*session*)
            (assoc :flash @*flash*))))))

;; 
;; Session management
;;

(defn- put-to-target!
  "Put key/value into target"
  [target k v]
  (swap! target assoc k v))

(defn put!
  "Add or update key/value for the current session"
  [k v]
  (put-to-target! *session* k v))

(defn get
  "Get the value associated to a key for the current session"
  [k]
  (@*session* k))

(defn remove
  "Remove key/value from current session."
  [k]
  (swap! *session* dissoc k))

(defn clear
  "Clear the current session"
  []
  (reset! *session* {}))

(defn set-user! 
  "Set the current user."
  [user]
  (put! :user user))

(defn current-user
  "Retrieve the current user."
  []
  (get :user))

;;
;; Flash management
;;

(defn flash-put!
  "Add or update key/value flash"
  [k v]
  (put-to-target! *flash* k v))

(defn flash-get
  "Get the value associated to a key in the flash and remove this key/value"
  [k]
  (let [v (@*flash* k)]
    (swap! *flash* dissoc k)
    v))
