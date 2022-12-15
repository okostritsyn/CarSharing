package carsharing.view;

import carsharing.model.Car;
import carsharing.model.Company;

import java.util.List;

public class CustomerRentCarListView implements View {
    private List<Car> carList;
    private final Company company;

    public CustomerRentCarListView(Company company) {
        this.company = company;
    }

    @Override
    public void printInfo() {
        printMessage("Choose a car:");
    }

    public void printCarList(List<Car> carList) {
        this.carList = carList;

        if (carList.isEmpty()){
            printMessage("No available cars in the "+company.getName()+" company.");
            printMessage("");
            return;
        }

        for (int i = 0, companyListSize = carList.size(); i < companyListSize; i++) {
            Car currCar = carList.get(i);
            printMessage((i+1)+". "+currCar);
        }

        printMessage("0.Back");
        printMessage("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.carList.size())){
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        return selectedElement;
    }
}