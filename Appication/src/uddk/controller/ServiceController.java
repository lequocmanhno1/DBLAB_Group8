/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.controller;

import java.util.Vector;
import uddk.model.ServiceDAO;

/**
 *
 * @author letha
 */
public class ServiceController {
    public static Vector getAllService() {
        try {
            return ServiceDAO.getAllService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
