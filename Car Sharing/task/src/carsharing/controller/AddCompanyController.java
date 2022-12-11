package carsharing.controller;

import carsharing.dao.CompanyDAO;
import carsharing.model.Company;
import carsharing.service.CompanyService;
import carsharing.view.View;

public class AddCompanyController extends Controller{
    protected AddCompanyController(View view, int action) {
        super(view, action);
    }

    private String getName(View view){
        return view.collectDataFromUser("Enter the company name:","");
    }

    private void createNewCompany(String name){
        var company = new Company();
        company.setName(name);
        var status = new CompanyService(new CompanyDAO(getControllerDB())).save(company);
        if (status) {
            System.out.println("The company " + name + " successfully created!");
        } else{
            System.out.println("The company " + name + " has not been created! ");
        }
        System.out.println("");
    }

    @Override
    public int process() {
        view.printInfo();
        String currName = getName(view);
        if (!currName.isEmpty()) {
            createNewCompany(currName);
        }
        return Controller.COMPANY_MENU;
    }
}
