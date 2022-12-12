package carsharing.view;

import carsharing.controller.Controller;

public class MainView implements View {

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while (!checkAction(selectedElement, 0, 3)) {
            System.out.println("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement == 0) {
            return Controller.FINISH_ACTION;
        }
        return selectedElement;
    }

    @Override
    public void printInfo() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
    }
}
