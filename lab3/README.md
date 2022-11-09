# java-technologies-labs
Create a Web application using JavaServer Faces technology, dedicated to Sports Tournament Scheduling
"The problem is to schedule a tournament of n teams over n−1 weeks, with each week divided into n/2 periods, and each period divided into two slots.
The first team in each slot plays at home, whilst the second plays the first team away.
A tournament must satisfy the following three constraints: every team plays once a week; every team plays at most twice in the same period over the tournament; every team plays every other team."
Each team has the following properties: name, founding date, city.

The main specifications of the application are:
# Compulsory
Create the following:

- `Lab3`
  - 
    * A relational database in order to store and retrieve data. (PostgreSQL is recommended).
    * You should have at least the tables: teams and cities.
    * A JSF project using Maven
    * A web page for viewing the teams in the database.

- `Lab4`
  - 
  * Configure a connection pool and a JDBC resource using an administrative tool (such as GlassFish/Payara Console or asadmin).
  * Create DatSource objects using either JNDI directly or resource injection.

# Homework
- `Lab3`
  - 
  * Create the support for managing the teams, using a datatable.
  * Define a page for creating a new team or editing an existing one, using a modal dialog. Use appropriate components for specifying the date and the city.
- `Lab4`
  - 
  - 
# Bonus


# How to run?

- index page:
http://localhost:8080/Tournament/
- Admin console:
http://localhost:4848/