(ns {{name}}.view.common
  (:require [hiccup.core :as hiccup]
            [hiccup.page :as page]
            [hiccup.element :as element]
            [{{name}}.util.session :as session]))

;;; HTML utils
(defmacro defhtml [name params & content]
  `(defn ~name ~params
     (hiccup/html ~@content)))

(defhtml dropdown-button [items & [{label :label id :id icon :icon class :class :as options}]]
  [:span {:class "btn-group" :id id}
   [:a {:class "btn dropdown-toggle" :data-toggle "dropdown" :href (str "#" id)}
    (when icon
      [:i {:class icon}])
    (str "&nbsp;" label "&nbsp;")
    [:span {:class "caret"}]]
   (element/unordered-list {:class "dropdown-menu"} items)])


;;; User utils
(defn authenticated?
  "Sample authentication function. Test if current user is not null."
  []
  (session/current-user))

(defn admin?
  "Sample authorization function. Test if current user it admin."
  []
  (let [user (session/current-user)]
    (= :admin (:type user))))


;;; Page utils
(defn- profil-menu
  "Display the dropdown menu related to the current user"
  []
  [:div {:class "pull-right right-menu"}
   (dropdown-button
    [(element/link-to "/profile" "Profile")
     (element/link-to "/logout" "Logout")]
    {:label (:login (session/current-user))
     :icon "icon-user"})])

(defn- login-button
  "Display authentication action"
  []
  (element/link-to {:class "btn pull-right"} "/login" "Log in"))


;;; Resources utils
(defn- add-css
  "Add useful css"
  []
  (page/include-css "/css/jquery-ui.min.css"
                    "/css/bootstrap.min.css"
                    "/css/{{name}}.css"
                    "/css/bootstrap-responsive.min.css"))

(defn- add-js
  "Add useful JS"
  []
  (page/include-js "/js/jquery.min.js"
                   "/js/jquery.ui.core.min.js"
                   "/js/jquery.ui.datepicker.min.js"
                   "/js/bootstrap.min.js"))


;;; Layout
(defn wrap-layout
  "Define pages layout"
  [title & body]
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
                               (when (admin?)
                                 (element/link-to "/admin" "Administration"))
                               (element/link-to "/about" "About")])
        (if (authenticated?)
          (profil-menu)
          (login-button))]]]]
    [:div#content {:class "container"}
     body]
    (add-js)]))