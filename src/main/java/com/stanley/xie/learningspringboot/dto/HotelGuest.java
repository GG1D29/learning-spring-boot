package com.stanley.xie.learningspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelGuest {
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String phoneNumber;
}
