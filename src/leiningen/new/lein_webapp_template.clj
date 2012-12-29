(ns leiningen.new.lein-webapp-template
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "lein-webapp-template"))

(defn lein-webapp-template
  "Create a new webapp project based on Compojure, Hiccup, Bootstrap and jQuery"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (println "Generating the webapp project named" (str name " ..."))
    (->files data
             [".gitignore" (render ".gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["src/{{sanitized}}/server.clj" (render "clj/server.clj" data)]
             ["src/{{sanitized}}/app.clj" (render "clj/app.clj" data)]
             "src/{{sanitized}}/model/"
             ["src/{{sanitized}}/view/about.clj" (render "clj/view/about.clj" data)]
             ["src/{{sanitized}}/view/common.clj" (render "clj/view/common.clj" data)]
             ["src/{{sanitized}}/view/home.clj" (render "clj/view/home.clj" data)]
             ["resources/public/css/bootstrap.min.css" (render "resources/public/css/bootstrap.min.css")]
             ["resources/public/css/bootstrap-responsive.min.css" (render "resources/public/css/bootstrap-responsive.min.css")]
             ["resources/public/css/jquery-ui.min.css" (render "resources/public/css/jquery-ui.min.css")]
             ["resources/public/css/{{name}}.css" (render "resources/public/css/quickstart.css")]
             "resources/public/img"
             ["resources/public/js/bootstrap.min.js" (render "resources/public/js/bootstrap.min.js")]
             ["resources/public/js/jquery.min.js" (render "resources/public/js/jquery.min.js")]
             ["resources/public/js/jquery.ui.core.min.js" (render "resources/public/js/jquery.ui.core.min.js")])))
