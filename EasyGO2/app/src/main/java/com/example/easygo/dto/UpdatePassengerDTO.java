package com.example.easygo.dto;

public class UpdatePassengerDTO {

    private String name;
    private String surname;
    private String profilePicture;  // Slika nije obavezna
    private String telephoneNumber;
    private String email;
    private String address;

    public UpdatePassengerDTO(String name, String surname, String profilePicture,
                            String telephoneNumber, String email,
                            String address) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;

    }

}
