package carsharing.view;
import carsharing.controller.Controller;
import carsharing.model.Customer;


public class CustomerCarMenuView implements View {
    private Customer customer;

    public CustomerCarMenuView(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void printInfo() {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,3)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement+Controller.SHIFT_MENU_CUSTOMER;
    }
}
