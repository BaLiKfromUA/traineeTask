package com.aimprosoft.sandbox.database.department;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author BaLiK on 25.03.19
 */
public class Department {
    @Min(value = 0, message = "ID should be greater than -1")
    private Long ID;

    @Size(min=6, max=128, message="Name should be between 6 - 128 characters.")
    private String name;

    public Department(Long id) {
        ID=id;
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
