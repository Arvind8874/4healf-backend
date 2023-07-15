package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helf.entity.Organization;
import com.helf.entity.enums.Status;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DoctorRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("degree")
    private String degree;

    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("drRegistrationNo")
    private String drRegistrationNo;

    @JsonProperty("dateOfRegistration")
    private Long dateOfRegistration;

    @JsonProperty("stateMedicalCouncil")
    private String stateMedicalCouncil;

    @JsonProperty("medicalCollege")
    private String medicalCollege;

    @JsonProperty("practicingField")
    private String practicingField;

    @JsonProperty("practicingSince")
    private String practicingSince;

    @JsonProperty("gstNo")
    private String gstNo;

    @JsonProperty("Dob")
    private Long Dob;

    @JsonProperty("organizationList")
    private List<Organization> organizationList;

}

