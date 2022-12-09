package carsharing.dao;

import carsharing.controller.ControllerDB;
import carsharing.mapper.CompanyMapper;
import carsharing.model.Company;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    private ControllerDB jdbcController;

    public CompanyDAO(ControllerDB jdbcController) {
        this.jdbcController = jdbcController;
    }

    private final String SQL_GET_ALL = "select name from company ORDER BY company.id";
    private final String SQL_INSERT_COMPANY = "insert into company (name) values('value_name')";

    public boolean create(Company company) {
        return jdbcController.update(SQL_INSERT_COMPANY.replace("value_name",company.getName())) > 0;
    }

    public List<Company> findAll() throws SQLException {
        List<Company> listOfCompanies = jdbcController.query(SQL_GET_ALL,new CompanyMapper());
        return listOfCompanies;
    }

}
