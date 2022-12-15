package carsharing.view;

import carsharing.controller.Controller;
import carsharing.model.Customer;

import java.util.List;

public class CustomerListView implements View {
    private List<Customer> customerList;

    @Override
    public void printInfo() {
        printMessage("Customer list:");
    }

    public void printCustomerList(List<Customer> customerList) {
        this.customerList = customerList;

        if (customerList.isEmpty()){
            printMessage("The customer list is empty!");
            printMessage("");
            return;
        }

        for (int i = 0, companyListSize = customerList.size(); i < companyListSize; i++) {
            Customer currCustomer = customerList.get(i);
            printMessage((i+1)+". "+currCustomer);
        }
        printMessage("0. Back");
        printMessage("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.customerList.size())){
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement;
    }
}