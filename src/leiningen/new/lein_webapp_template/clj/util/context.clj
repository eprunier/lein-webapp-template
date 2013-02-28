(ns {{name}}.util.context
  (:require [{{name}}.middleware.context :as context]))

(defn with-context
  "Return path with root context appended"
  [path]
  (str (context/get-context-root) path))
