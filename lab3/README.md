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

- `Lab5`
  - 
  * Define the persistence unit using a data source configured as a JDBC Resource.
  * Define at least one entity class and perform a simple test in order to verify the communication with the database.

- `Lab6`
  - 
  * TODO

# Homework
- `Lab3`
  - 
  * Create the support for managing the teams, using a datatable.
  * Define a page for creating a new team or editing an existing one, using a modal dialog. Use appropriate components for specifying the date and the city.
- `Lab4`
  - 
  - Create the pages using templates:
    * page.xhtml: describing the general aspect of the application pages: header, content, footer. The header should display the title and might include a menu bar.
      The footer will display a copyright notice and the current version of the aplication. The header, footer and the menu bar should all be in separate .xhtml files.
    * dataView.xhtml: a generic page for displaying data as a list, dataTable, etc.
- `Lab5`
  - 
  * TODO
- `Lab6`
  - 
  * TODO

# Bonus


# How to run?

### Tournamet
- index page:
http://localhost:8080/Tournament/
- Admin console:
http://localhost:4848/