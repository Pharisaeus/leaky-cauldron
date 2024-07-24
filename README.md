Repeat a couple of times:

- GET  http://localhost:8080/someEndpoint
- GET  http://localhost:8080/actuator/threaddump (how many threads?)
- POST http://localhost:8080/actuator/restart

and observe the number of threads growing
