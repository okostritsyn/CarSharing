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

    public boolean rentCar(Customer customer, int idCar) {
        var status = dao.rentCar(customer.getId(),idCar);
        if (status) customer.setRentedCarId(idCar);
        return status;
    }

    public boolean returnCar(Customer customer) {
        var status = dao.returnCar(customer.getId());
        if (status) customer.setRentedCarId(null);
        return status;
    }
}
