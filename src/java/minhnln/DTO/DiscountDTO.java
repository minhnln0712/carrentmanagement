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
public class DiscountDTO implements Serializable{
    private String discountID;
    private float discountPercent;
    private Date startDate;
    private Date expireDate;

    public DiscountDTO() {
    }

    public DiscountDTO(String discountID, float discountPercent, Date startDate, Date expireDate) {
        this.discountID = discountID;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.expireDate = expireDate;
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
     * @return the discountPercent
     */
    public float getDiscountPercent() {
        return discountPercent;
    }

    /**
     * @param discountPercent the discountPercent to set
     */
    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
}
