(ns {{name}}.util.flash
  (:refer-clojure :exclude [get])
  (:require [{{name}}.middleware.session :as session-manager]))

(defn get
  "Retrieve a flash value"
  [k]
  (session-manager/flash-get k))
