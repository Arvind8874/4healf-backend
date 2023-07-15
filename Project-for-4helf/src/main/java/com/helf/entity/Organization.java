package com.helf.entity;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "organization")
public class Organization {
         @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "city")
        private String city;

        @Column(name = "state")
        private String state;

        @Column(name = "pin_code")
        private String pinCode;

        @Column(name = "duration")
        private Long duration;

        @Column(name = "year")
        private Long year;

        @Column(name = "awarded")
        private String awarded;

        @Column(name = "awarded_by")
        private String awardedBy;

        @Column(name = "office_contact_no1")
        private Long officeContactNo1;

        @Column(name = "office_contact_no2")
        private Long officeContactNo2;

        @Column(name = "fees")
        private String fees;

        @Column(name = "gst_no")
        private String gstNo;

        @Column(name = "practicing_field")
        private String practicingField;

        @Column(name = "practicing_since")
        private String practicingSince;

        @Column(name = "created_by")
        private Long createdBy;

        @Column(name = "modified_by")
        private Long modifiedBy;

        @Column(name = "created_time")
        private Long createdTime;

        @Column(name = "modified_time")
        private Long modifiedTime;

        @Column(name = "medical_college")
        private String medicalCollege;
        @Column(name = "hospitalName")
        private String hospitalName;

        @Column(name = "doctor_id")
        private Long doctorId;

        // Getters and setters,, id, docType, docName, documentId, status, audit details---createdby,modify by, createdtime,modTime
    }

