package com.redjen.softcamp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer idx;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private SocialProvider sns;

    private Date lastLogin;
}
