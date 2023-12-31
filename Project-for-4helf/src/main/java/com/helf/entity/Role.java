package com.helf.entity;
import com.helf.entity.enums.ERole;
import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}