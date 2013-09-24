(ns {{name}}
  (:require [{{name}}.server :as server]))

(defn system
  []
  {:host "localhost"
   :port "8080"
   :server nil})

(defn start
  [system]
  (let [server (server/-main "host" (:host system) "port" (:port system))]
    (assoc system :server server)))

(defn stop
  [system]
  (-> system
      :server
      server/stop))
