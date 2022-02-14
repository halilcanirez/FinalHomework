# Payten Java Bootcamp Final Homework


- I wrote the rules that determine the loan to the user using the Strategy Design pattern.
- I used Combinator Pattern to do Validation.
- I preferred Hexogonal Architecture for code organization.

# Tech Stack
- Spring Boot
- Spring Web
- Docker
- Redis, PostgreSQL
- Open-Api
- Lombok 

# Docker compose dosyasını çalıştırmak için : 
 docker-compose -f src/main/resources/docker-compose.yml up -d

# End-point 

- POST /member                : create new member
- GET /member{identityNumber} : retrieve existing member
- PUT /member                 : update member
- DELETE /member{memberId}    : delete member

- GET /loan-application        : retrieve loan applications result
- POST /loan-application       : apply new loan application , it returns application result

- POST /credit-score           : create credit score 

# Swagger UI will be run on this url:
- http://localhost:{PORT}/swagger-ui.html

