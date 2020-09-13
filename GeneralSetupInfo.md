To run the stock exchange microservices , use one of the below options :
P.S jdk8 is used

To run it locally :
   1 -In the order-processor microservice directory run : mvn clean install
      -Navigate to the target folder and run : java -jar order-processor-0.0.1-SNAPSHOT.jar
      -To see the  total processed orders and the average execution price : from the browser : http://localhost:8080/orders/metrics 
      -To access the DB : from the browser : http://localhost:8080/h2-console/ 
       and make sure the jdbc url in the login page is jdbc:h2:mem:testdb , there is no password.
      -To run integration test : mvn clean verify
      - Access swagger documentation JSON : http://localhost:8080/v2/api-docs
      -Access swagger documentation UI : http://localhost:8080/swagger-ui.html#/
      
   2 -In the order-generator microservice directory run : mvn clean install -DskipTests
      -Navigate to the target folder and run : java -jar -DORDER_PROCESSOR_BASE_URL=http://localhost:8080 order-generator-0.0.1-SNAPSHOT.jar
      -This service will start generating orders and call the order-processor service and then print the new processed order to the console.
      -To run integration test : mvn clean verify

To use docker : 
    1- In the order-processor microservice directory run : docker build -t order-processor:0.0.1-SNAPSHOT . 
        P.S : the previous line contains a dot at the end , this has to be copied as it is part of the command
    2- In the order-generator microservice directory run : docker build -t order-generator:0.0.1-SNAPSHOT .
    3- In the order-processor microservice directory run : docker-compose up , this will launch the 2 microservices at the same time and new processed order
        will be printed to this console.