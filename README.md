# Image Service
Service to upload and download images using gradle, Spring boot, JPA, mysql

## Running the service
Open a terminal, navigate to the project and execute the command:
```java
gradle build && java -jar [buildPath]\builds\libs\gs-spring-boot-0.1.0.jar
```

## Using the services

Note. Work in progress.

### Uploading a picture
Open a browser and type 
localhost:8080
Once the form is displayed, you can upload a picture from the file system

### Query existing images
To review the uploaded pictures type 
localhost:8080/images
you should see a collection with the uploaded pictures