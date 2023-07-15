package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.helf.entity.enums.Status;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PatientRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private Status status;
}
