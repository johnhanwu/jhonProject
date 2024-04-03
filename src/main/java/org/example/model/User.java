package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int id;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name="last_name")
    private  String  lastName;
    private int age;
    private String address;
    private int height;
    private int weight;


    public User(int id,String firstName, String lastName, int age, String address, int height, int weight) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
