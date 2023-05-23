### Service Registry

notes: <br>
Update on Using Spring Boot 3 Version <br>
In the next lecture, make the below small change to support Spring Boot 3 and Spring Cloud 2022.0.0:

- Don’t annotate an entry-point DepartmentServiceApplication class with @EnableEurekaClient - This annotation was removed in spring cloud 2022.0.0 and provided auto-configuration.
