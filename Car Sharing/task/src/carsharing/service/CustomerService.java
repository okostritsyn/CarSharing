package carsharing.service;

import carsharing.dao.CustomerDAO;
import carsharing.model.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerDAO dao;

    public CustomerService(CustomerDAO dao) {
        this.dao = dao;
    }

    public boolean save(Customer customer) {
        return dao.create(customer);
    }

    public List<Customer> listAll() {
        return dao.findAll();
    }

    public List<Customer> listByCompanyId(int id) {
        return dao.findByOwner("rented_car_id",id);
    }
}
