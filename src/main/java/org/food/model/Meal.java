package org.food.model;


import jakarta.persistence.*;

@Entity
@Table(name = "meal", schema = "mydb")
public class Meal {
    // TODO: 18.10.2023 gg 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private double price;
    private int time;
    private String date;


    public Meal(){};

    public Meal(String nameOfMeal, double priceOfMeal, int cookingTime){
        this.name = nameOfMeal;
        this.price = priceOfMeal;
        this.time = cookingTime;
    }
    public Meal(String nameOfMeal, double priceOfMeal, int cookingTime, String id){
        this.name = nameOfMeal;
        this.price = priceOfMeal;
        this.time = cookingTime;
        this.id = id;
    }



    public void setName(String name) {
       this.name = name;
    }

   public void setPrice(double price) {
       this.price = price;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }
    public String toString() {
        return "Meal{" +
                "nameOfMeal='" + name + '\'' +
                ", priceOfMeal=" + price +
                ", cookingTime=" + time +
                ", createData='" + date + '\'' +
                '}';
    }
}
