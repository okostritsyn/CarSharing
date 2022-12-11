package carsharing.service;

import carsharing.dao.CarDAO;
import carsharing.dao.CompanyDAO;
import carsharing.dao.Dao;
import carsharing.model.Car;
import carsharing.model.Company;

import java.sql.SQLException;
import java.util.List;

public class CarService {

    private final CarDAO dao;

    public CarService(CarDAO dao) {
        this.dao = dao;
    }

    public boolean save(Car car) {
        return dao.create(car);
    }

    public List<Car> listAll() {
        return dao.findAll();
    }

    public List<Car> listByCompanyId(int id) {
        return dao.findByOwner("company_id",id);
    }
}
