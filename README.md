# Spotty Java App

This is a (simplified) java-port of the spotty application (https://github.com/Sero7777/spotty).

## Technologies used
- Java
- Spring Boot
- PostgreSQL
- Kafka
- Docker

##  Functionalities
- Create, Update, Delete Spots
- Create, Update, Delete Comments
- Get all Spots with Comments attached to corresponding Spot

## How to run

1) Clone the project
2) Open the terminal and run 
>docker-compose up 
3) Wait for everything to start up

## API Documentation (excerpt)
- Spot Post-Request:

![Post Spot Request](https://user-images.githubusercontent.com/47899469/103464951-1faef880-4d38-11eb-89a3-1eae64b02b3e.png "Post Spot Request")

- Spot Post-Response:

![Post Spot Response](https://user-images.githubusercontent.com/47899469/103464952-20e02580-4d38-11eb-82bf-c10cc2c25b35.png "Post Spot Response")

- Comment Post-Request:

![Post Comment Request](https://user-images.githubusercontent.com/47899469/103464967-39504000-4d38-11eb-988f-a597a2546c15.png "Post Comment Request")

- Comment Post-Response:

![Post Comment Response](https://user-images.githubusercontent.com/47899469/103464955-2178bc00-4d38-11eb-87c4-f4c287788c65.png "Post Comment Response")

- Spots+Comments Get-Request:

![Query Get Request](https://user-images.githubusercontent.com/47899469/103464956-2178bc00-4d38-11eb-9f1a-6ddec7924cc7.png "Query Get Request")

- Spots+Comments Get-Response:

![Query Get Response](https://user-images.githubusercontent.com/47899469/103464957-22115280-4d38-11eb-9114-9a3d5bbce062.png "Query Get Response")
