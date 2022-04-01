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
import java.util.Vector;
import uddk.entity.Staff;

/**
 *
 * @author USER
 */
public class StaffDAO {

    public static boolean login(String username, String password) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(staff_id) FROM public.\"Staff\" WHERE staff_id = ? AND password = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(username));
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                int count = rs.getInt(1);
                return count == 1 ? true : false;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
        return false;
    }

    public static boolean isAdmin(int staffId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(staff_id) FROM public.\"Staff\" WHERE staff_id = ? AND role < 3";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, staffId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int count = rs.getInt(1);
                return count == 1 ? true : false;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
        return false;
    }

//    public static Vector getAllUserDataForTable() throws Exception {
//        DBContext dbContext = new DBContext();
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Vector data = new Vector();
//        try {
//            String sql = "SELECT Magiaovien AS 'Mã giáo viên', Ten AS 'Tên', Chucvu AS 'Chức vụ', Tuoi AS 'Tuổi', username AS 'Tên đăng nhập', password AS 'Mật khẩu', role AS 'Role' FROM thongtinnguoidung";
//            connection = dbContext.getConnection();
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            // Get column
//            ResultSetMetaData rsmd = rs.getMetaData();
//            Vector vcol = new Vector();
//            Vector vdata = new Vector();
//            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                vcol.add(rsmd.getColumnLabel(i));
//            }
//
//            // Get data
//            while (rs.next()) {
//                Vector vtemp = new Vector();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    vtemp.add(rs.getString(i));
//                }
//                vdata.add(vtemp);
//            }
//
//            //Return data
//            data.add(vdata);
//            data.add(vcol);
//            return data;
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            dbContext.closeConnection(rs, ps, connection);
//        }
//    }
    public static Staff getStaffByStaffId(int staffId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM public.\"Staff\" WHERE staff_id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, staffId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Staff(rs.getInt("staff_id"), rs.getString("staffname"), rs.getString("national_id"), rs.getString("phone"), rs.getString("address"), rs.getInt("permission"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, ps, connection);
        }
        return null;
    }

//    public static int updateUserById(User user) throws Exception {
//        DBContext dbContext = new DBContext();
//        Connection connection = null;
//        PreparedStatement ps = null;
//        int n = 0;
//        try {
//            String sql = "INSERT INTO thongtinnguoidung(Magiaovien, Ten, Chucvu, Tuoi, username, password, role) VALUES"
//                    + " (?, ?, ?, ?, ?, ?, ?)"
//                    + " ON DUPLICATE KEY UPDATE"
//                    + " Ten=?, Chucvu=?, Tuoi=?, username=?, password=?, role=?";
//            connection = dbContext.getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, user.getId());
//            ps.setString(2, user.getName());
//            ps.setString(3, user.getPosition());
//            ps.setInt(4, user.getAge());
//            ps.setString(5, user.getUsername());
//            ps.setString(6, user.getPassword());
//            ps.setInt(7, user.getRole());
//            ps.setString(8, user.getName());
//            ps.setString(9, user.getPosition());
//            ps.setInt(10, user.getAge());
//            ps.setString(11, user.getUsername());
//            ps.setString(12, user.getPassword());
//            ps.setInt(13, user.getRole());
//            n = ps.executeUpdate();
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            dbContext.closeConnection(null, ps, connection);
//        }
//        return n;
//    }
//    public static int deleteUserById(String userId) throws Exception {
//        DBContext dbContext = new DBContext();
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int n = 0;
//        try {
//            String sql = "DELETE FROM public.\"Staff\" WHERE staff_id = ?";
//            connection = dbContext.getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, userId);
//            n = ps.executeUpdate();
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            dbContext.closeConnection(rs, ps, connection);
//        }
//        return n;
//    }
//    public static String getUserIdByUsername(String username) throws Exception {
//        DBContext dbContext = new DBContext();
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            String sql = "SELECT Magiaovien FROM thongtinnguoidung WHERE username = ?";
//            connection = dbContext.getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                return rs.getString("Magiaovien");
//            }
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            dbContext.closeConnection(rs, ps, connection);
//        }
//        return null;
//    }
}
