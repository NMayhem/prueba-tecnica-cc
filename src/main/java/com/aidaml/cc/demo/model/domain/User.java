package com.aidaml.cc.demo.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @UuidGenerator
    private String id;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String phone;

    @Column
    @NotNull
    @JsonIgnore
    private String password;

    @Column(unique=true) // Notes 4: Enforce taxId uniqueness.
    @NotNull
    private String taxId;

    @Column
    @NotNull
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm")
    private String createdAt;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @PrePersist  
    protected void onCreate() { 
        // Notes 2: Init timestamp in the Madagascar time zone, yyyy-MM-dd HH:mm format.
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        createdAt = LocalDateTime.now(ZoneId.of("Indian/Antananarivo")).format(dateTimeFormatter);
    }  

}