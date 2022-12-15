package carsharing.view;

import carsharing.controller.Controller;

public class MainView implements View {

    @Override
    public int readAction() {
        int selectedElement = View.super.readAction();

        while (!checkAction(selectedElement, 0, 3)) {
            printMessage("incorrect number! Make your choice: ");
            selectedElement = View.super.readAction();
        }

        if (selectedElement == 0) {
            return Controller.FINISH_ACTION;
        }
        return selectedElement;
    }

    @Override
    public void printInfo() {
        printMessage("1. Log in as a manager");
        printMessage("2. Log in as a customer");
        printMessage("3. Create a customer");
        printMessage("0. Exit");
    }
}
