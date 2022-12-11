package carsharing.controller;


import carsharing.dao.CarDAO;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.service.CarService;
import carsharing.view.CompanyCarListView;
import carsharing.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompanyCarListController extends Controller{
    private Company company;

    protected CompanyCarListController(View view, int action, Company company) {
        super(view, action);
        this.company = company;
    }

    @Override
    public int process() {
        view.printInfo();

        List<Car> carList = new CarService(new CarDAO(getControllerDB())).listByCompanyId(company.getId());
        ((CompanyCarListView) view).printCarList(carList);
        if (carList.isEmpty()) return Controller.COMPANY_CAR_MENU;

        return Controller.COMPANY_CAR_MENU;
    }
}