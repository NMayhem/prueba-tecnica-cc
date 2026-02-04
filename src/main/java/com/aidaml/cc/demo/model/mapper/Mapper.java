package com.aidaml.cc.demo.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aidaml.cc.demo.model.domain.Address;
import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.AddressDto;
import com.aidaml.cc.demo.model.dto.UserDto;
import com.aidaml.cc.demo.security.AESEncryptionComponent;

@Component
public class Mapper {

    @Autowired
    AESEncryptionComponent aesEncryptionComponent;

    public User userDtoToEntity(UserDto userDto) {
    
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setTaxId(userDto.getTax_id());

        try {
            user.setPassword(aesEncryptionComponent.cbcEncrypt(userDto.getPassword()));
        } catch(Exception e) {
            System.out.println("Password encryption failed: " + e.getMessage());
        }

        return user;
    }

    public Address addressDtoToEntity(AddressDto addressDto, User user) {
    
        Address address = new Address();

        address.setName(addressDto.getName());
        address.setStreet(addressDto.getStreet());
        address.setCountry_code(addressDto.getCountry_code());
        address.setUser(user);

        return address;
    }

}
