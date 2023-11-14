package com.hx2.desk.model;

import com.hx2.desk.enums.HdStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "_hdxUsers")
@Data
@Entity
public class HdxUsers {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HdStatus estado;
    @Column(nullable = false)
    private OffsetDateTime publishedOn = OffsetDateTime.now();
    //@Column(nullable = false)
    //private Long idArea;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @OneToOne(mappedBy = "hdxUsers")
    private HdxAreas hdxAreas;

}
