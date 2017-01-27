package com.codurance.driver.manager;


import java.sql.*;
import java.util.Properties;

public class JdbcExample {

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "myuser");
        props.setProperty("password", "mypassword");

        Connection connection =
                DriverManager.getConnection(
                        "jdbc:postgresql://192.168.99.100:5432/myuser",
                        props);

        Statement statement = connection.createStatement();

        execute(statement);
        executeQuery(statement);
        executeUpdate(statement);

        preparedStatementExample(connection, "3 Henry Darlot Dr");

        callableStatementExample(connection);

        connection.close();
    }

    private static void executeUpdate(Statement statement) throws SQLException {
        String updateQuery = "INSERT INTO jdbc.address" +
                "   (_identifier, _post_code, _post_town)" +
                " VALUES " +
                "   ('x','SOME POST CODE','A POST TOWN')";

        String deleteQuery = "DELETE FROM jdbc.address" +
                " WHERE" +
                "   _identifier = 'x'";

        String query = "SELECT" +
                "   _identifier, _post_code, _post_town" +
                " FROM " +
                "   jdbc.address WHERE _identifier = 'x'";

        statement.executeUpdate(updateQuery);


        ResultSet resultSet = statement.executeQuery(query);
        printAddressFromResultSet(resultSet);

        statement.executeUpdate(deleteQuery);

        resultSet = statement.executeQuery(query);
        printAddressFromResultSet(resultSet);
    }

    private static void execute(Statement statement) throws SQLException {
        String query = "SELECT" +
                "   _identifier, _post_code, _post_town" +
                " FROM" +
                "   jdbc.address where _identifier = 'x'";

        boolean executed = statement.execute(query);
        if (executed) {
            ResultSet resultSet = statement.getResultSet();
            printAddressFromResultSet(resultSet);
        }
    }

    private static void executeQuery(Statement statement) throws SQLException {
        String query = "SELECT" +
                "   _identifier, _post_code, _post_town" +
                " FROM " +
                "   jdbc.address WHERE _identifier = 'x'";

        ResultSet resultSet = statement.executeQuery(query);
        printAddressFromResultSet(resultSet);
    }

    private static void preparedStatementExample(Connection connection, String address_identifier) throws SQLException {
        String query = "SELECT _identifier, _post_code, _post_town FROM jdbc.address WHERE _identifier = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, address_identifier);

        printAddressFromResultSet(preparedStatement.executeQuery());

    }

    private static void callableStatementExample(Connection connection) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall("{ ? = call upper( ? ) }");
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, "lowercase to uppercase");
        callableStatement.execute();
        String upperCased = callableStatement.getString(1);
        System.out.println(upperCased);
        callableStatement.close();

    }


    private static void printAddressFromResultSet(ResultSet resultSet) throws SQLException {
        System.out.println("Results:");
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No results");
        } else {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("_identifier"));
                System.out.println(resultSet.getString("_post_code"));
                System.out.println(resultSet.getString("_post_town"));
                System.out.println();
            }
        }
    }

}
