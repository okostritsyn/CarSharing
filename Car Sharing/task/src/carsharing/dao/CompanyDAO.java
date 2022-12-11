package carsharing.dao;

import carsharing.controller.ControllerDB;
import carsharing.mapper.CompanyMapper;
import carsharing.model.Company;
import java.util.List;

public class CompanyDAO implements Dao {
    private ControllerDB jdbcController;

    public CompanyDAO(ControllerDB jdbcController) {
        this.jdbcController = jdbcController;
    }
    private static final String SQL_FIND_COMPANY = "select name,id from company where company.id = 'value_id'";
    private static final String SQL_GET_ALL = "select name,id from company ORDER BY company.id";
    private static final String SQL_INSERT_COMPANY = "insert into company (name) values('value_name')";

    @Override
    public boolean create(Object obj) {
        var company = (Company) obj;
        return jdbcController.update(SQL_INSERT_COMPANY.replace("value_name",company.getName())) > 0;
    }

    @Override
    public Company findById(int id) {
        return jdbcController.queryForObject(SQL_FIND_COMPANY.replace("value_id", String.valueOf(id)), new CompanyMapper());
    }

    @Override
    public List<Company> findAll() {
        return jdbcController.query(SQL_GET_ALL,new CompanyMapper());
    }

    @Override
    public List<Company> findByOwner(String ownerField, int id) {
        return findAll();
    }


}
