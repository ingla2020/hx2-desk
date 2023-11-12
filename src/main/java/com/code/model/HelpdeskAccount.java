package com.code.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;


@Entity
@Table(name = "helpdesk_account")
@Data
public class HelpdeskAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountId;
    private String accountName;
    private String accountEmail;
    private String password;
    private String stateInt;
    private OffsetDateTime accountDate;
    private String nameHelpdesk;

}
