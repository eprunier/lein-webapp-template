# {{name}}

FIXME

## Usage
Launch the application by issuing one of the following commands:

```shell
SERVER_HOST=localhost SERVER_PORT=8080 lein run
```

You can generate a standalone jar and run it:

```shell   
lein uberjar
java -Dserver.host=localhost -Dserver.port=8080 -jar target/{{name}}-0.1.0-SNAPSHOT-standalone.jar
```

You can also generate a war to deploy on a server like Tomcat, Jboss...

```shell
lein ring uberwar
```

## License

Copyright © {{year}} FIXME

Distributed under the Eclipse Public License, the same as Clojure.
