package com.example.defencedrive;

public class OrderHolder {
    boolean Order_Status;
    String Name;
    String Email;
    String Phone;
    String Gender;
    String Slots;
    String Course_Duration;
    String Car;
    String Order_Id;
    String Center_uid,Center_Name,Address;
    String User_id;
    String Instructor;
    String Date;
    String Center_Image;
    public OrderHolder(boolean order_Status,String center_uid,String center_Name,String address ,String order_Id,String user_id,String name, String email, String phone, String gender, String slots, String course_Duration, String car,String date ,String instructor, String center_Image) {
        Order_Status = order_Status;
        Center_uid = center_uid;
        Center_Name = center_Name;
        Address = address;
        Order_Id = order_Id;
        User_id = user_id;
        Name = name;
        Email = email;
        Phone = phone;
        Gender = gender;
        Slots = slots;
        Course_Duration = course_Duration;
        Car = car;
        Date = date;
        Instructor = instructor;
        Center_Image = center_Image;
    }
    public OrderHolder() {

    }

    public boolean isOrder_Status() {
        return Order_Status;
    }

    public void setOrder_Status(boolean order_Status) {
        Order_Status = order_Status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSlots() {
        return Slots;
    }

    public void setSlots(String slots) {
        Slots = slots;
    }

    public String getCourse_Duration() {
        return Course_Duration;
    }

    public void setCourse_Duration(String course_Duration) {
        Course_Duration = course_Duration;
    }

    public String getCar() {
        return Car;
    }

    public void setCar(String car) {
        Car = car;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(String order_Id) {
        Order_Id = order_Id;
    }

    public String getCenter_uid() {
        return Center_uid;
    }

    public void setCenter_uid(String center_uid) {
        Center_uid = center_uid;
    }

    public String getCenter_Name() {
        return Center_Name;
    }

    public void setCenter_Name(String center_Name) {
        Center_Name = center_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCenter_Image() {
        return Center_Image;
    }

    public void setCenter_Image(String center_Image) {
        Center_Image = center_Image;
    }
}
