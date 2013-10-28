(ns {{name}}.app
  (:require [clojure.core.cache :as cache]
            [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [stencil.loader :as stencil]
            [{{name}}.middleware.session :as session-manager]
            [{{name}}.middleware.context :as context-manager]))

;;; Initialization
;; Add required code here (database, etc.)
(stencil/set-cache (cache/ttl-cache-factory {}))
;;(stencil/set-cache (cache/lru-cache-factory {}))


;;; Load public routes
(require '[{{name}}.view.home :refer [home-routes]]
         '[{{name}}.view.about :refer [about-routes]])

;;; Load registration and authentication routes
(require '[{{name}}.view.auth :refer [auth-routes]])

;;; Load generic routes
(require '[{{name}}.view.profile :refer [profile-routes]]
         '[{{name}}.view.settings :refer [settings-routes]]
         '[{{name}}.view.admin :refer [admin-routes]])

;;; Load website routes
;; Add your routes here


;; Ring handler definition
(defroutes site-handler
  (-> (routes home-routes
              about-routes
              auth-routes
              profile-routes
              settings-routes
              admin-routes
              (route/resources "/")
              (route/not-found "<h1>Page not found.</h1>"))
      (session-manager/wrap-session)
      (context-manager/wrap-context-root)
      (handler/site)))
