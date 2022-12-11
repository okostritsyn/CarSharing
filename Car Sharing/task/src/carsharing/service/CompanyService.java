package carsharing.service;

import carsharing.dao.CompanyDAO;
import carsharing.model.Company;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {

    private final CompanyDAO companyDAO;

    public CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public boolean save(Company company) {
        return companyDAO.create(company);
    }

    public List<Company> listAll() {
        return companyDAO.findAll();
    }
}
