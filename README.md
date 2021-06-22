# Shoe shop API

## Launch Application
Launch the following command and the application is available on `http://localhost:8080/`:
```shell script
mvn clean install && \
  java -jar controller/target/controller-1.0.jar
```

You can change the stock capacity by adding the following property: `--stock.capacity=VALUE` or change it in the application.properties file (Set to 30 by default).  
Example: `mvn clean install && java -jar controller/target/controller-1.0.jar --stock.capacity=10`

## Technical info

JAVA 14  
Spring 5  
Spring Boot 2  
H2 database (Accessible through http://localhost:8080/h2-console when app is launch)  

jUnit5  
Mockito

## Application info

### GET /search/shoes
This endpoint will display all shoes available.

#### Request
```
curl --location --request GET 'http://localhost:8080/shoes/search?color=BLACK&size=42' \
--header 'version: 1' \
--header 'Content-Type: application/json'
```
#### Response
```
{
    "shoes": [
        {
            "name": "Nike Running",
            "size": 42,
            "color": "BLACK"
        }
    ]
}
```

### GET /stock/state
This endpoint will display the contents of stock with it state (SOME, FULL, EMPTY).

#### Request
```
curl --location --request GET 'http://localhost:8080/stock/state' \
--header 'version: 1' \
--header 'Content-Type: application/json'
```
#### Response
```
{
    "state": "SOME",
    "shoes": [
        {
            "name": "Nike Running",
            "size": 42,
            "color": "BLACK",
            "quantity": 10
        },
        {
            "name": "Nike Air Force",
            "size": 40,
            "color": "BLUE",
            "quantity": 1
        },
        {
            "name": "Toto",
            "size": 45,
            "color": "BLUE",
            "quantity": 5
        },
        {
            "name": "Puma Running",
            "size": 49,
            "color": "BLACK",
            "quantity": 1
        }
    ]
}
```

### PATCH /stock
This endpoint will add a list a shoes to stock. 
If the quantity of shoes to add is greater than the stock capacity, an error will be returned (403 Forbidden).  
The capacity of the stock can be set using the following property `stock.capacity` (Set to 30 by default).

#### Request
```
curl --location --request PATCH 'http://localhost:8080/stock' \
--header 'version: 1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "shoes": [
        {
            "name": "Nike Air Force",
            "size": 40,
            "quantity": 1,
            "color": "BLUE"
        },
        {
            "name": "Puma Running",
            "size": 49,
            "quantity": 1,
            "color": "BLACK"
        }
    ]
}'
```

#### Response 
Same as GET /stock response

### DELETE /stock
This endpoint will clear all the shoes in the stock.  

#### Request
```
curl --location --request DELETE 'http://localhost:8080/stock/' \
--header 'version: 1' \
--header 'Content-Type: application/json'
```

## Doc
[Explanations](doc/Explanations.md#explanations)



