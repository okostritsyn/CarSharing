package carsharing.model;

import lombok.Data;

@Data
public class Car {
    private String name;
    private Integer id;
    private Integer companyId;

    @Override
    public String toString() {
        return getName();
    }
}
