(ns leiningen.new.lein-webapp-template
  (:use [clojure.java.io :as io]
        [leiningen.new.templates :only [renderer sanitize name-to-path ->files]]))

(def ^{:private true :const true} template-name "lein-webapp-template")

(def render (renderer template-name))

(defn get-bytes [resource-path data]
  (let [path (str "leiningen/new/" (sanitize template-name) "/" resource-path)]
    (with-open [input (-> (Thread/currentThread)
                          .getContextClassLoader
                          (.getResourceAsStream path))
                output (new java.io.ByteArrayOutputStream)]
        (io/copy input output)
        (.toByteArray output))))

(defn lein-webapp-template
  "Create a new webapp project based on Compojure, Hiccup, Bootstrap and jQuery"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (println "Generating the webapp project" (str name "..."))
    (->files data
             [".gitignore" (render ".gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["src/{{sanitized}}/server.clj" (render "clj/server.clj" data)]
             ["src/{{sanitized}}/app.clj" (render "clj/app.clj" data)]
             ["src/{{sanitized}}/middleware/context.clj" (render "clj/middleware/context.clj" data)]
             ["src/{{sanitized}}/util/session.clj" (render "clj/util/session.clj" data)]
             "src/{{sanitized}}/model/"
             ["src/{{sanitized}}/view/about.clj" (render "clj/view/about.clj" data)]
             ["src/{{sanitized}}/view/auth.clj" (render "clj/view/auth.clj" data)]
             ["src/{{sanitized}}/view/common.clj" (render "clj/view/common.clj" data)]
             ["src/{{sanitized}}/view/home.clj" (render "clj/view/home.clj" data)]
             ["src/{{sanitized}}/view/private/admin.clj" (render "clj/view/private/admin.clj" data)]
             ["src/{{sanitized}}/view/private/profile.clj" (render "clj/view/private/profile.clj" data)]
             ["resources/public/css/bootstrap.min.css" (render "resources/public/css/bootstrap.min.css")]
             ["resources/public/css/bootstrap-responsive.min.css" (render "resources/public/css/bootstrap-responsive.min.css")]
             ["resources/public/css/jquery-ui.min.css" (render "resources/public/css/jquery-ui.min.css")]
             ["resources/public/css/{{name}}.css" (render "resources/public/css/quickstart.css")]
             ["resources/public/img/glyphicons-halflings.png" (get-bytes "resources/public/img/glyphicons-halflings.png" data)]
             ["resources/public/img/glyphicons-halflings-white.png" (get-bytes "resources/public/img/glyphicons-halflings-white.png" data)]
             ["resources/public/js/bootstrap.min.js" (render "resources/public/js/bootstrap.min.js")]
             ["resources/public/js/jquery.min.js" (render "resources/public/js/jquery.min.js")]
             ["resources/public/js/jquery.ui.core.min.js" (render "resources/public/js/jquery.ui.core.min.js")])
    (println "Project" name "successfully generated")))
