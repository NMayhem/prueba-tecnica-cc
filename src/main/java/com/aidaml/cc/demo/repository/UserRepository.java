package com.aidaml.cc.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aidaml.cc.demo.model.domain.User;

public interface UserRepository extends JpaRepository<User, UUID>{

    User findByTaxId(String taxId);

}
