/*DEVELOPMENT SETUP */

//LOMBOK setup for getter and setters. lombok.jar is in WEB-INF/lib folder so no need to copy or place in classpath
	Close your Eclipse IDE 
	Double click on the lombok.jar
	Specify the location of Eclipse
	click Install/Update button
	Use your Eclipse IDE as usual 

POST /login.do  --Login action URL
	InputPage: /loginPage.do
	ssoId
	password
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	RET: Success - /admin/home.do
	     Fail - /loginPage.do?error=true

POST /admin/logout.do  --Logout action URL
  	 RET: Success - /loginPage.do?logout=true

GET /admin/employees  -- To get all employees
	RET: 204 - NO_CONTENT  --No data found
		 500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     200 - OK -- fetched employees

GET /admin/employee/{idOrName} --To get employees by id or name
	RET: 204 - NO_CONTENT  --No data found
		 500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     200 - OK -- fetched employees

POST /guest/employee/{id} , /admin/employee/{id}  --To add Employee with employee id
	RET: 400 - BAD_REQUEST -- Request is incorrect
	     409 - CONFLICT  -- Employee with id already exist in DB
	     500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     201 - CREATED --added successfully
	

PUT /admin/employee/{id}  --To update employee with employee id {id}
	RET: 400 - BAD_REQUEST -- Request is incorrect
	     404 - NOT_FOUND  -- Employee with id not found in DB
	     500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     202 - ACCEPTED --Updated successfully

DELETE /admin/employee/{id}  --To Delete employee with employee id {id}
	RET: 400 - BAD_REQUEST -- Request is incorrect
	     404 - NOT_FOUND  -- Employee with id not found in DB
	     500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     202 - ACCEPTED --Deleted successfully
	     
GET /admin/{id}/file/{filename}  --To get a file {filename} for employee with id {id}	     
	RET: 400 - BAD_REQUEST -- Request is incorrect
		 204 - NO_CONTENT  --No data found
		 500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     200 - OK -- fetched file {filename} for employee {id}
		 
DELETE /admin/{id}/file/{filename}  --To delete a file {filename} for employee with id {id}	     
	RET: 400 - BAD_REQUEST -- Request is incorrect
	     404 - NOT_FOUND  -- Employee with id not found in DB
	     500 - INTERNAL_SERVER_ERROR  --Due to server issue update not happend
	     202 - ACCEPTED --Deleted {filename} for employee {id}
	   

/*Environment specific information*/
Set spring.profiles.active environment variable according to environment to load environment specific properties files. 
 
	DEV for development. 
		Properties files: DEV-crudExample.properties
	
	TEST for both Unit and Integration testing. 
		Properties files: TEST-crudExample.properties
	
	PROD for production.
		Properties files : PROD-crudExample.properties
	
	How to set the Environment variable:
	Ex: For production configuration
	For Windows, Right click "My Computer" --> Properties --> Advance System Settings --> Environment Variables. Add the following property 
	 	variable: spring.profiles.active
	 	value: PROD
	 	
	 	For *nix, add the following property in before calling server script.
	 		export spring.profiles.active=PROD 


/*HSQLDB configuration. 

HSQLDB is both in-memory and file DB. We are using file DB. 
Since it is file DB,Either application or standalone connectivity accepted, not both*/

/*HSQL Standalone readonly connectivity (while application running)*/
java -cp C:/Users/rnel1941/workspace/CrudExample/test/lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing -driver org.hsqldb.jdbcDriver -url jdbc:hsqldb:file:C:/Users/rnel1941/odcAccess_db;shutdown=true;hsqldb.write_delay=false;readonly=true -user sa


/* SPRING Security DB remember-me functionality. It needs database table to get and stored*/
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)

