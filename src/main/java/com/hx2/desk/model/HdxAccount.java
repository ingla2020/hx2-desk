package com.hx2.desk.model;

import com.hx2.desk.enums.HdEstado;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "_hdxAccount")
@Data
@Entity
public class HdxAccount {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, unique=true)
    private String email;
    @Column(nullable = false)
    private String conpanyName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HdEstado estado;
    @Column(nullable = false)
    private OffsetDateTime publishedOn = OffsetDateTime.now();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hdaccount")
    private List<HdxAreas> hdxAreas;

}

