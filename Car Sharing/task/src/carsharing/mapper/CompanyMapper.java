package carsharing.mapper;

import carsharing.model.Company;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyMapper implements Mapper<Company>{
    @Override
    public Company mapRow(ResultSet resultSet) throws SQLException {
        Company currCompany = new Company();
        currCompany.setName(resultSet.getString("name"));
        currCompany.setId(resultSet.getInt("id"));
        return currCompany;
    }

    @Override
    public List<Company> mapRows(ResultSet resultSet) throws SQLException {
        List<Company> companyList = new ArrayList<>();

        while(resultSet.next()){
            companyList.add(mapRow(resultSet));
        }

        return companyList;
    }
}