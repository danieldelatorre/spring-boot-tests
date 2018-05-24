Start the Spring Boot server.


Go to the project's root folder and execute:
```
mvn clean install
```

Run the generated jar:
```
java -jar target/coupon-service-0.0.1-SNAPSHOT.jar
```


Existing discount code: -10 discount

```
curl -H "Content-Type: application/json" -X POST -d '{"code": "TEST","products": [{"code": "camisa","price": 3.0},{"code": "traje","price": 12.0}]}' http://localhost:8080/calculate
```


Non existing discount code: 0 discount
```
curl -H "Content-Type: application/json" -X POST -d '{"code": "nonxistant","products": [{"code": "camisa","price": 3.0},{"code": "traje","price": 12.0}]}' http://localhost:8080/calculate
```


Create docker image:
```
./mvnw install dockerfile:build
```

```
docker run --rm -p 8080:8080 -d springio/coupon-service
```