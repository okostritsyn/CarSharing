package carsharing.controller;

import carsharing.exceptions.EmptyListOfControllersException;
import carsharing.view.View;

import java.util.List;

public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int FINISH_ACTION = 9;
    public static final int COMPANY_MENU = 1;
    public static final int ADD_COMPANY = 3;
    public static final int READ_COMPANY_LIST = 2;

    public static final int SHIFT_MENU_COMPANY = 1;
    public static final int SHIFT_MENU_CAR = 4;

    public static final int COMPANY_CAR_MENU = 4;
    public static final int ADD_CAR = 6;
    public static final int READ_CAR_LIST = 5;

    protected View view;
    protected int action;
    protected static ControllerDB controllerDB;

    protected Controller(View view, int action){
        this.view = view;
        this.action = action;
    }

    public static ControllerDB getControllerDB() {
        return controllerDB;
    }

    public static void initialization(String currDBName) {
        controllerDB = new ControllerDB(currDBName);
        controllerDB.initDB();
    }

    public boolean canProcess(int action) {
        return this.action == action;
    }

    public int process() {
        view.printInfo();
        return view.readAction();
    }

    public static void finishWork() {

    }

    public int processMenu(int action, int finishAction, List<Controller> controllers) throws EmptyListOfControllersException {
        if (controllers.isEmpty()){
            throw new EmptyListOfControllersException("There is no controllers to process! ");
        }
        do {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process();
                }
            }
        } while (action != finishAction);
        return action;
    }
}
