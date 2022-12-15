package carsharing.controller;

import carsharing.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
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
        String[] sqlRequests = {"CREATE TABLE IF NOT EXISTS COMPANY  " +
                "(ID INTEGER AUTO_INCREMENT NOT NULL, " +
                " NAME  VARCHAR(255) NOT NULL," +
                " UNIQUE (NAME)," +
                "PRIMARY KEY ( ID ))",
                "CREATE TABLE IF NOT EXISTS CAR  " +
                        "(ID INTEGER AUTO_INCREMENT NOT NULL, " +
                        " NAME  VARCHAR(255) NOT NULL," +
                        " COMPANY_ID  INTEGER NOT NULL," +
                        " UNIQUE (NAME)," +
                        "PRIMARY KEY ( ID ),"+
                        "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID))",
                "CREATE TABLE IF NOT EXISTS CUSTOMER  " +
                        "(ID INTEGER AUTO_INCREMENT NOT NULL, " +
                        " NAME  VARCHAR(255) NOT NULL," +
                        " RENTED_CAR_ID  INTEGER," +
                        " UNIQUE (NAME)," +
                        "PRIMARY KEY ( ID ),"+
                        "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID))"};
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            for (String sqlRequest : sqlRequests) {
               // log.info(sqlRequest);
                stmt.executeUpdate(sqlRequest);
            }
            conn.setAutoCommit(true);
        } catch (SQLException se) {
            //log.warn("An error while init DB"+se.getMessage());
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public <T>T queryForObject(String sql, Mapper<T> mapper) {
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            //log.info(sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) return mapper.mapRow(resultSet);

            //log.warn("resultSet is empty!");

            return null;

        } catch (SQLException se) {
            //Handle errors for JDBC
            //log.warn("An error while queryForObject"+se.getMessage());

            se.printStackTrace();
        }
        return null;
    }

    public <T> List<T> query(String sql, Mapper<T> companyMapper) {
        List<T> companyList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            //log.info(sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            return companyMapper.mapRows(resultSet);

        } catch (SQLException se) {
            //Handle errors for JDBC
            //log.warn("An error while query"+se.getMessage());

            se.printStackTrace();
        }
        return companyList;
    }

    public int update(String sql) {
        try (Connection conn = DriverManager.getConnection(getDbURL());
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            //log.info(sql);
            return stmt.executeUpdate(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            //log.warn("An error while update"+se.getMessage());

            se.printStackTrace();
        }
        return 0;
    }
}
