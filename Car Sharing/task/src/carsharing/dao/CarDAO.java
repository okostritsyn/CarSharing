package carsharing.dao;

import carsharing.controller.ControllerDB;
import carsharing.mapper.CarMapper;
import carsharing.model.Car;

import java.util.List;

public class CarDAO implements Dao{
    private ControllerDB jdbcController;

    public CarDAO(ControllerDB jdbcController) {
        this.jdbcController = jdbcController;
    }

    private static final String SQL_GET_ALL = "select name,id,company_id from car ORDER BY car.id";
    private static final String SQL_INSERT_CAR = "insert into car (name,company_id) values('value_name','value_id')";
    private static final String SQL_GET_BY_OWNER = "select car.name,car.id,car.company_id from car " +
            "LEFT JOIN customer on car.id = customer.rented_car_id " +
            "where car.owner_id = 'value_id' and customer.id IS NULL ORDER BY car.id";
    private static final String SQL_GET_BY_ID = "select name,id,company_id from car where car.id = 'value_id'";

    @Override
    public boolean create(Object obj) {
        var car = (Car) obj;
        return jdbcController.update(
                SQL_INSERT_CAR
                        .replace("value_name",car.getName())
                        .replace("value_id",String.valueOf(car.getCompanyId()))) > 0;
    }

    @Override
    public Car findById(int id) {
        return jdbcController.queryForObject(SQL_GET_BY_ID.replace("value_id", String.valueOf(id)),new CarMapper());
    }

    @Override
    public List<Car> findAll() {
        return jdbcController.query(SQL_GET_ALL,new CarMapper());
    }

    @Override
    public List<Car> findByOwner(String ownerField, int id) {
        return jdbcController.query(SQL_GET_BY_OWNER
                        .replace("owner_id",ownerField)
                        .replace("value_id",String.valueOf(id)),new CarMapper());
    }

}
