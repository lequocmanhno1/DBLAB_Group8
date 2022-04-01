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
public class Staff {

    private int staff_id;
    private String staffname;
    private String national_id;
    private String phone;
    private String address;
    private int permission;

    public Staff() {
    }

    public Staff(int staff_id, String staffname, String national_id, String phone, String address, int permission) {
        this.staff_id = staff_id;
        this.staffname = staffname;
        this.national_id = national_id;
        this.phone = phone;
        this.address = address;
        this.permission = permission;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    

}
