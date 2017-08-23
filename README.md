# Image Service
Service to allow photographers to share their work using REST API. The project is created using gradle, jetty, Spring boot, Jersey, Spring Data JPA, and mysql

# Deploying and Running the service

## Create data base

Execute script config/db.sql in mysql local instance

## Compile and Deploy

Open the file config/application.properties and update the property the property spring.datasource.password with the password created for the user 'chicharron' 

Open a terminal, navigate to the project and execute the following command:
```java
gradle build && java -jar [buildPath]\builds\libs\imageService-0.0.1-SNAPSHOT.jar
```

# Using the API

## Images

### Get all existing images

http://localhost:8080/photos


### Get All Images matching like a name

http://localhost:8080/photos?nameLike={value}


### Get image by Id

http://localhost:8080/photos/{id}


### Get Image by a photographer

http://localhost:8080/photographers/{photographerId}/photos/{imageName}


### Get All Images by a photographer

http://localhost:8080/photographers/{photographerId}/photos


### Uploading a picture

Open a browser and type
 
http://localhost:8080

Once the form is displayed, input the required information and upload a picture from the file system.
