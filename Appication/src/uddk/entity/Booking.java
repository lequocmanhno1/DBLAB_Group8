/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.entity;

import java.sql.Date;

/**
 *
 * @author letha
 */
public class Booking {
    private int id;
    private String guestLastName;
    private String guestFirstName;
    private String guestPhone;
    private String guestCitizenId;
    private String guestPassport;
    private Date checkInDate;
    private Date checkOutDate;
    private int adultAmount;
    private int childrenAmount;
    private int roomId;
    private int staffId;
    private int statusId;
    private String gender;

    public Booking() {
    }

    public Booking(int id, String guestLastName, String guestFirstName, String guestPhone, String guestCitizenId, String guestPassport, Date checkInDate, Date checkOutDate, int adultAmount, int childrenAmount, int roomId, int staffId, int statusId, String gender) {
        this.id = id;
        this.guestLastName = guestLastName;
        this.guestFirstName = guestFirstName;
        this.guestPhone = guestPhone;
        this.guestCitizenId = guestCitizenId;
        this.guestPassport = guestPassport;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultAmount = adultAmount;
        this.childrenAmount = childrenAmount;
        this.roomId = roomId;
        this.staffId = staffId;
        this.statusId = statusId;
        this.gender=gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public String getGuestFirstName() {
        return guestFirstName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGuestFirstName(String guestFirstName) {
        this.guestFirstName = guestFirstName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestCitizenId() {
        return guestCitizenId;
    }

    public void setGuestCitizenId(String guestCitizenId) {
        this.guestCitizenId = guestCitizenId;
    }

    public String getGuestPassport() {
        return guestPassport;
    }

    public void setGuestPassport(String guestPassport) {
        this.guestPassport = guestPassport;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(int adultAmount) {
        this.adultAmount = adultAmount;
    }

    public int getChildrenAmount() {
        return childrenAmount;
    }

    public void setChildrenAmount(int childrenAmount) {
        this.childrenAmount = childrenAmount;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    
}
