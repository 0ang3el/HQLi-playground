####HQLi playground
I wrote this vulnerable application for studying  JPQL/HQL injections.

####How I can use it
 - Install Apache Maven and build war package.

	`mvn install`

 - Download J2EE application server (WildFly, GlassFish, WebLogic, TomEE, WAS). Install JDBC driver for desired DBMS. 
 - Create database and table with name 'post' in the database.
 - Create and configure datasource with name 'HQLiDS'.
 - Have fun!