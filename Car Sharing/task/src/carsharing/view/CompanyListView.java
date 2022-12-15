package carsharing.view;

import carsharing.controller.Controller;
import carsharing.model.Company;
import java.util.List;

public class CompanyListView implements View {
    private List<Company> companyList;

    @Override
    public void printInfo() {
        printMessage("List of companies:");
    }

    public void printCompanyList(List<Company> companyList) {
        this.companyList = companyList;

        if (companyList.isEmpty()){
            printMessage("The company list is empty!");
            printMessage("");
            return;
        }

        for (int i = 0, companyListSize = companyList.size(); i < companyListSize; i++) {
            Company currCompany = companyList.get(i);
            printMessage((i+1)+". "+currCompany);
        }
        printMessage("0. Back");
        printMessage("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.companyList.size())){
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.MAIN_MENU_ACTION;
        }

        return selectedElement;
    }
}