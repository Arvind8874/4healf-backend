package com.helf.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "shift")

public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "maximum_patient")
    private String maximumPatient;
    @Column(name = "average_consultation_time")
    private String averageConsultationTime;
    @Column(name = "break_duration")
    private String breakDuration;
    @Column(name = "week_off")
    private String weekOff;
    @Column(name = "from_date")
    private Long fromDate;
    @Column(name = "to_date")
    private Long toDate;
    @Column(name = "start_date")
    private Long startDate;
    @Column(name = "end_date")
    private Long endDate;
    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "modified_time")
    private Long modifiedTime;

}
