package com.hx2.desk.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Table(name = "helpdesk_account")
@Data
public class HelpdeskAccount {

    @Id
    @UuidGenerator
    private UUID id;
    private String accountId;
    private String accountName;
    private String accountEmail;
    private String password;
    private String stateInt;
    private OffsetDateTime accountDate;
    private String nameHelpdesk;

}
