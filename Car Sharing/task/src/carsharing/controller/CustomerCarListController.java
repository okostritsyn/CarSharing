package carsharing.controller;


import carsharing.dao.CarDAO;
import carsharing.dao.CompanyDAO;
import carsharing.model.Customer;
import carsharing.service.CarService;
import carsharing.service.CompanyService;
import carsharing.view.View;


public class CustomerCarListController extends Controller{
    private final Customer customer;

    protected CustomerCarListController(View view, int action, Customer customer) {
        super(view, action);
        this.customer = customer;
    }

    @Override
    public int process() {
        view.printInfo();

        if (customer.getRentedCarId() == null){
            view.printMessage("You didn't rent a car!");
        } else {
            var car = new CarService(new CarDAO(getControllerDB())).getById(customer.getRentedCarId());
            var company = new CompanyService(new CompanyDAO(getControllerDB())).getById(car.getCompanyId());

            view.printMessage("Your rented car:\n" +
                    car.getName() + "\n" +
                    "Company:\n" +
                    company.getName() + "\n");
        }
        return Controller.CUSTOMER_CAR_MENU;
    }
}
