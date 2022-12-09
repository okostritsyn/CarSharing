package carsharing.controller;

import carsharing.view.AddCompanyView;
import carsharing.view.CompanyListView;
import carsharing.exceptions.EmptyListOfControllersException;
import carsharing.view.View;

import java.util.ArrayList;
import java.util.List;

public class CompanyController extends Controller {
    private final List<Controller> controllers = new ArrayList<>(2);

    public CompanyController(View mainView){
        super(mainView,Controller.COUNTRY_MENU);
        controllers.add(this);
        controllers.add(new CompanyListController(new CompanyListView(),Controller.READ_COUNTRY_LIST));
        controllers.add(new AddCompanyController(new AddCompanyView(),Controller.ADD_COUNTRY));
    }

    @Override
    public boolean canProcess(int action){return this.action == action;}

    @Override
    public int process() {
        int action = super.process();

        if (action != 0) {
            try {
                return processMenu(action,Controller.COUNTRY_MENU,controllers);
            } catch (EmptyListOfControllersException e) {
                System.out.println("There is no controllers to process! "+e);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
