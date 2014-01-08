(ns {{name}}.server
  (:require [ring.adapter.jetty :as jetty]
            [{{name}}.app :as app])
  (:gen-class))

(defn start [system]
  (println "Starting server...")
  (let [server (jetty/run-jetty (var app/site-handler)
                                {:host (:host system) 
                                 :port (:port system) 
                                 :join? false})]
    (println "Server started")
    (println (str "You can view the site at http://" (:host system) ":" (:port system)))
    server))

(defn stop [instance]
  (when instance
    (.stop instance))
  (println "Server stopped"))
