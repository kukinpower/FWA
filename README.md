# FWA

## About
School 21 java project.  
Using `Java Servlet API`, `Spring` and `jsp`  

## Compile and run
To run this app on your machine you need to specify your images-storage in [application.properties])(src/main/webapp/WEB-INF/application.properties)  

and add a default image file. For example [shades.png](src/main/webapp/WEB-INF/img/shades.png)

### Compile
```
mvn clean install
```

### Run
```
mvn org.codehaus.cargo:cargo-maven2-plugin:run
```

### If want to init data set `INIT_DATA` env
```
INIT_DATA=true mvn clean install org.codehaus.cargo:cargo-maven2-plugin:run
```
