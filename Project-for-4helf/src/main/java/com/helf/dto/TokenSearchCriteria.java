package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helf.entity.enums.Status;
import com.helf.entity.enums.TokenType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TokenSearchCriteria {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("doctorName")
    private String doctorName;
    @JsonProperty("mobileNumber")
    private String mobileNumber;
    @JsonProperty("tokenNumber")
    private Integer tokenNumber;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("clinicName")
    private String clinicName;
    @JsonProperty("tokenType")
    private TokenType tokenType;
    @JsonProperty("toDate")
    private Long toDate;
    @JsonProperty("fromDate")
    private  Long fromDate=new Date().getTime();

    @JsonProperty("limit")
    private int limit=10;

    @JsonProperty("offset")
    private int offset=0;

}
