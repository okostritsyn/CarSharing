package carsharing.service;

import carsharing.dao.CompanyDAO;
import carsharing.model.Company;
import java.util.List;

public class CompanyService {

    private final CompanyDAO dao;

    public CompanyService(CompanyDAO companyDAO) {
        this.dao = companyDAO;
    }

    public boolean save(Company company) {
        return dao.create(company);
    }

    public List<Company> listAll() {
        return dao.findAll();
    }

    public Company getById(int id) {
        return dao.findById(id);
    }
}
