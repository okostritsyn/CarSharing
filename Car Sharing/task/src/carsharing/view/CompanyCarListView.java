package carsharing.view;

import carsharing.controller.Controller;
import carsharing.model.Car;

import java.util.List;

public class CompanyCarListView implements View {
    private List<Car> carList;

    @Override
    public void printInfo() {
        System.out.println("Car list:");
    }

    public void printCarList(List<Car> carList) {
        this.carList = carList;

        if (carList.isEmpty()){
            System.out.println("The car list is empty!");
            System.out.println("");
            return;
        }

        for (int i = 0, companyListSize = carList.size(); i < companyListSize; i++) {
            Car currCar = carList.get(i);
            System.out.println((i+1)+". "+currCar);
        }

        System.out.println("");
    }

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while(!checkAction(selectedElement,0,this.carList.size())){
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement==0) {
            return Controller.COMPANY_MENU;
        }

        return selectedElement;
    }
}