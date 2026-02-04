package com.aidaml.cc.demo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.aidaml.cc.demo.model.dto.CountryCode;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String street;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CountryCode country_code;

    @ManyToOne
    @JsonIgnore
    private User user;

}