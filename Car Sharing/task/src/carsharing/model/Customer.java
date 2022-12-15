package carsharing.model;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private Integer id;
    private Integer rentedCarId = null;

    @Override
    public String toString() {
        return getName();
    }
}
