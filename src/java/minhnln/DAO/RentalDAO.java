/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import minhnln.DTO.RentalDTO;
import minhnln.utils.db;

/**
 *
 * @author Welcome
 */
public class RentalDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public void CloseConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean createRental(String rentalID, String email, float totalPrice, Date createDate, String discountID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblRental(RentalID,Email,totalPrice,createDate,status,DiscountID) "
                    + "VALUES(?,?,?,?,1,?) ";
            stm = con.prepareStatement(sql);
            stm.setString(1, rentalID);
            stm.setString(2, email);
            stm.setFloat(3, totalPrice);
            stm.setDate(4, createDate);
            stm.setString(5, discountID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            CloseConnection();
        }
    }

    public String getLastRentalID() throws Exception {
        String id = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 RentalID "
                    + "FROM tblRental "
                    + "ORDER BY RentalID DESC ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getString("RentalID");
            }
        } finally {
            CloseConnection();
            return id;
        }
    }

    List<RentalDTO> list;

    public List<RentalDTO> getAllRental() throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "SELECT RentalID,Email,DiscountID,totalPrice,createDate,Status "
                    + "FROM tblRental "
                    + "WHERE Status = 1 "
                    + "ORDER BY createDate DESC ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RentalDTO(rs.getString("RentalID"), rs.getString("Email"), rs.getString("DiscountID"), rs.getFloat("totalPrice"), rs.getDate("createDate"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<RentalDTO> getAllRentalForUser(String email) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "SELECT RentalID,Email,DiscountID,totalPrice,createDate,Status "
                    + "FROM tblRental "
                    + "WHERE Status = 1 AND Email = ? "
                    + "ORDER BY createDate DESC ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RentalDTO(rs.getString("RentalID"), rs.getString("Email"), rs.getString("DiscountID"), rs.getFloat("totalPrice"), rs.getDate("createDate"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public boolean deleteRental(String rentalID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "UPDATE tblRental SET status = 0 WHERE RentalID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, rentalID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            CloseConnection();
        }
    }

    public List<RentalDTO> searchInRental(String email, String rentDate, String returnDate) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "SELECT RentalID,Email,DiscountID,totalPrice,createDate,Status "
                    + "FROM tblRental "
                    + "WHERE Status = 1 AND Email LIKE ? AND createDate BETWEEN ? AND ? "
                    + "ORDER BY createDate DESC ";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + email + "%");
            stm.setString(2, rentDate);
            stm.setString(3, returnDate);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RentalDTO(rs.getString("RentalID"), rs.getString("Email"), rs.getString("DiscountID"), rs.getFloat("totalPrice"), rs.getDate("createDate"), rs.getBoolean("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }
}
