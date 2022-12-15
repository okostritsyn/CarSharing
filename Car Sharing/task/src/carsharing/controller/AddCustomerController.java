package carsharing.controller;

import carsharing.dao.CustomerDAO;
import carsharing.model.Customer;
import carsharing.service.CustomerService;
import carsharing.view.View;

public class AddCustomerController extends Controller{

    protected AddCustomerController(View view, int action) {
        super(view, action);
    }

    private String getName(View view){
        return view.collectDataFromUser("Enter the customer name:","");
    }

    private void createNewCustomer(String name){
        var customer = new Customer();
        customer.setName(name);
        var status = new CustomerService(new CustomerDAO(getControllerDB())).save(customer);
        if (status) {
            view.printMessage("The customer " + name + " successfully created!");
        } else{
            view.printMessage("The customer " + name + " has not been created! ");
        }
        view.printMessage("");
    }

    @Override
    public int process() {
        view.printInfo();
        String currName = getName(view);
        if (!currName.isEmpty()) {
            createNewCustomer(currName);
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
