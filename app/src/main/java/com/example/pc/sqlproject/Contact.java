package com.example.pc.sqlproject;

/**
 * Created by PC on 10/13/2017.
 */

public class Contact {

    int id;
    String name,phone,address,gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Contact(int id, String name, String phone, String address, String gender) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
}
