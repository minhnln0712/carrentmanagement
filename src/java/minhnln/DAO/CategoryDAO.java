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
import minhnln.DTO.CategoryDTO;
import minhnln.utils.db;

/**
 *
 * @author Welcome
 */
public class CategoryDAO implements Serializable {

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

    List<CategoryDTO> list;

    public List<CategoryDTO> getAllCategory() throws Exception {
        list = new ArrayList<>();
        try {
            con = db.openConnection();
            String sql = "SELECT CategoryID,CategoryName "
                    + "From tblCategory ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

}
