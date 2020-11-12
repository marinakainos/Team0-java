package com.kainos.team0.employee_stuff;

public class Project {

    private int id;
    private String name;


    public Project(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
