package com.project.partyraumkasse;

public class Pizza {
    private String name;
    private String pizza;
    private String extras;
    private String id;

    public Pizza(String name, String pizza, String extras) {
        this.name = name;
        this.pizza = pizza;
        this.extras = extras;
    }

    public Pizza(String name, String pizza, String extras, String id){
        this.name = name;
        this.pizza = pizza;
        this.extras = extras;
        this.id = id;
    }

    public Pizza() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
