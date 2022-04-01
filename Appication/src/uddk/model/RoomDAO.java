/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.Vector;
import uddk.entity.Room;

/**
 *
 * @author USER
 */
public class RoomDAO {

    public static Vector getAllDataForTable() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data = new Vector();
        try {
            String sql = "SELECT room_number AS \"Room\","
                    + " adult_capacity AS \"Adult Capacity\","
                    + " children_capacity AS \"Children Capacity\","
                    + " price_per_night AS \"Price\" FROM \"Room\"";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            // Get column
            ResultSetMetaData rsmd = rs.getMetaData();
            Vector vcol = new Vector();
            Vector vdata = new Vector();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                vcol.add(rsmd.getColumnLabel(i));
            }

            // Get data
            while (rs.next()) {
                Vector vtemp = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    vtemp.add(rs.getString(i));
                }
                vdata.add(vtemp);
            }

            //Return data
            data.add(vdata);
            data.add(vcol);
            return data;

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
    }

    public static Vector filterRoom(String roomType, String capacity) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data;
        try {
            String sql = "SELECT Maphong AS 'Mã phòng',"
                    + " Loaiphong AS 'Loại phòng',"
                    + " Succhua AS 'Sức chứa'"
                    + " FROM thongtinphong"
                    + " WHERE Loaiphong LIKE ?"
                    + " AND Succhua LIKE ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + roomType + "%");
            ps.setString(2, "%" + capacity + "%");
            rs = ps.executeQuery();
            data = new Vector();

            // Get column
            ResultSetMetaData rsmd = rs.getMetaData();
            Vector vcol = new Vector();
            Vector vdata = new Vector();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                vcol.add(rsmd.getColumnName(i));
            }

            // Get data
            while (rs.next()) {
                Vector vtemp = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    vtemp.add(rs.getString(i));
                }
                vdata.add(vtemp);
            }

            //Return data
            data.add(vdata);
            data.add(vcol);
            return data;

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
    }
    
    public static Room getAvailableRoom(int adultCapacity, int childrenCapacity, Date checkInDate, Date checkOutDate) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Date checkIn = new java.sql.Date(checkInDate.getTime());
        java.sql.Date checkOut = new java.sql.Date(checkOutDate.getTime());
        try {
            String sql = "SELECT * FROM \"Room\" \n"
                    + "WHERE ? > 0 AND ? >= 0 \n"
                    + " AND \"Room\".adult_capacity >= ? \n"
                    + "	AND \"Room\".adult_capacity + \"Room\".children_capacity >= ? + ?\n"
                    + "	AND NOT EXISTS \n"
                    + "		(SELECT 1 FROM \"Booking\" WHERE \"Booking\".room_id = \"Room\".id\n"
                    + "			AND (? BETWEEN \"Booking\".check_in_date AND \"Booking\".check_out_date - 1\n"
                    + "			OR ? BETWEEN \"Booking\".check_in_date + 1 AND \"Booking\".check_out_date)\n"
                    + "			AND \"Booking\".status_id <> 4)\n"
                    + "ORDER BY \"Room\".price_per_night ASC\n"
                    + "LIMIT 1";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, adultCapacity);
            ps.setInt(2, childrenCapacity);
            ps.setInt(3, adultCapacity);
            ps.setInt(4, adultCapacity);
            ps.setInt(5, childrenCapacity);
            ps.setDate(6, checkIn);
            ps.setDate(7, checkOut);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Room(rs.getInt("id"), rs.getInt("room_number"), rs.getInt("adult_capacity"), rs.getInt("children_capacity"), rs.getDouble("price_per_night"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
        return null;
    }
  
    public static Room getRoomByRoomId(int roomId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM public.\"Room\" WHERE \"Room\".id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Room(rs.getInt("id"), rs.getInt("room_number"), rs.getInt("adult_capacity"), rs.getInt("children_capacity"), rs.getDouble("price_per_night"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
        return null;
    }
}
