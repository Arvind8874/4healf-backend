package com.helf.entity;

import com.helf.entity.enums.Status;
/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mobile_number"),
        })
public class Patients {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "first_name", length = 256)
    private String firstName;

    @Column(name = "last_name", length = 256)
    private String lastName;

    @Column(name = "email", length = 256)
    private String email;
    @NotNull
    @Column(name = "password", length = 56)
    private String password;

    @Column(name = "mobile_number", length = 11)
    private String mobileNumber;

    @Column(name = "gender", length = 56)
    private String gender;

    @Column(name = "age", length = 56)
    private Integer age;

    @Column(name = "address", length = 56)
    private String address;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "modified_time")
    private Long modifiedTime;

    @Column(name = "status")
    private Status status = Status.ACTIVE;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles = new HashSet<>();
}

