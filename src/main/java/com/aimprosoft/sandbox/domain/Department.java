package com.aimprosoft.sandbox.domain;

import javax.persistence.*;

/**
 * @author BaLiK on 25.03.19
 */
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ID;

    @Column(name = "name")
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
