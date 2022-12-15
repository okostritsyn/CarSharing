package carsharing.view;
import carsharing.controller.Controller;

public class CustomerCarMenuView implements View {

    public CustomerCarMenuView() {

    }

    @Override
    public void printInfo() {
        printMessage("1. Rent a car");
        printMessage("2. Return a rented car");
        printMessage("3. My rented car");
        printMessage("0. Back");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,3)){
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement+Controller.SHIFT_MENU_CUSTOMER;
    }
}
