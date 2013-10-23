(ns {{name}}.service.db)

;; A simple in-memory database for testing purpose.
(def database (atom {}))

(defn get-user
  "Returns the user corresponding to the given ID."
  [id]
  (get @database id))

(defn add-user
  "Add a new user to database."
  [{:keys [id] :as user}]
  (when (and id
           (not (get-user id)))
    (swap! database assoc (:id user) user)))
