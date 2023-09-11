package com.exqress.adminservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_entity_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "userEntity")
    private List<QRinfo> qRinfoList = new ArrayList<>();

    public UserEntity(String name, String id, String pn) {
        this.name = name;
        this.userId = id;
        this.phoneNumber = pn;
    }

    public void assginQRInfo(QRinfo qRinfo){
        this.qRinfoList.add(qRinfo);
    }
}
