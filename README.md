# lein-webapp-template

A Leiningen template for Web apps based on Compojure, Stencil, Bootstrap and jQuery.

## Usage with Leiningen 2

Create a new project using this template:

    lein new lein-webapp-template my-web-site
    cd my-web-site

Then launch the new Web app by issuing one of the following commands:

```shell
lein run <port>
```

You can generate a standalone jar and run it:

```shell   
lein uberjar
java -jar target/my-web-site-0.1.0-SNAPSHOT-standalone.jar
```

You can also generate a war to deploy on a server like Tomcat, Jboss...

```shell
lein ring uberwar
```

## License

Copyright © 2012-2013 Eric Prunier

Distributed under the Eclipse Public License, the same as Clojure.
