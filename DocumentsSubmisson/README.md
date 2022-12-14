# java-technologies-labs
Create a JSF application for managing the submission of documents into a repository. Documents have a name, [at least]* one author and a single file (content).
The application will allow the following:
# Compulsory
- `Lab7`
  - 
  Create a JSF application for managing the submission of documents into a repository. Documents have a name, [at least]* one author and a single file (content).
The application will allow the following:
  - An authentication mechanism based on username and password (implement it as you please, we'll rewrite it soon).
  - Register new users and assign them a specific role, for example admin, author, reviewer, etc.
  - Specify a time frame, in which registration is open for users and submissions.
  - The possibility to upload a document (for authors) and to view all uploaded documents (for admin). Each uploaded document will have a uniquely generated registration number. All submissions will be logged in a text file.

- `Lab8`
  - 
    Create RESTful Web services using JAX-RS that allow the interaction with at least one JPA entity, implementing CRUD operations. For example, the application may offer a resource containing the following services:
    * adding a new document to the database;
    * replacing an existing document;
    * deleting an existing document.
    * returning a "list" of the documents that were uploaded. The parameter of the web method will be an identifier for the user. If the parameter is null, then all documents should be considered.

# Homework
- `Lab7`
  - 
  Use Contexts and Dependency Injection (CDI) for:
  - the management of application's beans (@Inject) and transactions (@Transactional);
  - decoupling the components using dependency injection (for example, use a producer method to generate registration numbers) (@Produces);
  - decoupling orthogonal concerns, such as logging; (@Interceptor)
  - decoupling bussines concerns, such as verifying the date for operations like registration and submission (@Decorator);
  - implementing at least one event-based comunication (for instance, whenever a new document is uploaded a message is produced and all observers of this type of event will be notified) (@Observes);
  - data validation, using Bean Validation annotations.
  
  The original use other CDI concepts such as alternatives, specializations, etc. will be appreciated.

# Bonus


# How to run?

- index page:
  http://localhost:8080/DocumentsSubmisson-1.0-SNAPSHOT/
- Admin console:
  http://localhost:4848/
