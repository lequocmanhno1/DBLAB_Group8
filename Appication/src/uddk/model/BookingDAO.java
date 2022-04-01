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
import uddk.entity.Booking;

/**
 *
 * @author letha
 */
public class BookingDAO {

    public static Vector getAllBookingForTable() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data = new Vector();
        try {
            String sql = "SELECT \"Booking\".id AS \"ID\",\n"
                    + "		\"Booking\".guest_firstname AS \"Guest First Name\",\n"
                    + "		\"Booking\".guest_lastname AS \"Guest Last Name\",\n"
                    + "		\"Room\".room_number AS \"Room\",\n"
                    + "		TO_CHAR(\"Booking\".check_in_date, 'dd/MM/yyyy') AS \"Check In\",\n"
                    + "		TO_CHAR(\"Booking\".check_out_date, 'dd/MM/yyyy') AS \"Check Out\",\n"
                    + "		\"Status\".label AS \"Status\"\n"
                    + "FROM public.\"Booking\" \n"
                    + "	INNER JOIN public.\"Room\" ON \"Booking\".room_id = \"Room\".id\n"
                    + "	INNER JOIN public.\"Staff\" ON \"Booking\".staff_id = \"Staff\".staff_id\n"
                    + "	INNER JOIN public.\"Status\" ON \"Booking\".status_id = \"Status\".id";
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

    public static int bookRoom(String lastName, String firstName, String phone, String citizenId, String passport, Date checkIn, Date checkOut, int adult, int children, int room, int staff,String gender) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        int n = 0;
        java.sql.Date checkInDate = new java.sql.Date(checkIn.getTime());
        java.sql.Date checkOutDate = new java.sql.Date(checkOut.getTime());
        try {
            String sql = "INSERT INTO public.\"Booking\"(\n"
                    + "	guest_lastname, guest_firstname, guest_phone, guest_citizen_id, guest_passport, check_in_date, check_out_date, adult_amount, children_amount, room_id, staff_id, status_id, gender)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, phone);
            ps.setString(4, citizenId);
            ps.setString(5, passport);
            ps.setDate(6, checkInDate);
            ps.setDate(7, checkOutDate);
            ps.setInt(8, adult);
            ps.setInt(9, children);
            ps.setInt(10, room);
            ps.setInt(11, staff);
            ps.setInt(12, 1);
            ps.setString(13,gender);
            n = ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
        return n;
    }

    public static Booking getBookingById(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM public.\"Booking\" WHERE \"Booking\".id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Booking(rs.getInt("id"),
                        rs.getString("guest_lastname"),
                        rs.getString("guest_firstname"),
                        rs.getString("guest_phone"),
                        rs.getString("guest_citizen_id"),
                        rs.getString("guest_passport"),
                        rs.getDate("check_in_date"),
                        rs.getDate("check_out_date"),
                        rs.getInt("adult_amount"),
                        rs.getInt("children_amount"),
                        rs.getInt("room_id"),
                        rs.getInt("staff_id"),
                        rs.getInt("status_id"),
                        rs.getString("gender")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
        return null;
    }

    public static boolean checkIn(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        try {
            String sql = "UPDATE public.\"Booking\" SET status_id = 2 WHERE id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            n = ps.executeUpdate();

            return n > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
    }

    public static boolean checkOut(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        try {
            String sql = "UPDATE public.\"Booking\" SET status_id = 3 WHERE id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            n = ps.executeUpdate();

            return n > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
    }

    public static boolean cancelBooking(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        try {
            String sql = "UPDATE public.\"Booking\" SET status_id = 4 WHERE id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            n = ps.executeUpdate();

            return n > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
    }

    public static double getTotalRoomPrice(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT (\"Booking\".check_out_date::date - \"Booking\".check_in_date::date) * \"Room\".price_per_night "
                    + " FROM \"Booking\" INNER JOIN \"Room\" ON \"Booking\".room_id = \"Room\".id WHERE \"Booking\".id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
        return 0;
    }

    public static Vector getCheckInToday() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data = new Vector();
        try {
            String sql = "SELECT \"Booking\".id AS \"ID\", \n"
                    + "		\"Booking\".guest_firstname AS \"Guest First Name\", \n"
                    + "		\"Booking\".guest_lastname AS \"Guest Last Name\", \n"
                    + "		\"Room\".room_number AS \"Room\", \n"
                    + "		TO_CHAR(\"Booking\".check_out_date, 'dd/MM/yyyy') AS \"Check-Out\" \n"
                    + "FROM public.\"Booking\" \n"
                    + "	INNER JOIN public.\"Room\" ON \"Booking\".room_id = \"Room\".id \n"
                    + "WHERE (\"Booking\".check_in_date::date - NOW()::date) = 0 AND \"Booking\".status_id = 1";
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

    public static Vector getCheckOutToday() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data = new Vector();
        try {
            String sql = "SELECT \"Booking\".id AS \"ID\", \n"
                    + "		\"Booking\".guest_firstname AS \"Guest First Name\", \n"
                    + "		\"Booking\".guest_lastname AS \"Guest Last Name\", \n"
                    + "		\"Room\".room_number AS \"Room\", \n"
                    + "		TO_CHAR(\"Booking\".check_in_date, 'dd/MM/yyyy') AS \"Check-In\" \n"
                    + "FROM public.\"Booking\" \n"
                    + "	INNER JOIN public.\"Room\" ON \"Booking\".room_id = \"Room\".id \n"
                    + "WHERE (\"Booking\".check_out_date::date - NOW()::date) = 0 AND \"Booking\".status_id = 2";
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

    public static Vector getServiceUseForBookingId(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector data = new Vector();
        try {
            String sql = "SELECT \"Service_Use\".service_id AS \"Id\",\n"
                    + "		\"Service\".name AS \"Name\",\n"
                    + "		\"Service\".description AS \"Description\",\n"
                    + "		\"Service_Use\".quantity AS \"Quantity\",\n"
                    + "		\"Service\".price AS \"Unit Price\"\n"
                    + "FROM \"Service_Use\" INNER JOIN \"Service\" ON \"Service_Use\".service_id = \"Service\".id WHERE \"Service_Use\".booking_id = ?";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
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

    public static boolean addNewServiceUse(int bookingId, int serviceId, int quantity) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        try {
            String sql = "INSERT INTO public.\"Service_Use\"(booking_id, service_id, quantity)\n"
                    + " VALUES (?, ?, ?)\n"
                    + " ON CONFLICT (booking_id, service_id) DO UPDATE\n"
                    + "	SET quantity = \"Service_Use\".quantity + excluded.quantity;";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            ps.setInt(2, serviceId);
            ps.setInt(3, quantity);
            n = ps.executeUpdate();

            return n > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
    }

    public static boolean deleteServiceUse(int bookingId, int serviceId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int n = 0;
        try {
            String sql = "DELETE FROM public.\"Service_Use\"\n"
                    + "	WHERE booking_id = ? AND service_id = ?;";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            ps.setInt(2, serviceId);
            n = ps.executeUpdate();

            return n > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
    }

    public static double getTotalServicePrice(int bookingId) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT SUM(\"Service_Use\".quantity * \"Service\".price) \n"
                    + "FROM \"Booking\" \n"
                    + "INNER JOIN \"Service_Use\" ON \"Booking\".id = \"Service_Use\".booking_id\n"
                    + "INNER JOIN \"Service\" ON \"Service_Use\".service_id = \"Service\".id\n"
                    + "WHERE \"Booking\".id = ?\n"
                    + "GROUP BY \"Booking\".id";
            connection = dbContext.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(null, ps, connection);
        }
        return 0;
    }
}
