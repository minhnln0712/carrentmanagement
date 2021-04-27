/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Welcome
 */
public class RentalDTO implements Serializable{
    private String rentalID,email,discountID;
    private float totalPrice;
    private Date createDate;
    private boolean status;

    public RentalDTO() {
    }

    public RentalDTO(String rentalID, String email, String discountID, float totalPrice, Date createDate, boolean status) {
        this.rentalID = rentalID;
        this.email = email;
        this.discountID = discountID;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.status = status;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the discountID
     */
    public String getDiscountID() {
        return discountID;
    }

    /**
     * @param discountID the discountID to set
     */
    public void setDiscountID(String discountID) {
        this.discountID = discountID;
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

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
