package carsharing.mapper;

import carsharing.dao.CompanyDAO;
import carsharing.model.Company;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper {
    public Company mapRow(ResultSet resultSet) throws SQLException {
        Company currCompany = new Company();
        currCompany.setName(resultSet.getString("name"));
        return currCompany;
    }


}