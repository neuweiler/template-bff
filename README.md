# template-bff
A template for a "backend-for-frontend" spring-boot app with all the necessary stuff that's required for an enterprise app

Contains a sample model with CRUD support via dao's, dto's and spring controllers.

The application is intended to allow users of a system owner or a company
to enter production values for their systems on a monthly basis. E.g. the kiloWattHours of a solar system or the
output of a system in number of units (e.g. breads).

![Class Diagram](https://github.com/neuweiler/template-bff/raw/main/docs/Class%20Diagram.png)

The template includes:
* spring-boot based application
* offers REST based services for frontend solution(s)
* JPA/hibernate integration towards database
* spring-security for authentication/authorization
* lombok and jmapper for boilerplate stuff (getter/setter, mapping)
* swagger for API documentation with UI (http://localhost:8080/swagger-ui/index.html)

ToDo:
* graphQL integration
* OAuth and SAML based auth, URL and method based authorization
* sending reminders to user to enter their monthly values (e.g. by mail)
