/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Welcome
 */
public class CartDTO {

    Map<String, CarDTO> cart = new HashMap<>();

    public CartDTO() {
    }

    public Map<String, CarDTO> getCart() {
        return cart;
    }

    public int getNumofDay(String rentDate, String returnDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = sdf.parse(rentDate);
        Date secondDate = sdf.parse(returnDate);
        long numofmilisecond = Math.abs(secondDate.getTime() - firstDate.getTime());
        long numofday = TimeUnit.DAYS.convert(numofmilisecond, TimeUnit.MILLISECONDS);
        int days = (int) numofday;
        return days;
    }

    public float getCarPrice(float price, String rentDate, String returnDate) throws Exception {
        int days = getNumofDay(rentDate, returnDate);
        float carPrice = price * days;
        return carPrice;
    }

    public void addToCart(CarDTO car, String rentDate, String returnDate, int maxQuantity) throws Exception {
        if (this.cart.isEmpty()) {
            cart = new HashMap<>();
        }
        if (this.cart.containsKey(car.getCarDetailID()) && car.getRentDate().equals(rentDate) && car.getReturnDate().equals(returnDate)) {
            car.setQuantity(1);
            car.setMaxquantity(maxQuantity);
            float price = car.getPrice();
            car.setTotalPrice(getCarPrice(price, rentDate, returnDate));
        } else {
            car.setRentDate(rentDate);
            car.setReturnDate(returnDate);
            float price = car.getPrice();
            car.setTotalPrice(getCarPrice(price, rentDate, returnDate));
            this.cart.put(car.getCarDetailID(), car);
        }
    }

    public void remove(String getCarDetailID) throws Exception {
        if (this.cart.isEmpty()) {
            return;
        }
        if (this.cart.containsKey(getCarDetailID)) {
            this.cart.remove(getCarDetailID);
        }
    }

    public void changeInCart(String getCarDetailID, String rentDate, String returnDate) throws Exception {
        if (this.cart.containsKey(getCarDetailID)) {
            this.cart.get(getCarDetailID).setRentDate(rentDate);
            this.cart.get(getCarDetailID).setReturnDate(returnDate);
            this.cart.get(getCarDetailID).setTotalPrice(getCarPrice(this.cart.get(getCarDetailID).getPrice(), rentDate, returnDate));
        }
    }

    public float getTotalMoney() throws Exception {
        float total = 0;
        for (CarDTO car : this.cart.values()) {
            total += car.getTotalPrice();
        }
        return total;
    }
}
