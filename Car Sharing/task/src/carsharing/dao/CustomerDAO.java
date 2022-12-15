package carsharing.dao;

import carsharing.controller.ControllerDB;
import carsharing.mapper.CustomerMapper;
import carsharing.model.Customer;
import java.util.List;

public class CustomerDAO implements Dao{
    private ControllerDB jdbcController;

    public CustomerDAO(ControllerDB jdbcController) {
        this.jdbcController = jdbcController;
    }

    private static final String SQL_GET_ALL = "select name,id,rented_car_id from customer ORDER BY customer.id";
    private static final String SQL_INSERT = "insert into customer (name,rented_car_id) values('value_name',value_id)";
    private static final String SQL_GET_BY_OWNER = "select name,id,rented_car_id from customer where owner_id = 'value_id' ORDER BY customer.id";
    private static final String SQL_UPDATE_CAR = "UPDATE customer SET  rented_car_id = value_id where customer.id = value_cust_id";

    @Override
    public boolean create(Object obj) {
        var car = (Customer) obj;
        return jdbcController.update(
                SQL_INSERT
                        .replace("value_name",car.getName())
                        .replace("value_id",car.getRentedCarId() != null?"'"+ car.getRentedCarId() +"'":"NULL")) > 0;
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return jdbcController.query(SQL_GET_ALL,new CustomerMapper());
    }

    @Override
    public List<Customer> findByOwner(String ownerField, int id) {
        return jdbcController.query(SQL_GET_BY_OWNER
                        .replace("owner_id",ownerField)
                        .replace("value_id",String.valueOf(id)),new CustomerMapper());
    }

    public boolean rentCar( int id, int idCar) {
        return jdbcController.update(
                SQL_UPDATE_CAR
                        .replace("value_cust_id",String.valueOf(id))
                        .replace("value_id",String.valueOf(idCar)))>0;
    }

    public boolean returnCar(int id) {
        return jdbcController.update(
                SQL_UPDATE_CAR
                        .replace("value_cust_id",String.valueOf(id))
                        .replace("value_id","NULL"))>0;
    }
}
