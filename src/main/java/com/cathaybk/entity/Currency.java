package com.cathaybk.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CURRENCY")
public class Currency {

    public Currency() {
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Id
    @Column(name="code", nullable = false)
    private String code;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="UPDATE_TIME")
    private Timestamp updateTime;

    @PreUpdate
    protected void preUpdate() {
        if (updateTime == null) {
            this.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        }
    }

}