/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.entity;

/**
 *
 * @author USER
 */
public class Room {
    private int id;
    private int roomNumber;
    private int adultCapacity;
    private int childrenCapacity;
    private double pricePerNight;

    public Room() {
    }

    public Room(int id, int roomNumber, int adultCapacity, int childrenCapacity, double pricePerNight) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.adultCapacity = adultCapacity;
        this.childrenCapacity = childrenCapacity;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getAdultCapacity() {
        return adultCapacity;
    }

    public void setAdultCapacity(int adultCapacity) {
        this.adultCapacity = adultCapacity;
    }

    public int getChildrenCapacity() {
        return childrenCapacity;
    }

    public void setChildrenCapacity(int childrenCapacity) {
        this.childrenCapacity = childrenCapacity;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
}
