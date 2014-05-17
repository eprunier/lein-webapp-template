(ns {{name}}.middleware.session)

(declare ^:dynamic *session*)
(declare ^:dynamic *flash*)

(defn wrap-session
  "Store session into a Clojure map"
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

(defn- put!
  "Put key/value into target"
  [target k v]
  (swap! target #(assoc % k v)))

(defn session-put!
  "Add or update key/value for the current session"
  [k v]
  (put! *session* k v))

(defn session-get
  "Get the value associated to a key for the current session"
  [k]
  (@*session* k))

(defn session-clear
  "Clear the current session"
  []
  (reset! *session* {}))

(defn flash-put!
  "Add or update key/value flash"
  [k v]
  (put! *flash* k v))

(defn flash-get
  "Get the value associated to a key in the flash and remove this key/value"
  [k]
  (let [v (@*flash* k)]
    (swap! *flash* (fn [old-flash]
                     (dissoc old-flash k)))
    v))
