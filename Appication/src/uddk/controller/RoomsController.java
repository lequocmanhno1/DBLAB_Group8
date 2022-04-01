/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import uddk.entity.Room;
import uddk.model.RoomDAO;

/**
 *
 * @author USER
 */
public class RoomsController {

    public static Vector getAllRoomForTable() {
        try {
            return RoomDAO.getAllDataForTable();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static Room getAvailableRoom(String adultCapacity, String childrenCapacity, String checkInDate, String checkOutDate) {
        try {
            int adult = Integer.parseInt(adultCapacity);
            int children = Integer.parseInt(childrenCapacity);
            Date parsedInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate);
            Date parsedOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDate);
            return RoomDAO.getAvailableRoom(adult, children, parsedInDate, parsedOutDate);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static Room getRoomByRoomId(int roomId) {
        try {
            return RoomDAO.getRoomByRoomId(roomId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
