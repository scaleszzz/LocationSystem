# # Location system

The "Location System" is a Spring Boot application designed to manage locations and provide users with access to them by location.

## Functionality

- **User Management**: Create, update, receive and delete users.
- **Location Management**: Create, update, receive and delete locations.
- **Access value**: Assigning access to users with limited access with different access levels (`READ-ONLY', `ADMIN`).


API Documentation
After launching the application, you can access the Swagger UI to interact with the API:

Swagger UI: http://localhost:8080/swagger-ui.html
Main Endpoints
Users (Users)
POST /users: Create a new user.
GET /users: Get a list of all users.
GET /users/{id}: Get a user by ID.
PUT /users/{id}: Update user information.
DELETE /users/{id}: Delete the user.
Locations (Locations)
POST /locations: Create a new location.
GET /locations: Get a list of all locations.
GET /locations/{id}: Get the location by ID.
PUT /locations/{id}: Update the location information.
DELETE /locations/{id}: Delete a location.
Location Access
POST /location-access/{LocationID}/share: Assign user access to a location.
GET /location-access/{LocationID}/users: Get a list of users who have access to the location.
DELETE /location-access/{LocationID}/users/{userId}: Delete a user's access to a location.
