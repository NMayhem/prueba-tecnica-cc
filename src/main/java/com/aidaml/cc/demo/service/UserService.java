package com.aidaml.cc.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aidaml.cc.demo.model.domain.Address;
import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.UserDto;
import com.aidaml.cc.demo.exception.DuplicateUsernameException;
import com.aidaml.cc.demo.model.mapper.Mapper;
import com.aidaml.cc.demo.repository.AddressRepository;
import com.aidaml.cc.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    Mapper mapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public String create(UserDto userDto) {

        if (userRepository.findByTaxId(userDto.getTax_id()) != null) {
            throw new DuplicateUsernameException();
        }

        User user = mapper.userDtoToEntity(userDto);

        userRepository.save(user);
        
        List<Address> addresses = userDto.getAddresses()
                                  .stream()
                                  .map(e -> mapper.addressDtoToEntity(e, user))
                                  .collect(Collectors.toList());
        
        addressRepository.saveAll(addresses);

        return "User saved successfully.";
    }

    @Transactional
    public String delete(UUID id) {

        userRepository.deleteById(id);

        return "User deleted successfully.";

    }

}
