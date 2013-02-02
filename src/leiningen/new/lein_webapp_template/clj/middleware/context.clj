(ns {{name}}.middleware.context)

(declare ^:dynamic *session*)

(defn wrap-context
  "Store request, session and flash into a Clojure map"
  [handler]
  (fn [request]
    (binding [*session* (atom {})]
      (when-let [session (get-in request [:session :app-context])]
        (reset! *session* session))
      (let [response (handler request)
            session (:session response)]
        (assoc-in response [:session :app-context] @*session*)))))

(defn session-put!
  "Add or update key/value for the current session"
  [k v]
  (swap! *session* (fn [old-session]
                     (assoc old-session k v))))

(defn session-get
  "Get the value associated to a key for the current session"
  [k]
  (@*session* k))

(defn session-clear
  "Clear the current session"
  []
  (reset! *session* {}))
