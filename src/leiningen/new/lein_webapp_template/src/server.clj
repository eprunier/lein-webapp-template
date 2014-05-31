(ns {{name}}.server
  (:require [ring.adapter.jetty :as jetty]
            [{{name}}.config :refer [config]]
            [{{name}}.app :as app])
  (:gen-class))

(defn start
  []
  (println "Starting server...")
  (let [host (config :server-host :required? true)
        port (config :server-port :required? true)
        server (jetty/run-jetty (var app/site-handler)
                                {:host host
                                 :port (Integer/parseInt port)
                                 :join? false})]
    (println "Server started")
    (println (str "You can view the site at http://" host ":" port))
    server))

(defn stop
  [instance]
  (when instance
    (.stop instance))
  (println "Server stopped"))

(defn -main
  []
  (start))
