# CC Technical Skills Interview.

## Overview

This is a Spring Boot app written in Java 17 that exposes API resources for managing users and authentication.

## API Resources

### User Management

#### 1. Retrieve Users

- **Request**: 'GET /users?sortedBy=[email|id|name|phone|taxId|createdAt]'
- **Description**: Returns a list of users stored in the array sorted by the attribute specified in the 'sortedBy' query parameter. If 'sortedBy' is empty or null, users are returned unsortedng.

- **Request**: 'GET /users?filter=[email|id|name|phone|taxId|createdAt]+[co|eq|sw|ew]+[value]'
- **Description**: Returns a list of users filtered by the attribute specified in the 'filter' query parameter. 'filter' should not be empty or null.

##### Examples:

- '/users?filter=name+co+user' 
Returns all users whose name attribute contains "user".
  
- '/users?filter=email+ew+mail.com' 
Returns all users whose email attribute ends with "mail.com".
  
- '/users?filter=phone+sw+555' 
Returns all users whose phone attribute number starts with "555".
  
- '/users?filter=taxId+eq+AARR990101XXX' 
Returns all users whose tax_id attribute is equal to "AARR990101XXX".

#### 2. Create User

- **Request**: 'POST /users'
- **Description**: Stores a new user.

#### 3. Update User

- **Request**: 'PATCH /users/{id}'
- **Description**: Updates a user by ID.

#### 4. Delete User

- **Request**: 'DELETE /users/{id}'
- **Description**: Removes a user by ID.

### User Authentication

#### 1. Login

- **Request**: 'POST /login'
- **Description**: User authentication where 'tax_id' is used as the username.

## Compilation

This project requires Maven for compilation. To compile the application, navigate to the project directory and run:

```bash
mvn clean install

## Additional Notes

- **Password Security**: Passwords are stored using AES256 in CBC mode..
  
- **Timestamp**: The 'created_at' attribute is set to the current timestamp in the Madagascar time zone, formatted as 'dd-mm-yyyy HH:mm'.
  
- **Validations**:
  - 'tax_id' mostly follows Mexico's RFC format and, as it's used as the username, must be unique.
  - Phone numbers must be 10 digits long and can include a country code (max 3 digits).
