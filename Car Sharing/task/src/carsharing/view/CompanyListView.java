package carsharing.view;

import carsharing.model.Company;

import java.util.List;

public class CompanyListView implements View {
    @Override
    public void printInfo() {
        System.out.println("List of companies:");
    }

    public void printCompanyList(List<Company> companyList) {

        if (companyList.isEmpty()){
            System.out.println("The company list is empty!");
            return;
        }

        for (int i = 0, companyListSize = companyList.size(); i < companyListSize; i++) {
            Company currCompany = companyList.get(i);
            System.out.println((i+1)+". "+currCompany);
        }
        System.out.println("");
    }
}