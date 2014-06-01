(ns {{name}}.view.common
  (:require [ring.util.response :as response]
            [stencil.core :as stencil]
            [{{name}}.middleware.context :refer [url] :as context]
            [{{name}}.middleware.session :as session]
            [{{name}}.service.security :refer [authenticated? admin?]]))

(defn- base-content
  [title body]
  {:context-root (context/get-context-root)
   :title title
   :body body})

(defn- user-nav-links 
  []
  (when (admin?) 
    [{:link (url "/admin") :label "Administration"}]))

(defn wrap-layout
  "Define pages layout"
  [title body]
  (stencil/render-file
   "{{name}}/view/templates/layout"
   (let [content (base-content title body)
         user (session/current-user)]
     (if (authenticated?)
       (assoc content 
         :authenticated? 
         {:user (:username user)
          :nav-links (user-nav-links)})
       content))))
