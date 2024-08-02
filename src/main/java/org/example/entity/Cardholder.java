package org.example.entity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CARD_HOLDER")
@Data
@Builder
public class Cardholder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cardholder_id")
    private Long cardholderId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(optional = true)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

}

