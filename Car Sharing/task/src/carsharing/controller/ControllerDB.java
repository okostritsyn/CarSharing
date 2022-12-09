package carsharing.controller;

import carsharing.dao.CompanyDAO;
import carsharing.mapper.CompanyMapper;
import carsharing.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerDB {
    private final String dbName;

    public ControllerDB(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbURL() {
        return "jdbc:h2:./src/carsharing/db/"+getDbName();
    }

    public void initDB() {
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY  " +
                    "(ID INTEGER AUTO_INCREMENT NOT NULL, " +
                    " NAME  VARCHAR(255) NOT NULL," +
                    " UNIQUE KEY acct_authority_name_UNIQUE (NAME)," +
                    "PRIMARY KEY ( ID ))";
            stmt.executeUpdate(sql);
            conn.setAutoCommit(true);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public List<?> query(String sql, CompanyMapper companyMapper) {
        List<?> companyList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);

            ResultSet resultSet = stmt.executeQuery(sql);
            return companyMapper.mapRows(resultSet);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return companyList;
    }

    public int update(String sql) {
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);

            return stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return 0;
    }
}
