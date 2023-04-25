# HR-Platform
HR platform for adding and monitoring job candidates and their skills in order to facilitate HR processes within a company.

## Runing project localy

#### Backend
The backend is a Maven Spring Boot application. Source code is in the <i>./HR-Platform/demo/</i> folder. It can be started normally as a Java project in Eclipse or IntelliJ. I used Java 11 version. The server is running on port 8081.\
PostgreSQL is needed for the backend, and database with name <i>HRDatabase</i>. All about project configuration you can find in <i>application.properties</i> file.

#### Frontend
The frontend is a React application. Source code is in the <i>./HR-platform/hr-web-app</i> folder. It can be started by running <code>npm install</code> and then <code>npm start</code> in the mentioned folder. The URL address is http://localhost:3000/.