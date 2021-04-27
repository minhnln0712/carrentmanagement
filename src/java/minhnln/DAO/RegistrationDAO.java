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
import minhnln.DTO.RegistrationDTO;
import minhnln.utils.db;

/**
 *
 * @author Welcome
 */
public class RegistrationDAO implements Serializable {

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

    public RegistrationDTO login(String email, String password) throws Exception {
        RegistrationDTO dto = null;
        try {
            con = db.openConnection();
            String sql = "SELECT Email,Phone,Name,Address,CreateDate,Role,isActive "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? AND Password = ? AND isActive = 1 ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new RegistrationDTO(rs.getString("Email"), rs.getString("Phone"), rs.getString("Name"), rs.getString("Address"), rs.getString("Role"), rs.getDate("CreateDate"), rs.getBoolean("isActive"));
            }
        } finally {
            CloseConnection();
            return dto;
        }
    }

    public String getRolebyEmail(String email) throws Exception {
        String role = "";
        try {
            con = db.openConnection();
            String sql = "SELECT Role "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            CloseConnection();
            return role;
        }
    }

    public String getNamebyEmail(String email) throws Exception {
        String name = "";
        try {
            con = db.openConnection();
            String sql = "SELECT Name "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                name = rs.getNString("Name");
            }
        } finally {
            CloseConnection();
            return name;
        }
    }

    public boolean createAccount(String email, String phone, String name, String address, Date createDate, String password) throws Exception {
        boolean status = false;
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblRegistration(Email,Phone,Name,Address,CreateDate,Role,isActive,Password) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, phone);
            stm.setString(3, name);
            stm.setString(4, address);
            stm.setDate(5, createDate);
            stm.setString(6, "user");
            stm.setBoolean(7, false);
            stm.setString(8, password);
            int row = stm.executeUpdate();
            if (row > 0) {
                status = true;
            }
        } finally {
            CloseConnection();
            return status;
        }
    }

    public String getPasswordbyEmail(String email) throws Exception {
        String password = "";
        try {
            con = db.openConnection();
            String sql = "SELECT Password "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                password = rs.getString("Password");
            }
        } finally {
            CloseConnection();
            return password;
        }
    }

    public boolean loginToActivation(String email, String password) throws Exception {
        boolean status = false;
        try {
            con = db.openConnection();
            String sql = "SELECT Email,Password "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? AND Password = ? AND isActive = 0 ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                status = true;
            }
        } finally {
            CloseConnection();
            return status;
        }
    }

    public String getEmailinDBbyEmail(String email) throws Exception {
        String emailInDB = "";
        try {
            con = db.openConnection();
            String sql = "SELECT Email "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                emailInDB = rs.getString("Email");
            }
        } finally {
            CloseConnection();
            return emailInDB;
        }
    }

        public boolean createRandomCode(String email) throws Exception {
        boolean success = false;
        try {
            con = db.openConnection();
            String sql = "UPDATE tblRegistration "
                    + "SET ActiveCode = RIGHT( NEWID(), 10 )"
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            int row = stm.executeUpdate();
            if (row > 0) {
                success = true;
            }
        } finally {
            CloseConnection();
            return success;
        }
    }

    public String getActiveCode(String email) throws Exception {
        String code = "";
        try {
            con = db.openConnection();
            String sql = "SELECT ActiveCode "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                code = rs.getString("ActiveCode");
            }
        } finally {
            CloseConnection();
            return code;
        }
    }

    public boolean checkActiveCode(String email, String activeCode) throws Exception {
        boolean success = false;
        try {
            con = db.openConnection();
            String sql = "UPDATE tblRegistration "
                    + "SET isActive = 1 "
                    + "WHERE Email = ? AND ActiveCode = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, activeCode);
            int row = stm.executeUpdate();
            if (row > 0) {
                success = true;
            }
        } finally {
            CloseConnection();
            return success;
        }
    }
}
