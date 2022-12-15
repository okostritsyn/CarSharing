package carsharing.controller;

import carsharing.dao.CarDAO;
import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.service.CarService;
import carsharing.view.View;

public class AddCarController extends Controller{
    private final Company company;

    protected AddCarController(View view, int action, Company company) {
        super(view, action);
        this.company = company;
    }

    private String getName(View view){
        return view.collectDataFromUser("Enter the car name:","");
    }

    private void createNewCar(String name){
        var car = new Car();
        car.setName(name);
        car.setCompanyId(company.getId());
        var status = new CarService(new CarDAO(getControllerDB())).save(car);
        if (status) {
            view.printMessage("The car " + name + " successfully created!");
        } else{
            view.printMessage("The car " + name + " has not been created!");
        }
        view.printMessage("");
    }

    @Override
    public int process() {
        view.printInfo();
        String currName = getName(view);
        if (!currName.isEmpty()) {
            createNewCar(currName);
        }
        return Controller.COMPANY_CAR_MENU;
    }
}
