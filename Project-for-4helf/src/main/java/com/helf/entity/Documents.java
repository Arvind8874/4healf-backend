package com.helf.entity;
import com.helf.entity.enums.DocumentType;
import com.helf.entity.enums.Status;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "documents")
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "docType")
    private DocumentType docType;
    @Column(name = "docName")
    private String docName;
    @Column(name = "documentId")
    private String documentId;
    @Column(name = "status")
    private Status status;
    @Column(name = "createdBy")
    private Long createdBy;
    @Column(name = "modifyBy")
    private Long modifyBy;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "createdTime")
    private Long createdTime;
    @Column(name = "modifyTime")
    private Long modifyTime;
    @Column(name = "data",length = 256)
    @Lob
    private byte[] data;
    @Column(name = "doctor_id")
    private Long doctorId;





}
