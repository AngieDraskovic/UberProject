package com.example.easygo.model.users;

public class User {

    private int id;
    private String name;
    private String surname;
    private String profilePic;
    private String phone;
    private String email;
    private String address;
    private String password;
    private boolean blocked;

    public User(int id, String name, String surname, String profilePic, String phone, String email, String address, String password, boolean blocked){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePic = profilePic;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.password = password;
        this.blocked = blocked;
        this.blocked = blocked;
    }

    public()
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


}
