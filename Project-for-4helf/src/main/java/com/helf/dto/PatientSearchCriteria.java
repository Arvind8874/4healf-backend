package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PatientSearchCriteria {

    @JsonProperty("id")
    private Long id;

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
