# Back

This project is develop in Java version 17 with Spring

## Software

- IDE (intelliJ)
- Postman
- VsCode
- MongoDB Compass

## Intallation

For this application, I created a NoSQL database at https://cloud.mongodb.com/, then connected it to the MongoDB Compass software. If you already have Mongo installed locally on your machine, use your own login. Otherwise, don't forget to create a user with full rights to connect to your database, and to validate your connection IP address.
After this step , follow these steps

1. Open the back Folder with your IDE
2. Create un file .env in the back folder. In this file , you have to create :
   - DATABASE = "Name of your database"
   - DB_USERNAME = "Username connexion"
   - DB_PASSWORD = "Password connexion"
3. Run your application with your IDE. With IntelliJ, right click on the `MddApiApplication` file. Intellij will install automatically maven dependencies

After these steps, you can go on http://localhost:8080/documentation for more information on this API.

### Warning

Install the back end before consulting the API documentation.
