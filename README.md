# Image Service
Service to upload and download images using gradle, Spring boot, Jersey, JPA, and mysql

# Running the service
Open a terminal, navigate to the project and execute the command:
```java
gradle build && java -jar [buildPath]\builds\libs\imageService-0.0.1-SNAPSHOT.jar
```

# Using the API

## Images

### Get all existing images

http://localhost:8080/photos


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
