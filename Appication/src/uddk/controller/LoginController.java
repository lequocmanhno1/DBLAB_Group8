/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.controller;

import uddk.entity.Staff;
import uddk.model.StaffDAO;

/**
 *
 * @author USER
 */
public class LoginController {

    public static boolean login(String username, String password) {
        try {
            return StaffDAO.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    public static boolean isAdmin(int staffId) {
        try {
            return StaffDAO.isAdmin(staffId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
    
    public static Staff getStaffByStaffId(int staffId) {
        try {
            return StaffDAO.getStaffByStaffId(staffId);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
