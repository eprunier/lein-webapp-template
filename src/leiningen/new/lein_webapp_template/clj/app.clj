(ns {{name}}.app
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [{{name}}.middleware.context :as context-manager]))

;; Initialization
; Add required code here (database, etc.)


;; Load public routes
(require '[{{name}}.view.home :refer [home-routes]]
         '[{{name}}.view.about :refer [about-routes]])

;; Load authentication routes
(require '[{{name}}.view.auth :refer [auth-routes]])

;; Load private routes
(require '[{{name}}.view.private.profile :refer [profile-routes]]
         '[{{name}}.view.private.admin :refer [admin-routes]])


;; Ring handler definition
(defroutes site-handler
  (-> (routes home-routes
              about-routes
              auth-routes
              profile-routes
              admin-routes
              (route/resources "/")
              (route/not-found "<h1>Page not found.</h1>"))
      (context-manager/wrap-context)
      (handler/site)))
