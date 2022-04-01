/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import uddk.model.BookingDAO;
import uddk.entity.Booking;

/**
 *
 * @author USER
 */
public class BookingsController {

    public static Vector getAllBookingForTable() {
        try {
            return BookingDAO.getAllBookingForTable();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static Vector getCheckInToday() {
        try {
            return BookingDAO.getCheckInToday();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static Vector getCheckOutToday() {
        try {
            return BookingDAO.getCheckOutToday();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static boolean bookRoom(String lastName, String firstName, String phone, String citizenId, String passport, String checkInDate, String checkOutDate, String adultAmount, String childrenAmount, int roomId, int staffId, String gender) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date checkIn = sdf.parse(checkInDate);
            Date checkOut = sdf.parse(checkOutDate);
            int adult = Integer.parseInt(adultAmount);
            int children = Integer.parseInt(childrenAmount);
            return BookingDAO.bookRoom(lastName, firstName, phone, citizenId, passport, checkIn, checkOut, adult, children, roomId, staffId,gender) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    public static Booking getBookingById(int bookingId) {
        try {
            return BookingDAO.getBookingById(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static boolean checkIn(int bookingId) {
        try {
            return BookingDAO.checkIn(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    public static boolean checkOut(int bookingId) {
        try {
            return BookingDAO.checkOut(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    public static boolean cancelBooking(int bookingId) {
        try {
            return BookingDAO.cancelBooking(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    public static double getTotalRoomPrice(int bookingId) {
        try {
            return BookingDAO.getTotalRoomPrice(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }
    
    public static double getTotalServicePrice(int bookingId) {
        try {
            return BookingDAO.getTotalServicePrice(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static Vector getServiceUseForBookingId(int bookingId) {
        try {
            return BookingDAO.getServiceUseForBookingId(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
    
    public static boolean addNewServiceUse(int bookingId, int serviceId, int quantity) {
        try {
            return BookingDAO.addNewServiceUse(bookingId, serviceId, quantity);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
    
    public static boolean deleteServiceUse(int bookingId, int serviceId) {
        try {
            return BookingDAO.deleteServiceUse(bookingId, serviceId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
    
}
