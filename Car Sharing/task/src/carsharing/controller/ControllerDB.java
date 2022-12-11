package carsharing.controller;

import carsharing.mapper.Mapper;

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
                    " UNIQUE (NAME)," +
                    "PRIMARY KEY ( ID ))";
            stmt.executeUpdate(sql);
            conn.setAutoCommit(true);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public <T>T queryForObject(String sql, Mapper<T> mapper) {
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);

            ResultSet resultSet = stmt.executeQuery(sql);
            return mapper.mapRow(resultSet);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }

    public <T> List<T> query(String sql, Mapper<T> companyMapper) {
        List<T> companyList = new ArrayList<>();
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
