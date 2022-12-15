package carsharing.controller;

import carsharing.dao.CustomerDAO;
import carsharing.model.Customer;
import carsharing.service.CustomerService;
import carsharing.view.*;

import java.util.List;

public class CustomerController extends Controller {
    protected CustomerController(View view, int action) {
        super(view, action);
    }

    @Override
    public int process() {
        view.printInfo();

        List<Customer> customerList = new CustomerService(new CustomerDAO(getControllerDB())).listAll();
        ((CustomerListView) view).printCustomerList(customerList);
        if (!customerList.isEmpty()) {
            int action = view.readAction();
            int finAction;
            if (action != 0) {
                do {
                    var customer = customerList.get(action - 1);
                    View customerView = new CustomerCarMenuView();
                    Controller customerController = new CustomerCarController(customerView, customer);
                    finAction = customerController.process();
                } while (finAction != Controller.MAIN_MENU_ACTION);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
