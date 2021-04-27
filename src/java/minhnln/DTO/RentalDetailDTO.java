/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author Welcome
 */
public class RentalDetailDTO implements Serializable {

    private String rentalDetailID, rentalID, carID, carDetailID, licenceID, carName, carImage;
    private Date rentDate, returnDate;

    public RentalDetailDTO() {
    }

    public RentalDetailDTO(String rentalDetailID, String rentalID, String carID, String carDetailID, String licenceID, String carName, String carImage, Date rentDate, Date returnDate) {
        this.rentalDetailID = rentalDetailID;
        this.rentalID = rentalID;
        this.carID = carID;
        this.carDetailID = carDetailID;
        this.licenceID = licenceID;
        this.carName = carName;
        this.carImage = carImage;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    /**
     * @return the rentalDetailID
     */
    public String getRentalDetailID() {
        return rentalDetailID;
    }

    /**
     * @param rentalDetailID the rentalDetailID to set
     */
    public void setRentalDetailID(String rentalDetailID) {
        this.rentalDetailID = rentalDetailID;
    }

    /**
     * @return the rentalID
     */
    public String getRentalID() {
        return rentalID;
    }

    /**
     * @param rentalID the rentalID to set
     */
    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
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
     * @return the licenceID
     */
    public String getLicenceID() {
        return licenceID;
    }

    /**
     * @param licenceID the licenceID to set
     */
    public void setLicenceID(String licenceID) {
        this.licenceID = licenceID;
    }

    /**
     * @return the rentDate
     */
    public Date getRentDate() {
        return rentDate;
    }

    /**
     * @param rentDate the rentDate to set
     */
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

}
