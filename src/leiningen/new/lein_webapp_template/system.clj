(ns {{name}}
  (:require [{{name}}.server :as server]))

(defn system
  []
  {:port "8080"
   :server nil})

(defn start
  [system]
  (let [server (server/-main (:port system))]
    (assoc system :server server)))

(defn stop
  [system]
  (-> system
      :server
      server/stop))
