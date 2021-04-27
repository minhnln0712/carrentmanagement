/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.io.Serializable;

/**
 *
 * @author Welcome
 */
public class CarDTO implements Serializable {

    private String carID, carName, carImage, color, categoryName, rentDate, returnDate, licenceID, carDetailID;
    private int year, categoryID, quantity, maxquantity;
    private float price, totalPrice;

    public CarDTO() {
    }

    public CarDTO(String carID, String carName, String carImage, String color, String categoryName, int year, int categoryID, int quantity, int maxquantity, float price) {
        this.carID = carID;
        this.carName = carName;
        this.carImage = carImage;
        this.color = color;
        this.categoryName = categoryName;
        this.year = year;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.maxquantity = maxquantity;
        this.price = price;
    }
    
    

    public CarDTO(String carID, String carName, String carImage, String color, String categoryName, String rentDate, String returnDate, String licenceID, int year, int categoryID, int quantity, int maxquantity, float price, float totalPrice) {
        this.carID = carID;
        this.carName = carName;
        this.carImage = carImage;
        this.color = color;
        this.categoryName = categoryName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.licenceID = licenceID;
        this.year = year;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.maxquantity = maxquantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public CarDTO(String carID, String carName, String carImage, String licenceID, String carDetailID, float price) {
        this.carID = carID;
        this.carName = carName;
        this.carImage = carImage;
        this.licenceID = licenceID;
        this.carDetailID = carDetailID;
        this.price = price;
    }

    /**
     * @return the carID
     */
    public String getCarID() {
        return carID;
    }

    /**
     * @param carID the carID to set
     */
    public void setCarID(String carID) {
        this.carID = carID;
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the carImage
     */
    public String getCarImage() {
        return carImage;
    }

    /**
     * @param carImage the carImage to set
     */
    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the rentDate
     */
    public String getRentDate() {
        return rentDate;
    }

    /**
     * @param rentDate the rentDate to set
     */
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the licenceID
     */
    public String getLicenseID() {
        return licenceID;
    }

    /**
     * @param licenceID the licenceID to set
     */
    public void setLicenseID(String licenceID) {
        this.licenceID = licenceID;
    }

    /**
     * @return the carDetailID
     */
    public String getCarDetailID() {
        return carDetailID;
    }

    /**
     * @param carDetailID the carDetailID to set
     */
    public void setCarDetailID(String carDetailID) {
        this.carDetailID = carDetailID;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the maxquantity
     */
    public int getMaxquantity() {
        return maxquantity;
    }

    /**
     * @param maxquantity the maxquantity to set
     */
    public void setMaxquantity(int maxquantity) {
        this.maxquantity = maxquantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

}
