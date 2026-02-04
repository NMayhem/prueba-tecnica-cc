package com.aidaml.cc.demo.model.mapper;

import org.springframework.stereotype.Component;

import com.aidaml.cc.demo.model.domain.Address;
import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.AddressDto;
import com.aidaml.cc.demo.model.dto.UserDto;

@Component
public class Mapper {

    public User userDtoToEntity(UserDto userDto) {
    
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setTaxId(userDto.getTax_id());
        user.setPassword(userDto.getPassword());

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
