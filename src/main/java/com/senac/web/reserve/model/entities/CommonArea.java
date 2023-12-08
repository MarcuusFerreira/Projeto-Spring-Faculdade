package com.senac.web.reserve.model.entities;

import jakarta.persistence.*;

@Table(name = "common_area")
@Entity(name = "common_area")
public class CommonArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75, unique = true)
    private String name;
    @Column(length = 255)
    private String description;

    public CommonArea(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CommonArea() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Id:" + this.getId() +
                "\nName:" + this.getName() +
                "\nDescription:" + this.getDescription();
    }
}
