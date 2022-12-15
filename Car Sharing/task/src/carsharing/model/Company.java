package carsharing.model;

import lombok.Data;

@Data
public class Company {
    private String name;
    private Integer id;

    @Override
    public String toString() {
        return getName();
    }

}
