(ns {{name}}.middleware.context)

(def ^{:dynamic true :private true} *context-root*)

(defn wrap-context-root
  "Wrap application root context"
  [handler]
  (fn [request]
    (binding [*context-root* (or (:context request) "")]
      (handler request))))

(defn get-context-root []
  *context-root*)

