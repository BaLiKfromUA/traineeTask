package com.aimprosoft.sandbox.controller.data;

/**
 * @author BaLiK on 29.03.19
 */
public class DepartmentData {
    private final String id;
    private final String name;

    public DepartmentData(String name) {
        this.id = "1";
        this.name = name;
    }

    public DepartmentData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
