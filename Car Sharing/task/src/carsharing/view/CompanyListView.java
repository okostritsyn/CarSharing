package carsharing.view;

import carsharing.controller.Controller;
import carsharing.model.Company;

import java.util.List;

public class CompanyListView implements View {
    private List<Company> companyList;

    @Override
    public void printInfo() {
        System.out.println("List of companies:");
    }

    public void printCompanyList(List<Company> companyList) {
        this.companyList = companyList;

        if (companyList.isEmpty()){
            System.out.println("The company list is empty!");
            System.out.println("");
            return;
        }

        for (int i = 0, companyListSize = companyList.size(); i < companyListSize; i++) {
            Company currCompany = companyList.get(i);
            System.out.println((i+1)+". "+currCompany);
        }
        System.out.println("0. Back");
        System.out.println("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.companyList.size())){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement;
    }
}