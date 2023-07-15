
package com.helf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.helf.entity.enums.Status;
import com.helf.entity.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.ObjectInputFilter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TokenRequest {
    @JsonProperty("id")
    private Long id;
   // @JsonProperty("id")
    private boolean isExpire=false;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("modifiedBy")
    private String modifiedBy;
    @JsonProperty("createdTime")
    private Long createdTime;
    @JsonProperty("modifiedTime")
    private String modifiedTime;
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

}

