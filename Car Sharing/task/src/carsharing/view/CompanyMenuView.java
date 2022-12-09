package carsharing.view;
import carsharing.controller.Controller;


public class CompanyMenuView implements View {
    @Override
    public void printInfo() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,2)){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement  + Controller.SHIFT_MENU_COUNTRY;
    }
}
