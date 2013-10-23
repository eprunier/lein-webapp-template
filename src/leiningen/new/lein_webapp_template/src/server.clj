(ns {{name}}.server
  (:require [ring.adapter.jetty :as jetty]
            [{{name}}.app :as app])
  (:gen-class))

(defn -main [& {:as args}]
  (let [{:keys [host port]
         :or {host "localhost" port "8080"}} (clojure.walk/keywordize-keys args)]
    (println "Starting server...")
    (let [server (jetty/run-jetty (var app/site-handler)
                                  {:host host :port (Integer/parseInt port) :join? false})]
      (println "Server started")
      (println (str "You can view the site at http://" host ":" port))
      server)))

(defn stop [instance]
  (when instance
    (.stop instance))
  (println "Server stopped"))
