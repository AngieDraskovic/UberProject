package com.example.easygo.dto;

public class PostPassengerDTO {

    private String name;
    private String surname;
    private String profilePicture;  // Slika nije obavezna
    private String telephoneNumber;
    private String email;
    private String address;
    private String password;
    private String confirmPassword;

    public PostPassengerDTO(String name, String surname, String profilePicture,
                            String telephoneNumber, String email,
                            String address, String password, String confirmPassword) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
