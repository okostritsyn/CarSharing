package carsharing.model;

public class Customer {
    private String name;
    private Integer id;
    private Integer rentedCarId ;

    public Integer getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(int id) {
        this.rentedCarId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getName();
    }
}
