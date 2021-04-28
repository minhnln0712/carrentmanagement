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
import java.util.ArrayList;
import java.util.List;
import minhnln.DTO.CarDTO;
import minhnln.utils.db;

/**
 *
 * @author Welcome
 */
public class CarDAO implements Serializable {

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
    List<CarDTO> list;

    public int getSumOfCar() throws Exception {
        int sum = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(CarID) AS TOTAL "
                    + "FROM tblCar ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return sum;
        }
    }

    public List<CarDTO> getCarInforWithPaging(int pageNo, int itemPerPage) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY Year DESC) as STT,CarID,CarName,CarImage,Color,Year,CategoryID,Price,Quantity\n"
                    + "				FROM tblCar)  \n"
                    + "SELECT L.CarID,L.CarName,L.CarImage,L.Color,L.Year,L.CategoryID,L.Price,CATE.CategoryName,L.Quantity \n"
                    + "FROM LIST L JOIN tblCategory CATE ON L.CategoryID = CATE.CategoryID \n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, pageNo);
            stm.setInt(2, itemPerPage);
            stm.setInt(3, itemPerPage);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CarDTO(rs.getString("CarID"), rs.getString("CarName"), rs.getString("CarImage"), rs.getString("Color"), rs.getString("CategoryName"), rs.getInt("Year"), rs.getInt("CategoryID"), rs.getInt("Quantity"), rs.getInt("Quantity"), rs.getInt("Price")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<CarDTO> searchCarWithPaging(int pageNo, int itemPerPage, String CarName, String CategoryID, String Quantity) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY Year DESC) as STT,CarID,CarName,CarImage,Color,Year,CategoryID,Price,Quantity  \n"
                    + "			FROM tblCar                                       \n"
                    + "			WHERE CarName LIKE ? AND CategoryID LIKE ? AND Quantity LIKE ? )  \n"
                    + "SELECT L.CarID,L.CarName,L.CarImage,L.Color,L.Year,L.CategoryID,L.Price,C.CategoryName,L.Quantity\n"
                    + "FROM LIST L JOIN tblCategory C ON L.CategoryID=C.CategoryID \n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + CarName + "%");
            stm.setString(2, CategoryID);
            stm.setString(3, Quantity);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            stm.setInt(6, itemPerPage);
            stm.setInt(7, pageNo);
            stm.setInt(8, itemPerPage);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CarDTO(rs.getString("CarID"), rs.getString("CarName"), rs.getString("CarImage"), rs.getString("Color"), rs.getString("CategoryName"), rs.getInt("Year"), rs.getInt("CategoryID"), rs.getInt("Quantity"), rs.getInt("Quantity"), rs.getInt("Price")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getSumOfCarAfterSearch(String CarName, String CategoryID, String Quantity) throws Exception {
        int sum = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(CarID) AS TOTAL "
                    + "FROM tblCar "
                    + "WHERE CarName LIKE ? AND CategoryID LIKE ? AND Quantity LIKE ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + CarName + "%");
            stm.setString(2, CategoryID);
            stm.setString(3, Quantity);
            rs = stm.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return sum;
        }
    }

    public List<CarDTO> getAllCarDetailWithID(String carID) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS(	SELECT RD.CarDetailID\n"
                    + "				FROM tblRentalDetail RD JOIN tblRental R ON RD.RentalID = R.RentalID\n"
                    + "				WHERE R.status = 1 )\n"
                    + "SELECT * "
                    + "FROM tblCarDetail CD \n"
                    + "JOIN tblCar C ON CD.CarID = C.CarID \n"
                    + "FULL OUTER JOIN LIST L ON CD.CarDetailID = L.CarDetailID\n"
                    + "WHERE C.CarID = ? AND L.CarDetailID IS NULL";
            stm = con.prepareStatement(sql);
            stm.setString(1, carID);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CarDTO(carID, rs.getString("CarName"), rs.getString("CarImage"), rs.getString("LicenceID"), rs.getString("CarDetailID"), rs.getFloat("Price")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<CarDTO> getCarDetailWithIDAndDate(String carID, String rentDate, String returnDate) throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "SELECT C.CarID,C.CarName,C.CarImage,CD.LicenceID,CD.CarDetailID,C.Price \n"
                    + "FROM tblCarDetail CD \n"
                    + "JOIN tblCar C ON CD.CarID = C.CarID \n"
                    + "FULL OUTER JOIN tblRentalDetail RD ON CD.CarDetailID = RD.CarDetailID\n"
                    + "WHERE C.CarID = ? AND \n"
                    + "CD.CarDetailID NOT IN (	SELECT RD.CarDetailID\n"
                    + "				FROM tblRentalDetail RD JOIN tblRental R ON RD.RentalID = R.RentalID\n"
                    + "				WHERE R.Status = 1 AND RD.CarID = ?\n"
                    + "				AND (((? >= RD.rentdate AND ? <= RD.returnDate)\n"
                    + "				OR ((? <= RD.rentdate AND ? > RD.rentdate) \n"
                    + "				OR ( ? < RD.returnDate AND ? >= RD.returnDate)))))";
            stm = con.prepareStatement(sql);
            stm.setString(1, carID);
            stm.setString(2, carID);
            stm.setString(3, rentDate);
            stm.setString(4, returnDate);
            stm.setString(5, rentDate);
            stm.setString(6, returnDate);
            stm.setString(7, rentDate);
            stm.setString(8, returnDate);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CarDTO(carID, rs.getString("CarName"), rs.getString("CarImage"), rs.getString("LicenceID"), rs.getString("CarDetailID"), rs.getFloat("Price")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<CarDTO> getAllCarDetail() throws Exception {
        list = new ArrayList<>();;
        try {
            con = db.openConnection();
            String sql = "SELECT C.CarID,C.CarName,C.CarImage,CD.LicenceID,CD.CarDetailID,C.Price\n"
                    + "FROM tblCarDetail CD JOIN tblCar C ON CD.CarID=C.CarID";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CarDTO(rs.getString("CarID"), rs.getString("CarName"), rs.getString("CarImage"), rs.getString("LicenceID"), rs.getString("CarDetailID"), rs.getFloat("Price")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }
}
