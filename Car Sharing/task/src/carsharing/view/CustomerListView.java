package carsharing.view;

import carsharing.controller.Controller;
import carsharing.model.Customer;

import java.util.List;

public class CustomerListView implements View {
    private List<Customer> customerList;

    @Override
    public void printInfo() {
        System.out.println("Customer list:");
    }

    public void printCompanyList(List<Customer> customerList) {
        this.customerList = customerList;

        if (customerList.isEmpty()){
            System.out.println("The customer list is empty!");
            System.out.println("");
            return;
        }

        for (int i = 0, companyListSize = customerList.size(); i < companyListSize; i++) {
            Customer currCustomer = customerList.get(i);
            System.out.println((i+1)+". "+currCustomer);
        }
        System.out.println("0. Back");
        System.out.println("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.customerList.size())){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement;
    }
}