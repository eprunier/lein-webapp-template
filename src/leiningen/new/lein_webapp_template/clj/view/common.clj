(ns {{name}}.view.common
  (:require [hiccup.core :as hiccup]
            [hiccup.page :as page]
            [hiccup.element :as element]))

(defmacro wrap-html [& content]
  `(hiccup/html ~@content))

(defn- add-css []
  (page/include-css "/css/jquery-ui.min.css"
                    "/css/bootstrap.min.css"
                    "/css/{{name}}.css"
                    "/css/bootstrap-responsive.min.css"))

(defn- add-js []
  (page/include-js "/js/jquery.min.js"
                   "/js/jquery.ui.core.min.js"
                   "/js/jquery.ui.datepicker.min.js"
                   "/js/bootstrap.min.js"))

(defn wrap-layout [title & body]
  (page/html5
   [:head
    [:title title]
    (add-css)]
   [:body
    [:div {:class "navbar navbar-inverse navbar-fixed-top"}
     [:div.navbar-inner
      [:div.container
       [:a {:class "btn btn-navbar" :data-toggle "collapse" :data-target ".nav-collapse"}
        [:span.icon-bar]
        [:span.icon-bar]
        [:span.icon-bar]]
       [:a.brand "{{name}}"]
       [:div {:class "nav-collapse collapse"}
        (element/ordered-list {:class "nav"}
                              [(element/link-to "/" "Home")
                               (element/link-to "/about" "About")])]]]]
    [:div#content {:class "container"}
     body]
    (add-js)]))