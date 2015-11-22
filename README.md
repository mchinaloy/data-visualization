# DataVisualization
This project demonstrates the use of Vert.x, a high performance reactive framework for the JVM. It focuses on how it can be used to stream data in real-time to a web frontend.

1. Frontend starts and subscribes to two event buses on a given port
1. Backend begins to read a 4MB input file (binary data of an image)
2. It reads from the file in 8 byte chunks to save memory
3. It then processes and formats the data into JSON for the frontend
4. Finally, it publishes the JSON objects over the Vert.x event buses to be displayed

Epoch Charts
https://fastly.github.io/epoch/

Bootstrap
http://getbootstrap.com/

Vert.x
http://vertx.io/

Spring Boot
http://projects.spring.io/spring-boot/
