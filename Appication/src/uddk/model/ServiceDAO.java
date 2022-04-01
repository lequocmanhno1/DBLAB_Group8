/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import uddk.entity.Service;

/**
 *
 * @author letha
 */
public class ServiceDAO {
    public static Vector getAllService() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data;
        try {
            String sql = "SELECT * FROM public.\"Service\"";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            data = new Vector();
            // Get data
            while (rs.next()) {
                data.add(new Service(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price")));
            }

            //Return data
            return data;

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
    }
}
