package com.example.Test_Project.mvc.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "chairStatus")
public class ChairStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idchairStatus;  // Khóa chính

    private String statusName;

    @OneToMany(mappedBy = "chairStatus")
    private List<Chair> chairs;

    public Integer getIdchairStatus() {
        return idchairStatus;
    }

    public void setIdchairStatus(Integer idchairStatus) {
        this.idchairStatus = idchairStatus;
    }


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(List<Chair> chairs) {
        this.chairs = chairs;
    }
}
