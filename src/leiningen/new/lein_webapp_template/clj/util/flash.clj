(ns {{name}}.util.flash
  (:refer-clojure :exclude [get])
  (:require [{{name}}.middleware.session :as session-manager]))

(defn put!
  "Put key/value in flash"
  [k v]
  (session-manager/flash-put! k v))

(defn get
  "Retrieve a flash value"
  [k]
  (session-manager/flash-get k))
