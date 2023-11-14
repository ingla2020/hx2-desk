package com.hx2.desk.model;

import com.hx2.desk.enums.HdStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "_hdx_areas")
@Data
@Entity
public class HdxAreas {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HdStatus estado;
    @Column(nullable = false)
    private OffsetDateTime publishedOn = OffsetDateTime.now();
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hdxaccount_id", referencedColumnName = "id")
    private HdxAccount hdaccount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hdxusers_id", referencedColumnName = "id")
    private HdxUsers hdxUsers;


}