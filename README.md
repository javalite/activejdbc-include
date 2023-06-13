# ActiveJDBC example showing how to do includes 
 
This example was developed as a response to this Stack overflow question: [Unable to Eager load children using getAll(..)](https://stackoverflow.com/questions/76449257/unable-to-eager-load-children-using-getall).

## How to build

This example uses MySQL in a Docker container. When starting the database for the first time, use script: 

```
./docker/install-run.sh
```

In subsequent runs,  you can use start/stop scripts:

```
./docker/start.sh
```

## How to execute 

The example was developed as a test, so it will execute during a normal build: 

```
mvn clean install
```

The build will execute the test and will emit the following log snippet: 

```
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO directors (first_name, last_name) VALUES (?, ?)","params":["Alfred","Hitchcock"],"duration_millis":3}
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO movies (director_id, title) VALUES (?, ?)","params":[27,"Strangers on a Train"],"duration_millis":1}
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO movies (director_id, title) VALUES (?, ?)","params":[27,"Robert Walker"],"duration_millis":0}
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO movies (director_id, title) VALUES (?, ?)","params":[27,"Rear Window"],"duration_millis":1}
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO directors (first_name, last_name) VALUES (?, ?)","params":["Stanley","Kubrick"],"duration_millis":1}
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO movies (director_id, title) VALUES (?, ?)","params":[28,"2001: A Space Odyssey"],"duration_millis":2}
[main] INFO org.javalite.activejdbc.DB - {"sql":"INSERT INTO movies (director_id, title) VALUES (?, ?)","params":[28,"A Clockwork Orange"],"duration_millis":0}
---->>
[main] INFO org.javalite.activejdbc.LazyList - {"sql":"SELECT * FROM directors","params":[],"duration_millis":3,"cache":"miss"}
Model: activejdbc.examples.simple.Director, table: 'directors', attributes: {first_name=Alfred, id=27, last_name=Hitchcock}
Model: activejdbc.examples.simple.Director, table: 'directors', attributes: {first_name=Stanley, id=28, last_name=Kubrick}
<<----
[main] INFO org.javalite.activejdbc.LazyList - {"sql":"SELECT * FROM directors WHERE id in(27,28)","params":[],"duration_millis":1,"cache":"miss"}
[main] INFO org.javalite.activejdbc.LazyList - {"sql":"SELECT * FROM movies WHERE director_id IN (?, ?) ORDER BY id","params":[27,28],"duration_millis":1,"cache":"miss"}
ID:27, Director: Alfred Hitchcock , Movies: [Strangers on a Train, Robert Walker, Rear Window]
ID:28, Director: Stanley Kubrick , Movies: [2001: A Space Odyssey, A Clockwork Orange]
```

The important line is: 

```
SELECT * FROM movies WHERE director_id IN (?, ?) ORDER BY id","params":[27,28]
```
Which proves that the `include()` method is working as expected
