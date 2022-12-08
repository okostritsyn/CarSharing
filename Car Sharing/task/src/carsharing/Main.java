package carsharing;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";

    public static void main(String[] args) {
        String currDbName = "carsharing.mv";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFileName") && (i+1)<args.length){
                currDbName = args[i+1].isEmpty()?currDbName:args[i+1];
            }
        }
        String dbUrl = "jdbc:h2:./src/carsharing/db/"+currDbName;

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()){
            System.out.println("Creating table in given database...");
            String sql =  "CREATE TABLE IF NOT EXISTS COMPANY  " +
                    "(id INTEGER not NULL, " +
                    " NAME  VARCHAR(255)," +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Created table Company in given database...");
            conn.setAutoCommit(true);
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        System.out.println("Goodbye!");
    }
}