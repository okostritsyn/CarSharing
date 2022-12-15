package carsharing.controller;

import carsharing.view.*;
import carsharing.exceptions.EmptyListOfControllersException;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class MainController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(4);

    public MainController(View mainView){
        super(mainView,Controller.MAIN_MENU_ACTION);
        controllers.add(this);
        controllers.add(new CompanyController(new CompanyMenuView()));
        controllers.add(new CustomerController(new CustomerListView(),Controller.READ_CUSTOMER_LIST));
        controllers.add(new AddCustomerController(new AddCustomerView(),Controller.ADD_CUSTOMER));
    }

    @Override
    public int process() {
        int action = super.process();
        int finishAction = Controller.FINISH_ACTION;

        try {
            return processMenu(action,finishAction,controllers);
        } catch (EmptyListOfControllersException e) {
            //log.warn("MainController: There is no controllers to process! "+e);
            action = finishAction;
        }

        return action;
    }
}
