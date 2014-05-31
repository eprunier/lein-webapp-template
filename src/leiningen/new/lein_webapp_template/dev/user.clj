(ns user
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer (javadoc)]
   [clojure.pprint :refer (pprint)]
   [clojure.reflect :refer (reflect)]
   [clojure.repl :refer (apropos dir doc find-doc pst source)]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer (refresh refresh-all)]
   [{{name}}.server :as server]))

;; Map containing the application under development.
(defonce system {})

(defn init
  "Initializes system properties"
  []
  (System/setProperty "server.host" "localhost")
  (System/setProperty "server.port" "8080"))

(defn start
  "Starts the system running, updates the Var #'system."
  []
  (alter-var-root #'system
                  #(merge % {:server (server/start)})))

(defn stop
  "Stops the system if it is currently running, updates the Var
  #'system."
  []
  (alter-var-root #'system
                  (fn [{s :server :as system}]
                    (when s 
                      (do
                        (server/stop s)
                        (dissoc system :server))))))

(defn go
  "Initializes and starts the system running."
  []
  (init)
  (start)
  :ready)

(defn reset
  "Stops the system, reloads modified source files, and restarts it."
  []
  (stop)
  (refresh :after 'user/go))
