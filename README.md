# data-visualization
This project demonstrates the use of Vert.x, a high performance reactive framework for the JVM. It focuses on how it can be used to stream data in real-time to a web frontend.

1. Frontend starts and subscribes to two event buses
1. Backend begins to read a 4MB input file in 8 byte chunks to save memory
3. Processes and formats the data into JSON for the frontend
4. Publishes the JSON objects over the Vert.x event buses to be displayed

The frontend contains two charts: 

1. Heatmap represents the values of each 8 byte chunk read from file and then evaluated as an integer. For example 00001011 = 11. The BE then keeps a count of how many times this value has occurred (map will remember at most 50 distinct values).
2. Area chart, plots the number of 0's versus the number of 1's that have occurred in the most recent 8 byte chunk read from the file. 

Technologies used include:

[Epoch Charts](https://fastly.github.io/epoch/) /
[Bootstrap](http://getbootstrap.com/) /
[Vert.x](http://vertx.io/) /
[Spring Boot](http://projects.spring.io/spring-boot/) 

===================
Running locally
===================

Simply checkout the project and then run:

```mvn spring-boot:run``` 
