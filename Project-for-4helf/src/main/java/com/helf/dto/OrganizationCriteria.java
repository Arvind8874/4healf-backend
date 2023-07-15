package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrganizationCriteria {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("hospitalName")
    private String hospitalName;
    @JsonProperty("medicalCollege")
    private String medicalCollege;
    @JsonProperty("createdTime")
    private Long createdTime;
    @JsonProperty("modifiedTime")
    private Long modifiedTime;
    @JsonProperty("limit")
    private int limit=10;

    @JsonProperty("offset")
    private int offset=0;

}
