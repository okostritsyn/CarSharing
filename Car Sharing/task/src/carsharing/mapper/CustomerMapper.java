package carsharing.mapper;

import carsharing.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements Mapper<Customer>{
    @Override
    public Customer mapRow(ResultSet resultSet) throws SQLException {
        Customer currCustomer = new Customer();
        currCustomer.setName(resultSet.getString("name"));
        currCustomer.setId(resultSet.getInt("id"));
        var rentCarId = resultSet.getInt("rented_car_id");
        currCustomer.setRentedCarId(resultSet.wasNull()?null:rentCarId);
        return currCustomer;
    }

    @Override
    public List<Customer> mapRows(ResultSet resultSet) throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        while(resultSet.next()){
            customerList.add(mapRow(resultSet));
        }

        return customerList;
    }
}