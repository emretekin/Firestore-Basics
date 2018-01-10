package com.mobilemovement.firestorebasics.models;

/**
 * Created by emretekin on 08/01/18.
 */

public class Users extends UserId {

    private String name, status;

    public Users() {

    }

    public Users(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
