(ns {{name}}.view.home
  (:require [compojure.core :refer [defroutes GET]]
            [{{name}}.view.common :as common]))

(defn- page-body []
  [:div
   [:h1 "Home"]
   [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In a urna augue, sit amet molestie lectus. Cras nec est est. Aenean interdum nisi non purus mattis eget sagittis nunc sagittis. Morbi laoreet nisl id felis tristique sed ullamcorper mauris ornare. Fusce elementum rutrum purus, ac porta ante porta quis. Etiam euismod euismod nisl vel bibendum. In faucibus iaculis suscipit. Cras ut porta mauris. Vestibulum quis nisi quam, quis auctor velit. Phasellus pretium, risus id cursus porttitor, nunc erat scelerisque velit, ut volutpat sem nunc non nunc. Phasellus vehicula commodo nibh, nec sodales diam feugiat eu. Proin vulputate nibh ac est cursus adipiscing. Aenean fermentum nibh et lorem rhoncus feugiat. In malesuada metus in magna bibendum porttitor vitae vitae nisl."]
   [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In a urna augue, sit amet molestie lectus. Cras nec est est. Aenean interdum nisi non purus mattis eget sagittis nunc sagittis. Morbi laoreet nisl id felis tristique sed ullamcorper mauris ornare. Fusce elementum rutrum purus, ac porta ante porta quis. Etiam euismod euismod nisl vel bibendum. In faucibus iaculis suscipit. Cras ut porta mauris. Vestibulum quis nisi quam, quis auctor velit. Phasellus pretium, risus id cursus porttitor, nunc erat scelerisque velit, ut volutpat sem nunc non nunc. Phasellus vehicula commodo nibh, nec sodales diam feugiat eu. Proin vulputate nibh ac est cursus adipiscing. Aenean fermentum nibh et lorem rhoncus feugiat. In malesuada metus in magna bibendum porttitor vitae vitae nisl."]])

(defn- render-page [request]
  (common/wrap-layout "Home"
                      (page-body)))

(defroutes home-routes
  (GET "/" request (render-page request)))
