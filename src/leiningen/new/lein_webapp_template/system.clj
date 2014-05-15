(ns {{name}}
  (:require [{{name}}.server :as server]))

(defn system
  "Gives the default parameters for the system to run."
  []
  {:host "localhost"
   :port 8080
   :server nil})

(defn start
  "Start the system."
  [system]
  (let [server (server/start system)]
    (assoc system :server server)))

(defn stop
  "Stop the system."
  [system]
  (-> system
      :server
      server/stop))

(defn- set-config
  "Set a custom system parameter."
  [system k v]
  (if v
    (assoc system k v)
    system))

(defn -main
  "Launch server with optional parameters (host, port)."
  [& {:as args}]
  (let [{:keys [host port]} (clojure.walk/keywordize-keys args)]
    (-> (system)
        (set-config :host host)
        (set-config :port (if (string? port)
                            (Integer/parseInt port)
                            port))
        start)))
