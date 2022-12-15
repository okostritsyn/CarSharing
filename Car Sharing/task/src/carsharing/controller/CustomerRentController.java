package carsharing.controller;


import carsharing.dao.CarDAO;
import carsharing.dao.CompanyDAO;
import carsharing.dao.CustomerDAO;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.service.CarService;
import carsharing.service.CompanyService;
import carsharing.service.CustomerService;
import carsharing.view.*;

import java.util.List;

public class CustomerRentController extends Controller {
    private final Customer customer;

    protected CustomerRentController(View view, int action, Customer customer) {
        super(view, action);
        this.customer = customer;
    }

    @Override
    public int process() {
        var customerHasRentCar = customer.getRentedCarId()!=null;

        if (customerHasRentCar) {
            view.printMessage("You've already rented a car!");
            return Controller.CUSTOMER_CAR_MENU;
        }

        view.printInfo();

        List<Company> companyList = new CompanyService(new CompanyDAO(getControllerDB())).listAll();
        ((CompanyListView) view).printCompanyList(companyList);
        if (companyList.isEmpty()) return Controller.CUSTOMER_CAR_MENU;

        int action = view.readAction();
        if (action != 0) {
            var company = companyList.get(action - 1);

            CustomerRentCarListView carView = new CustomerRentCarListView(company);
            carView.printInfo();
            List<Car> carList = new CarService(new CarDAO(getControllerDB())).listByCompanyId(company.getId());
            carView.printCarList(carList);
            if (carList.isEmpty()) return Controller.CUSTOMER_CAR_MENU;
            int currAction = carView.readAction();

            if (currAction == 0) return Controller.CUSTOMER_CAR_MENU;

            var car = carList.get(currAction - 1);
            var status = new CustomerService(new CustomerDAO(getControllerDB())).rentCar(customer, car.getId());
            if (status) {
                view.printMessage("You rented '" +
                        car.getName() + "'");
            }
            view.printMessage("");

        }
        return Controller.CUSTOMER_CAR_MENU;
    }
}
