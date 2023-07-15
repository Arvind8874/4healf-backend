package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helf.entity.Doctors;
import com.helf.entity.Documents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrganizationRequest {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("pinCode")
    private String pinCode;

    @JsonProperty("duration")
    private Long duration;

    @JsonProperty("year")
    private Long year;

    @JsonProperty("awarded")
    private String awarded;

    @JsonProperty("awardedBy")
    private String awardedBy;

    @JsonProperty("officeContactNo1")
    private Long officeContactNo1;

    @JsonProperty("officeContactNo2")
    private Long officeContactNo2;

    @JsonProperty("fees")
    private String fees;

    @JsonProperty("gstNo")
    private String gstNo;

    @JsonProperty("practicingField")
    private String practicingField;

    @JsonProperty("practicingSince")
    private String practicingSince;
    @JsonProperty("medicalCollege")
    private String medicalCollege;
    @JsonProperty("doctorsId")
    private Long doctorsId;
    @JsonProperty("hospitalName")
    private String hospitalName;
    @JsonProperty("documents")
    List<Documents> documents;
}
