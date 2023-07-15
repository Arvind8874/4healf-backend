package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class DoctorSearchCriteria {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("createdTime")
    private Long createdTime;

    @JsonProperty("modifiedTime")
    private Long modifiedTime;

    @JsonProperty("limit")
    private int limit=10;

    @JsonProperty("offset")
    private int offset=0;
}
