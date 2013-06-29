(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.1.6"]
                 [compojure "1.1.3"]
                 [stencil "0.3.2"]
                 [clj-http "0.7.3"]
                 [cheshire "5.2.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler {{name}}.app/site-handler}
  :main {{name}}.server)
