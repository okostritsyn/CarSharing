package carsharing.view;
import carsharing.controller.Controller;
import carsharing.model.Company;


public class CompanyCarMenuView implements View {
    private final Company company;

    public CompanyCarMenuView(Company company) {
        this.company = company;
    }

    @Override
    public void printInfo() {
        printMessage(company+" company");
        printMessage("1. Car list");
        printMessage("2. Create a car");
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
            return Controller.COMPANY_MENU;
        }

        return selectedElement+Controller.SHIFT_MENU_CAR;
    }
}
