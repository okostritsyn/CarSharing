package carsharing.mapper;

import carsharing.dao.CarDAO;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.service.CarService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarMapper implements Mapper<Car>{
    @Override
    public Car mapRow(ResultSet resultSet) throws SQLException {
        Car currCar = new Car();
        currCar.setName(resultSet.getString("name"));
        currCar.setId(resultSet.getInt("id"));
        currCar.setCompanyId(resultSet.getInt("company_id"));
        return currCar;
    }

    @Override
    public List<Car> mapRows(ResultSet resultSet) throws SQLException {
        List<Car> carList = new ArrayList<>();

        while(resultSet.next()){
            carList.add(mapRow(resultSet));
        }

        return carList;
    }
}