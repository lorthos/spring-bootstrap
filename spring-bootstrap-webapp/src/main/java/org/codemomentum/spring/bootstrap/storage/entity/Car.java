package org.codemomentum.spring.bootstrap.storage.entity;

/**
 * @author Halit
 */
public class Car {

    private Long id;

    private String model;

    private String color;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
