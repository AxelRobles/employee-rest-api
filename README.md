# employee-rest-api

In order to run the program, you can either, run the jar file that is in the root of the project like this:

java -jar employee-rest-api-0.0.1-SNAPSHOT.jar

Or also you can execute the maven command: mvn clean install.

This will generate a docker image in you machine, so then you can run a container like this:
docker run -p 8080:8080 employee-rest-api

Also if you want you can open the project in one IDE and run it from there.

In order to test the api, you have to do requests through localhost in port 8080 adding the specific url.

http://localhost:8080/

Is preferred to test the api with a tool like postman.

By default, the project already adds some employees to testing, so from the begginig you can perform request like get all employees.

I added some extras functionalities that I will mark with *ADDED note.

# Get all employees
## GET Method
URI : "/public/employees"
example: http://localhost:8080/public/employees
answer: list of json objects that represent employees stored in the DB

# Get employees by an ID
## GET Method
URI: "/public/employee/{id}"
example: http://localhost:8080/public/employee/9999


# Get employee id searching by full name (firstName, middleInitial and lastName)
## POST Method
### *ADDED by me
URI: /public/getEmployeeId
example:  http://localhost:8080/public/getEmployeeId
body request example : 
{
	"firstName": "Victoria",
    "middleInitial": "Sandra",
    "lastName": "Arias"
} 
answer: long number representing id of employee with that name



# Create/Save new employee
## Needs Json representation of employee object
## POST Method
URI: /public/saveEmployee"
example:  http://localhost:8080/public/saveEmployee
body request example : 
{
    "firstName": "Antonio",
    "middleInitial": "Carlos",
    "lastName": "Lopez",
    "dateOfBirth": "27/01/2020",
    "dateOfEmployment": "27/01/2020",
    "status": "Active"
}
answer: status code 201 Created

# Update employee by id
## Post Method
Basic Authentication needed  user:admin password:password
Needs Json reprentation that MUST have the employee id and all the other fields you want to update
It will only update the fields that you put in the json body

URI: /private/updateEmployeeById
example: http://localhost:8080/private/updateEmployeeById
body request example:
{
   "id": 9995,
    "firstName": "Valentina",
    "middleInitial": "Luciana",
    "lastName": "Flores"
}
Another example:
{
   "id": 9995,
    "firstName": "Jane",
}


# Delete employee by id
## DELETE Method
Basic Authentication needed  user:admin password:password

URI: /private/deleteEmployee/{id}
example: http://localhost:8080/private/deleteEmployee/9995
answer: status code 200 OK

