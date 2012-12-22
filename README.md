# lein-webapp-template

A Leiningen template for Web apps based on Compojure, Hiccup, Boostrap and jQuery.

## Usage

Add lein-ring plugin and this template to your profile (~/.lein/profiles.clj) :
    
    {:user {:plugins [[lein-ring "0.7.5"]
    	   	      [lein-webapp-template/lein-template "1.0.0"]]}}

Then create a new project using this template :

    lein new lein-webapp-template my-web-site

Launch the new Web app : 

    cd my-web-site
    lein ring server

## License

Copyright © 2012 Eric Prunier

Distributed under the Eclipse Public License, the same as Clojure.
