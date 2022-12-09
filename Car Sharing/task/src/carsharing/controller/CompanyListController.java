package carsharing.controller;


import carsharing.dao.CompanyDAO;
import carsharing.model.Company;
import carsharing.service.CompanyService;
import carsharing.view.CompanyListView;
import carsharing.view.View;

import java.sql.SQLException;
import java.util.List;


public class CompanyListController extends Controller{
    protected CompanyListController(View view, int action) {
        super(view, action);
    }

    @Override
    public int process() {
        view.printInfo();

        List<Company> companyList = null;
        try {
            companyList = new CompanyService(new CompanyDAO(getControllerDB())).listAll();
            ((CompanyListView) view).printCompanyList(companyList);
            if (companyList.isEmpty()) return Controller.COUNTRY_MENU;
        } catch (SQLException e) {
            System.out.println("An error while fetching list ");
            e.printStackTrace();
        }

        return Controller.COUNTRY_MENU;
    }
}
