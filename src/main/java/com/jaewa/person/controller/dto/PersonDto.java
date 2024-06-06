package com.jaewa.person.controller.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
