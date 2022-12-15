package carsharing.controller;

import carsharing.exceptions.EmptyListOfControllersException;
import carsharing.model.Company;
import carsharing.view.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class CompanyCarController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(3);

    public CompanyCarController(View mainView, Company company){
        super(mainView,Controller.COMPANY_CAR_MENU);
        controllers.add(this);
        controllers.add(new CompanyCarListController(new CompanyCarListView(),Controller.READ_CAR_LIST,company));
        controllers.add(new AddCarController(new AddCarView(),Controller.ADD_CAR,company));
    }

    @Override
    public boolean canProcess(int action){return this.action == action;}

    @Override
    public int process() {
        int action = super.process();
        int finishAction = Controller.COMPANY_MENU;
        if (action != 0) {
            try {
                return processMenu(action,finishAction,controllers);
            } catch (EmptyListOfControllersException e) {
               // log.warn("CompanyCarController: There is no controllers to process! "+e);
                action = finishAction;
            }
        }
        return action+Controller.SHIFT_MENU_CAR;
    }
}
