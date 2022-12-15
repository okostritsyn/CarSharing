package carsharing.controller;

import carsharing.dao.CustomerDAO;
import carsharing.model.Customer;
import carsharing.service.CustomerService;
import carsharing.view.View;

public class ReturnRentCarController extends Controller{
    private final Customer customer;

    protected ReturnRentCarController(View view, int action, Customer customer) {
        super(view, action);
        this.customer = customer;
    }

    @Override
    public int process() {
        view.printInfo();
        var customerHasRentCar = customer.getRentedCarId()!=null;

        if (!customerHasRentCar) {
            view.printMessage("You didn't rent a car!");
        } else {
            var status = new CustomerService(new CustomerDAO(getControllerDB())).returnCar(customer);
            if (status) view.printMessage("You've returned a rented car!");
        }
        return Controller.CUSTOMER_CAR_MENU;
    }
}
