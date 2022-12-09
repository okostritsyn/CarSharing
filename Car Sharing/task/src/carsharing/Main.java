package carsharing;
import carsharing.controller.Controller;
import carsharing.controller.MainController;
import carsharing.view.MainView;
import carsharing.view.View;

public class Main {
    public static void main(String[] args) {
        String currDbName = "carsharing.mv";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFileName") && (i+1)<args.length){
                currDbName = args[i+1].isEmpty()?currDbName:args[i+1];
            }
        }

        Controller.initialization(currDbName);

        View mainView = new MainView();
        Controller mainController = new MainController(mainView);
        mainController.process();

        Controller.finishWork();
    }
}