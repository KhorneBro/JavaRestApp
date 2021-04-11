package com.app.AppJava.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "userInformation")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private Date dateCreated;

    @UpdateTimestamp
    private Date dateUpdated;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String surName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String patronymic;

    @NotBlank
    @Size(min = 2, max = 20)
    private String gender;

    @NotBlank
    @Size(min = 2, max = 20)
    private String familyStatus;

    @Temporal(TemporalType.DATE)
    private Date birthDay;
}
