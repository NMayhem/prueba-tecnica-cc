package com.aidaml.cc.demo.repository;

import com.aidaml.cc.demo.model.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer>{
}
