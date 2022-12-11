package carsharing.model;

public class Car {
    private String name;
    private Integer id;
    private Integer companyId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int id) {
        this.companyId = id;
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
