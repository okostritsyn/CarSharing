package carsharing.view;
import carsharing.controller.Controller;


public class CompanyMenuView implements View {
    @Override
    public void printInfo() {
        printMessage("1. Company list");
        printMessage("2. Create a company");
        printMessage("0. Back");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,2)){
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement  + Controller.SHIFT_MENU_COMPANY;
    }
}
