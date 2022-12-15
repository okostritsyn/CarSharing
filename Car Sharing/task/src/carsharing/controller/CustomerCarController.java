package carsharing.controller;

import carsharing.exceptions.EmptyListOfControllersException;
import carsharing.model.Customer;
import carsharing.view.*;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class CustomerCarController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(4);

    public CustomerCarController(View mainView, Customer customer){
        super(mainView,Controller.CUSTOMER_CAR_MENU);
        controllers.add(this);
        controllers.add(new CustomerRentController(new CompanyListView(),Controller.CUSTOMER_RENT_CAR,customer));
        controllers.add(new ReturnRentCarController(new ReturnRentCarView(),Controller.CUSTOMER_RETURN_CAR,customer));
        controllers.add(new CustomerCarListController(new CustomerCarListView(),Controller.CUSTOMER_LIST_CAR,customer));
    }

    @Override
    public boolean canProcess(int action){return this.action == action;}

    @Override
    public int process() {
        int action = super.process();
        if (action != 0) {
            try {
                return processMenu(action, Controller.MAIN_MENU_ACTION,controllers);
            } catch (EmptyListOfControllersException e) {
                //log.warn("CustomerCarController: There is no controllers to process! "+e);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
