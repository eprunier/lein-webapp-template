(ns {{name}}.service.db)

;; A simple in-memory database for testing purpose.
(def database (atom {}))

(defn get-user
  "Returns the user corresponding to the given username."
  [username]
  (get @database username))

(defn add-user
  "Add a new user to database."
  [{:keys [username] :as user}]
  (when (and username
           (not (get-user username)))
    (swap! database assoc (:username user) user)))
