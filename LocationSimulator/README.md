# Getting Started Location Simulator


## URL and Port to Consume

You can hit the following URL via postman to test:-
[http://localhost:8009/get?origin=12.93175, 77.62872&destination=12.92662, 77.63696]

### `LocationSimulatorController`

Created an API in controller that consumes the request from client

### `LocationSimulatorService`

In this Service Layer we have the logic of organize the data that we recive from DAO Implementation and process the result to Controller.

### `LocationSimulatorDAO`

It is an interface that defines the functions that we use in the LocationSimulatorDAOImpl class.

### `LocationSimulatorDAOImpl`

This is generally termed as DAO layer.We will create the Request to be sent to googleAPI and process the result to the Service Layer.

### `application.properties`

Here in this file we will configure all the properties that are consumed by our application.
