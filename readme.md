api-gateway : 9191
department-service : 8080
employee-service : 8081
organization-service : 8083
config-server : 8888
service-registry : 8761
zipkin server : 9411


## Properties Files
### api gateway
spring.application.name=API-GATEWAY
server.port=9191
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/
management.endpoints.web.exposure.include=*


#spring.cloud.gateway.discovery.locator.enabled=true
#spring.cloud.gateway.discovery.locator.lower-case-service-id=true
#logging.level.org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping=DEBUG


##Routes for EMPLOYEE SERVICE
spring.cloud.gateway.routes[0].id=EMPLOYEE-SERVICE
spring.cloud.gateway.routes[0].uri=lb://EMPLOYEE-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/employees/**

##Routes for DEPARTMENT SERVICE
spring.cloud.gateway.routes[1].id=DEPARTMENT-SERVICE
spring.cloud.gateway.routes[1].uri=lb://DEPARTMENT-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/departments/**

##Routes for ORGANIZATION SERVICE
spring.cloud.gateway.routes[2].id=ORGANIZATION-SERVICE
spring.cloud.gateway.routes[2].uri=lb://ORGANIZATION-SERVICE
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/organizations/**


##ZIPKIN CONF
#spring.zipkin.base-url=http://127.0.0.1:9411/
#spring.sleuth.sampler.probability=1.0


##CORS
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedOrigins=http://localhost:3000
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedHeaders=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedMethods=GET, POST


### Service Registry
spring.application.name=SERVICE-REGISTRY
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

### Config server
server.port=8888

spring.application.name=CONFIG-SERVER
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/


spring.cloud.config.server.git.uri=
spring.cloud.config.server.git.clone-on-start=false
spring.cloud.config.server.git.default-label=master

### department-service
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/department_db
spring.datasource.username=root
spring.datasource.password=123456789
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true

#secret=test sha256
#app.jwt-secret=9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08
#app-jwt-expiration-milliseconds=604800000

spring.application.name=DEPARTMENT-SERVICE
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/


### employee-service
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/employee_db
spring.datasource.username=root
spring.datasource.password=123456789
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true

server.port=8081


spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest



spring.application.name=EMPLOYEE-SERVICE
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/


#Actuator endpoints for circuit breaker
management.health.circuitbreakers.enabled=true
management.endpoints.web.exposure.include=health
management.endpoint.health.shod-details=always

#Circuit breaker configuration
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.registerHealthIndicator=true
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.failureRateThreshold=50
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.minimumNumberOfCalls=5
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.automaticTransitionFromOpenToHalfOpenEnabled=true
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.waitDurationInOpenState=5s
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.permittedNumberOfCallsInHalfOpenState=3
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.slidingWindowSize=10
resilience.circuitbreaker.instances.EMPLOYEE-SERVICE.slidingWindowType=COUNT_BASED

#Retry configuration
resilience.retry.instances.EMPLOYEE-SERVICE.registerHealthIndicator=true
resilience.retry.instances.EMPLOYEE-SERVICE.maxRetryAttempts=5
resilience.retry.instances.EMPLOYEE-SERVICE.waitDuration=1s

### department-service
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/organization_db
spring.datasource.username=root
spring.datasource.password=123456789
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true

server.port=8083

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest


spring.application.name=ORGANIZATION-SERVICE
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/

spring.config.import=optional:configserver:http://localhost:8888


#Actuator endpoints for circuit breaker
management.health.circuitbreakers.enabled=true
management.endpoints.web.exposure.include=*
management.endpoint.health.shod-details=always

