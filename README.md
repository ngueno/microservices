# Microservices Architecture

This repo contains some material and studies around the microservices architecture.
The initiative to create this repo was my studies around the microservices, and a course I started on Udemy.

If you want some more information about that, please find the related materials below:

* [Udemy Course](https://www.udemy.com/microservices-with-spring-boot-and-spring-cloud/learn/v4/overview) - Master Microservices with Spring Boot and Spring Cloud 
* [in28minutes Github](https://github.com/in28minutes/spring-microservices) - spring-microservices repo with all the course code

It is important to say that I am not involved with in28minutes or anything related to their course, but indeed it is an awesome course and they deserve all the credit :)

## Getting Started

To get started running all these modules in your system, you should clone the repo and proceed to the deployment section.

### Technologies and Dependencies

All modules are based on Spring Boot, so it should be easy to get them up and running.

The technologies you will find on this repo are the following:

* [Spring Boot](https://spring.io/projects/spring-boot) - Spring framework to easy develop stand-alone Spring based applications
* [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/) - Solution to externalize client-server configuration in a distributed system
* [Eureka](https://cloud.spring.io/spring-cloud-netflix/) - Service discovery and monitoring
* [Ribbon](https://cloud.spring.io/spring-cloud-netflix/) - Client Side Load Balancer
* [Hystrix](https://cloud.spring.io/spring-cloud-netflix/) - Circuit Breaker / Fault Tolerance
* [Zuul](https://cloud.spring.io/spring-cloud-netflix/) - Inteligent Routing / API Gateway
* [Zipkin](https://zipkin.io/) - Distributed tracing system
* [RabbitMQ](https://www.rabbitmq.com/) - Message Queue Framework

Dependency management is being done using [Maven](https://maven.apache.org/).

### Installing

It is important to build all the modules before moving to the next step, you can do it using your IDE or the command line. 

```
cd <module directory>
mvn clean install
```

As a repository, you can use the [Central Repository](https://mvnrepository.com/repos/central), I have successfully fetched all dependencies from there.
If you are having trouble, please check the [Maven Documentation](https://maven.apache.org/configure.html), I am also available for any questions.

## Deployment

Since we are applying many concepts and technologies, I will briefly describe the deployment order and the reasons for that:

1) spring-cloud-config-server
    * Config for all the other applications
2) eureka-naming-server
    * Naming server to enable service-discovery and load balancing
3) zuul-api-gateway-server
    * API Gateway to centralize requests being done to all modules
4) *start RabbitMQ server*
5) *start zipkin server*
    * Zipkin will work as a tracing system to all modules
    * Before start the server, use the following command to configure Zipkin to listen to RabbitMQ: 
    ```
        SET RABBIT_URI=amqp://localhost
    ```
6) currency-exchange-service
    * Service to fetch the currency exchange factor for a conversion
7) currency-conversion-service
    * Service to apply the conversion exchange factor and get back with the final conversion value
8) limits-service
    * Service to verify limits
    
The default configuration ports are the following (Copied from in28minutes GitHub):

|     Application       |     URL          |
| ------------- | ------------- |
| Limits Service | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh|
|Spring Cloud Config Server| http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev |
|  Currency Converter Service - Direct Call| http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10|
|  Currency Converter Service - Feign| http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000|
| Currency Exchange Service | http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR|
| Eureka | http://localhost:8761/|
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10|
| Zipkin | http://localhost:9411/zipkin/ |
| Spring Cloud Bus Refresh | http://localhost:8080/bus/refresh |