(ns {{name}}.server
  (:require [ring.adapter.jetty :as jetty]
            [{{name}}.app :as app]))


(defn start []
  (println "Starting server...")
  (let [port 3000
        server (jetty/run-jetty (var app/site-handler)
                                {:port port :join? false})]
    (println "Server started on port" port)
    (println (str "You can view the site at http://localhost:" port))
    server))

(defn stop [instance]
  (.stop instance)
  (println "Server stopped"))
