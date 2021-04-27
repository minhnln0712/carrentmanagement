/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import minhnln.DTO.RentalDetailDTO;
import minhnln.utils.db;

/**
 *
 * @author Welcome
 */
public class RentalDetailDAO implements Serializable {

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

    public boolean createRentalDetail(String rentalDetailID, String rentalID, String carID, Date rentDate, Date returnDate, String carDetailID, String licenceID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblRentalDetail(RentalDetailID,RentalID,CarID,rentDate,returnDate,CarDetailID,LicenceID) "
                    + "VALUES(?,?,?,?,?,?,?) ";
            stm = con.prepareStatement(sql);
            stm.setString(1, rentalDetailID);
            stm.setString(2, rentalID);
            stm.setString(3, carID);
            stm.setDate(4, rentDate);
            stm.setDate(5, returnDate);
            stm.setString(6, carDetailID);
            stm.setString(7, licenceID);
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

    public String getLastRentalDetailID(String rentalID) throws Exception {
        String id = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 RentalDetailID "
                    + "FROM tblRentalDetail "
                    + "WHERE RentalID = ? "
                    + "ORDER BY RentalDetailID DESC ";
            stm = con.prepareStatement(sql);
            stm.setString(1, rentalID);
            rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getString("RentalDetailID");
            }
        } finally {
            CloseConnection();
            return id;
        }
    }

    List<RentalDetailDTO> list;

    public List<RentalDetailDTO> getDetailbyRentalID(String rentalID) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "SELECT RD.RentalDetailID,RD.RentalID,CD.CarID,RD.rentDate,RD.returnDate,CD.CarDetailID,CD.LicenceID "
                    + "FROM tblRentalDetail RD JOIN tblCarDetail C ON RD.CarID = CD.CarID "
                    + "WHERE RentalID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, rentalID);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RentalDetailDTO(rs.getString("RentalDetailID"), rentalID, rs.getString("CarID"), rs.getString("CarDetailID"), rs.getString("LicenceID"), rs.getString("CarName"), rs.getString("CarImage"), rs.getDate("RentDate"), rs.getDate("ReturnDate")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getNumOfCarInOrder(String carID, String rentdate, String returnDate) throws Exception {
        int carinorder = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(RD.CarDetailID) AS 'INORDER'\n"
                    + "FROM tblRentalDetail RD JOIN tblRental R ON RD.RentalID = R.RentalID\n"
                    + "WHERE R.Status = 1 AND RD.CarID = ? \n"
                    + "AND (((? >= RD.rentdate AND ? <= RD.returnDate)\n"
                    + "OR ((? <= RD.rentdate AND ? > RD.rentdate) \n"
                    + "OR ( ? < RD.returnDate AND ? >= RD.returnDate))))\n";
            stm = con.prepareStatement(sql);
            stm.setString(1, carID);
            stm.setString(2, rentdate);
            stm.setString(3, returnDate);
            stm.setString(4, rentdate);
            stm.setString(5, returnDate);
            stm.setString(6, rentdate);
            stm.setString(7, returnDate);
            rs = stm.executeQuery();
            while (rs.next()) {
                carinorder = rs.getInt("INORDER");
                System.out.println(carID + "-" + carinorder);
            }
        } finally {
            CloseConnection();
            return carinorder;
        }
    }

}
