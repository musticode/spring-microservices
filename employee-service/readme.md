## Microservices Architecture using Spring boot and Spring Cloud
RestTemplate<br>
WebClient<br>
SpringCloud Open Feign


### Architecture
Employee Service -> mysql<br>
Department Service -> mysql<br>
Organization Service<br>
Service registry<br>
API Gateway<br>
Config Server<br>
Spring cloud sleuth <br>

### Microservice Applications and It's Port Mapping
We will be creating a lot of microservices so please refer below ports mapping (microservice applications with their ports):

For the API-Gateway application, use the **9191** port.

For the Department-Service application, use the **8080** port and for its instance, use port 8082

For the Employee-Service application, use the **8081** port.

For the Config-Server application, use the **8888** port.

For the Service-Registry application, use the **8761** port.

For the Organization-Service application, use the **8083** port.

For the React-Frontend application, use the **3000** port.

Zipkin Server uses the default port **9411**

Here is the screenshot image for your reference. Download and use it on your machine.

GitHub repository for Spring Boot 2.7.4 - <br> https://github.com/RameshMF/springboot-microservices/tree/main/springboot-microservices



GitHub repository for Spring Boot 3 -<br> https://github.com/RameshMF/springboot-microservices/tree/main/v3/springboot-microservices

