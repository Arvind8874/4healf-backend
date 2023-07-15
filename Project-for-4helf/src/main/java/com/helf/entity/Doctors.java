package com.helf.entity;
import com.helf.entity.enums.Status;
import lombok.*;
import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docters",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mobile_number"),
        })

public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private Long dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "dr_registration_no")
    private String drRegistrationNo;

    @Column(name = "date_of_registration")
    private Long dateOfRegistration;

    @Column(name = "state_medical_council")
    private String stateMedicalCouncil;

    @Column(name = "email")
    private String email;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "modified_time")
    private Long modifiedTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Getters and setters
}



