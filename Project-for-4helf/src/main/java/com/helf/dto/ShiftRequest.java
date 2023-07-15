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
public class ShiftRequest {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("maximumPatient")
    private String maximumPatient;
    @JsonProperty("averageConsultationTime")
    private String averageConsultationTime;
    @JsonProperty("breakDuration")
    private String breakDuration;
    @JsonProperty("weekOff")
    private String weekOff;
    @JsonProperty("startDate")
    private Long startDate;
    @JsonProperty("endDate")
    private Long endDate;

}
