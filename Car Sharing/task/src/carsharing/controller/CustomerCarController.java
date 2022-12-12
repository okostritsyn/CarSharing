package carsharing.controller;

import carsharing.exceptions.EmptyListOfControllersException;
import carsharing.model.Company;
import carsharing.model.Customer;
import carsharing.view.AddCarView;
import carsharing.view.CompanyCarListView;
import carsharing.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomerCarController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(4);

    public CustomerCarController(View mainView, Customer customer){
        super(mainView,Controller.CUSTOMER_CAR_MENU);
        controllers.add(this);
       // controllers.add(new CompanyCarListController(new CompanyCarListView(),Controller.CUSTOMER_LIST_CAR,company));
       // controllers.add(new AddCarController(new AddCarView(),Controller.ADD_CAR,company));
       // controllers.add(new CompanyCarListController(new CompanyCarListView(),Controller.READ_CAR_LIST,company));
    }

    @Override
    public boolean canProcess(int action){return this.action == action;}

    @Override
    public int process() {
        int action = super.process();
        int finishAction = Controller.MAIN_MENU_ACTION;
        if (action != 0) {
            try {
                return processMenu(action,finishAction,controllers);
            } catch (EmptyListOfControllersException e) {
                System.out.println("There is no controllers to process! "+e);
                action = finishAction;
            }
        }
        return action;
    }
}
