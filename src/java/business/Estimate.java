/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

/**
 *
 * @author tmdel
 */
public class Estimate {
    private int estimateID, userID, year;
    private String make, model, wrapDescription;

    public Estimate() {
    }

    public Estimate(int estimateID, int userID, int year, String make, String model, String wrapDescription) {
        this.estimateID = estimateID;
        this.userID = userID;
        this.year = year;
        this.make = make;
        this.model = model;
        this.wrapDescription = wrapDescription;
    }

    public int getEstimateID() {
        return estimateID;
    }

    public void setEstimateID(int estimateID) {
        this.estimateID = estimateID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWrapDescription() {
        return wrapDescription;
    }

    public void setWrapDescription(String wrapDescription) {
        this.wrapDescription = wrapDescription;
    }
    
    
}
