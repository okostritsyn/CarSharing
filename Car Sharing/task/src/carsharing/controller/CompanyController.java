package carsharing.controller;

import carsharing.view.AddCompanyView;
import carsharing.view.CompanyListView;
import carsharing.exceptions.EmptyListOfControllersException;
import carsharing.view.View;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class CompanyController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(3);

    public CompanyController(View mainView){
        super(mainView,Controller.COMPANY_MENU);
        controllers.add(this);
        controllers.add(new CompanyListController(new CompanyListView(),Controller.READ_COMPANY_LIST));
        controllers.add(new AddCompanyController(new AddCompanyView(),Controller.ADD_COMPANY));
    }

    @Override
    public boolean canProcess(int action){return this.action == action;}

    @Override
    public int process() {
        int action = super.process();

        if (action != 0) {
            try {
                return processMenu(action,Controller.COMPANY_MENU,controllers);
            } catch (EmptyListOfControllersException e) {
              // log.warn("CompanyController: There is no controllers to process! "+e);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
