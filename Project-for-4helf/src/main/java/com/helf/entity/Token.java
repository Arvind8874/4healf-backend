package com.helf.entity;
import com.helf.entity.enums.Status;
import com.helf.entity.enums.TokenType;
import lombok.*;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "is_expire")
    private boolean isExpire;
    @Column(name = "created_by")
     private Long createdBy;
    @Column(name = "modified_by")
     private Long modifiedBy;
    @Column(name = "modified_time")
     private Long modifiedTime;
    @Column(name = "created_time")
    private Long createdTime;
    @Column(name = "doctor_id")
     private String doctorName;
    @Column(name = "token_number")
    private Integer tokenNumber;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "status")
    private Status status;
    @Column(name = "clinic_name")
    private String clinicName;
    @Column(name = "token_ype")
    private TokenType tokenType;

}
