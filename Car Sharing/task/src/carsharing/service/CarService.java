package carsharing.service;

import carsharing.dao.CarDAO;
import carsharing.model.Car;
import java.util.List;

public class CarService {

    private final CarDAO dao;

    public CarService(CarDAO dao) {
        this.dao = dao;
    }

    public boolean save(Car car) {
        return dao.create(car);
    }

    public List<Car> listByCompanyId(int id) {
        return dao.findByOwner("company_id",id);
    }

    public Car getById(int id) {
        return dao.findById(id);
    }
}
