package com.exqress.adminservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table
public class QRinfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qrinfo_id")
    private Long id;

    @Column(nullable = false)
    private String qrId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String invoiceNo;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    public QRinfo(){

    }
    public QRinfo(String invoiceNo, String productName, String address){
        this.invoiceNo = invoiceNo;
        this.productName = productName;
        this.address = address;
    }

    public void assignedUser(UserEntity user){
        this.userEntity = user;
    }
}
