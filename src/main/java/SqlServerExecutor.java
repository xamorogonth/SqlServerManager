import java.sql.*;

public class SqlServerExecutor {
        private static final String URL = "jdbc:sqlserver://localhost:1433";
        private static final String USERNAME = "operator";
        private static final String PASSWORD = "password";

        private static Connection connection;

        static {
                try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }

                try {
                        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }
        }

        public static void main(String[] args) {
                try {
                        Statement statement = connection.createStatement();
                        String query =
                                "CREATE DATABASE user3_db;" +
                                "CREATE LOGIN user3 WITH PASSWORD = 'default';";
                        statement.execute(query);
                        while (true) {
                                try {
                                        statement.execute("USE user3_db;");
                                        break;
                                } catch (SQLException exception) {

                                }
                        }
                        statement.execute("CREATE USER dbuser FOR LOGIN user3;");
//                        ResultSet result = statement.executeQuery("select name from user3_db.sys.database_principals where name like 'dbuser';");
//                        while (true) {
//                                if (result.next()) {
//                                        System.out.println("Username " + result.getString("name"));
//                                        break;
//                                }
//                        }
                        statement.execute("EXEC sp_addrolemember 'db_owner', 'dbuser';");
                } catch (SQLException throwables) {
                        throwables.printStackTrace();

                }

//                Stream<String> queries = Stream.of(
//                        "CREATE DATABASE user3_db",
//                        "CREATE LOGIN user3 WITH PASSWORD = 'default'",
//                        "USE user3_db;",
//                        "CREATE USER dbuser FOR LOGIN user3;",
//                        "EXEC sp_addrolemember 'db_owner', 'dbuser'"
//                );
//
//                queries.forEach(query -> {
//
//                });
        }

}
