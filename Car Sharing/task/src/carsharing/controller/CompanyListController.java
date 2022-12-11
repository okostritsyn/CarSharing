package carsharing.controller;


import carsharing.dao.CompanyDAO;
import carsharing.model.Company;
import carsharing.service.CompanyService;
import carsharing.view.CompanyCarMenuView;
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

        List<Company> companyList = new CompanyService(new CompanyDAO(getControllerDB())).listAll();
        ((CompanyListView) view).printCompanyList(companyList);
        if (companyList.isEmpty()) return Controller.COMPANY_MENU;

        int action = view.readAction();
        int finAction;
        if (action != 0) {
            do {
            var company = companyList.get(action-1);
            View carView = new CompanyCarMenuView(company);
            Controller carController = new CompanyCarController(carView,company);
            finAction = carController.process();
            } while (finAction != Controller.COMPANY_MENU);
        }
        return Controller.COMPANY_MENU;
    }
}
