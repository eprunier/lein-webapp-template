(ns leiningen.new.lein-webapp-template
  (:refer-clojure :exclude [read])
  (:import [java.io ByteArrayOutputStream])
  (:use [clojure.java.io :as io]
        [leiningen.new.templates :only [renderer sanitize year ->files]]))

(def ^{:private true :const true} template-name "lein-webapp-template")

(def render (renderer template-name))

(defn get-bytes 
  "Read resource as byte array. Usefull for binary resources like images."
  [resource-path]
  (let [path (str "leiningen/new/" (sanitize template-name) "/" resource-path)]
    (with-open [input (-> (Thread/currentThread)
                          .getContextClassLoader
                          (.getResourceAsStream path))
                output (ByteArrayOutputStream.)]
      (io/copy input output)
      (.toByteArray output))))

(defn read 
  "Read the content of a resource"
  ([resource-path]
     (get-bytes resource-path))
  ([resource-path data]
     (render resource-path data)))

(defn lein-webapp-template
  "Create a new webapp project based on Compojure, Stencil, Bootstrap and jQuery"
  [name]
  (let [data {:name name
              :sanitized (sanitize name)
              :year (year)}]
    (println "Generating the webapp project" (str name "..."))
    (->files data
             [".gitignore" (read ".gitignore" data)]
             ["project.clj" (read "project.clj" data)]
             ["README.md" (read "README.md" data)]
             ["src/{{sanitized}}/server.clj" (read "clj/server.clj" data)]
             ["src/{{sanitized}}/app.clj" (read "clj/app.clj" data)]
             ["src/{{sanitized}}/middleware/session.clj" (read "clj/middleware/session.clj" data)]
             ["src/{{sanitized}}/middleware/context.clj" (read "clj/middleware/context.clj" data)]
             ["src/{{sanitized}}/util/session.clj" (read "clj/util/session.clj" data)]
             ["src/{{sanitized}}/util/flash.clj" (read "clj/util/flash.clj" data)]
             "src/{{sanitized}}/model/"
             ["src/{{sanitized}}/view/about.clj" (read "clj/view/about.clj" data)]
             ["src/{{sanitized}}/view/auth.clj" (read "clj/view/auth.clj" data)]
             ["src/{{sanitized}}/view/common.clj" (read "clj/view/common.clj" data)]
             ["src/{{sanitized}}/view/home.clj" (read "clj/view/home.clj" data)]
             ["src/{{sanitized}}/view/admin.clj" (read "clj/view/admin.clj" data)]
             ["src/{{sanitized}}/view/profile.clj" (read "clj/view/profile.clj" data)]
             ["src/{{sanitized}}/view/templates/layout.mustache" (read "clj/view/templates/layout.mustache")]
             ["src/{{sanitized}}/view/templates/about.html" (read "clj/view/templates/about.html" data)]
             ["src/{{sanitized}}/view/templates/admin.mustache" (read "clj/view/templates/admin.mustache")]
             ["src/{{sanitized}}/view/templates/auth.mustache" (read "clj/view/templates/auth.mustache")]
             ["src/{{sanitized}}/view/templates/home.mustache" (read "clj/view/templates/home.mustache")]
             ["src/{{sanitized}}/view/templates/profile.mustache" (read "clj/view/templates/profile.mustache")]
             ["resources/public/css/bootstrap.min.css" (read "resources/public/css/bootstrap.min.css")]
             ["resources/public/css/bootstrap-responsive.min.css" (read "resources/public/css/bootstrap-responsive.min.css")]
             ["resources/public/css/jquery-ui.min.css" (read "resources/public/css/jquery-ui.min.css")]
             ["resources/public/css/main.css" (read "resources/public/css/main.css")]
             ["resources/public/img/glyphicons-halflings.png" (read "resources/public/img/glyphicons-halflings.png")]
             ["resources/public/img/glyphicons-halflings-white.png" (read "resources/public/img/glyphicons-halflings-white.png")]
             ["resources/public/js/bootstrap.min.js" (read "resources/public/js/bootstrap.min.js")]
             ["resources/public/js/jquery.min.js" (read "resources/public/js/jquery.min.js")]
             ["resources/public/js/jquery.ui.core.min.js" (read "resources/public/js/jquery.ui.core.min.js")])
    (println "Project" name "successfully generated")))
