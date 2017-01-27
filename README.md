# JDBC and JPA example

## How to create tables.

Example SQL script for PostgreSQL:
/src/main/resources/postgresql_example_database.sql

## How to connect to your database.

Change values of properties in the lines:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://192.168.99.100:5432/myuser" />
<property name="javax.persistence.jdbc.user" value="myuser" />
<property name="javax.persistence.jdbc.password" value="mypassword" />
<property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
```