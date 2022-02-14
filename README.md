# Payten Java Bootcamp Final Ödevi

# Tercih Ettiğim Yöntemler
- Kullanıcıya kredi verilmesini belirleyen kuralları Strategy Design pattern kullanarak yaptım.
- Validation işlemlerini yapmak için Combinator pattern kullandım.
- Kod orgazinazyonu için Hexogonal Mimariyi tercih ettim. Çünkü projede yapılacak eklemelere daha kolay izin verdiği için. 

# Kullandığım Teknolojiler 
- Spring Boot
- Spring Web
- Docker
- Redis, PostgreSQL
- Open-Api
- Lombok 

Docker compose dosyasını çalıştırmak için : 
 docker-compose -f src/main/resources/docker-compose.yml up -d

# End-pointler 

- POST /member                : create new member
- GET /member{identityNumber} : retrieve existing member
- PUT /member                 : update member
- DELETE /member{memberId}    : delete member

-GET /loan-application        : retrieve loan applications result
-POST /loan-application       : apply new loan application , it returns application result

-POST /credit-score           : create credit score 

# Swagger UI will be run on this url:
- http://localhost:{PORT}/swagger-ui.html

