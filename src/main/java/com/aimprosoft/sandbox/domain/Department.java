package com.aimprosoft.sandbox.domain;

/**
 * @author BaLiK on 25.03.19
 */
public class Department {
    private Long ID;
    private String name;

    public Department() {
        ID = 0L;
    }

    public Department(Long id) {
        ID = id;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
