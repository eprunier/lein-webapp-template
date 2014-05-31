(ns {{name}}.config
  (:require [clojure.string :as str]))

(defn- env
  [k]
  (-> k
      (name)
      (str/replace "-" "_")
      (str/upper-case)
      (System/getenv)))

(defn- prop
  [k]
  (-> k
      (name) 
      (str/replace "-" ".") 
      (System/getProperty)))

(defn config
  [k & {:as opt}]
  (let [v (or (env k)
              (prop k))]
    (if (and (:required? opt)
             (not v))
      (throw (NullPointerException. (str "Undefined configuration for key " k)))
      v)))
