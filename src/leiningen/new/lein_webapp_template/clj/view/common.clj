(ns {{name}}.view.common
  (:require [ring.util.response :as response]
            [hiccup.core :as hiccup]
            [hiccup.page :as page]
            [hiccup.element :as element]
            [{{name}}.util.session :as session]
            [{{name}}.util.context :as context]))

;;; Context utils
(defn wrap-context
  "Add web context to the path of URI"
  [path]
  (context/with-context path))


;;; HTML utils
(defmacro defhtml [name params & content]
  `(defn ~name ~params
     (hiccup/html ~@content)))

(defhtml dropdown-button [items & [{label :label id :id icon :icon class :class :as options}]]
  [:span {:class "btn-group" :id id}
   [:a {:class "btn btn-inverse dropdown-toggle" :data-toggle "dropdown" :href (str "#" id)}
    (when icon
      [:i {:class icon}])
    (str "&nbsp;" label "&nbsp;")
    [:span {:class "caret"}]]
   (element/unordered-list {:class "dropdown-menu"} items)])


;;; User utils
(defn restricted
  "Function for restricted part of the Web site. 
   Takes a predicate function and the handler to execute if predicate is true."
  [predicate handler & args]
  (if (predicate)
     (apply handler args)
     (response/redirect (wrap-context "/"))))

(defn authenticated?
  "Sample authentication function. Test if current user is not null."
  []
  (not (nil? (session/current-user))))

(defn admin?
  "Sample authorization function. Test if current user it admin."
  []
  (if-let [user (session/current-user)]
    (= :admin (:type user))))


;;; Page utils
(defn- profil-menu
  "Display the dropdown menu related to the current user"
  []
  [:div {:class "pull-right right-menu"}
   (dropdown-button
    [(-> "/profile"
         (wrap-context)
         (element/link-to "Profile"))
     (-> "/logout"
         (wrap-context)
         (element/link-to "Logout"))]
    {:label (:login (session/current-user))
     :icon "icon-user icon-white"})])

(defn- login-button
  "Display authentication action"
  []
  (element/link-to {:class "btn btn-inverse pull-right"}
                   (wrap-context "/login")
                   "Log in"))


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
                              [(element/link-to (wrap-context "/") "Home")
                               (when (admin?)
                                 (element/link-to (wrap-context "/admin") "Administration"))
                               (element/link-to (wrap-context "/about") "About")])
        (if (authenticated?)
          (profil-menu)
          (login-button))]]]]
    [:div#content {:class "container"}
     body]
    (add-js)]))