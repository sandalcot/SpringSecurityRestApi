package com.daria.demospring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
public class Phone extends BaseEntity {
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "confirmation")
    private ConfirmationPhone confirmationPhone;

    @OneToOne(mappedBy = "phone", fetch = FetchType.EAGER)
    private User user;
}
