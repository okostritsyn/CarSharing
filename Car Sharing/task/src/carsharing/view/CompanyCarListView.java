package carsharing.view;

import carsharing.controller.Controller;
import carsharing.model.Car;

import java.util.List;

public class CompanyCarListView implements View {
    private List<Car> carList;

    @Override
    public void printInfo() {
        printMessage("Car list:");
    }

    public void printCarList(List<Car> carList) {
        this.carList = carList;

        if (carList.isEmpty()){
            printMessage("The car list is empty!");
            printMessage("");
            return;
        }

        for (int i = 0, companyListSize = carList.size(); i < companyListSize; i++) {
            Car currCar = carList.get(i);
            printMessage((i+1)+". "+currCar);
        }

        printMessage("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.carList.size())){
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.COMPANY_MENU;
        }

        return selectedElement;
    }
}