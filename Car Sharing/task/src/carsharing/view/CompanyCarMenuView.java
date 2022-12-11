package carsharing.view;
import carsharing.controller.Controller;
import carsharing.model.Company;


public class CompanyCarMenuView implements View {
    private Company company;

    public CompanyCarMenuView(Company company) {
        this.company = company;
    }

    @Override
    public void printInfo() {
        System.out.println(company+" company");
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
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
            return Controller.COMPANY_MENU;
        }

        return selectedElement+Controller.SHIFT_MENU_CAR;
    }
}
