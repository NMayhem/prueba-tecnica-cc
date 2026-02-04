package com.aidaml.cc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aidaml.cc.demo.model.domain.Address;
import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.CountryCode;
import com.aidaml.cc.demo.repository.AddressRepository;
import com.aidaml.cc.demo.repository.UserRepository;
import com.aidaml.cc.demo.security.AESEncryptionService;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Component
public class Startup {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

    @Autowired
    AESEncryptionService aesEncryptionService;

    // This method runs after the application has started in order to populate the DB with our 3 user objects.
	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {

        try {
            User usr1 = new User();
            User usr2 = new User();
            User usr3 = new User();
    
            usr1.setEmail("user1@mail.com");
            usr1.setName("user1");
            usr1.setPhone("+11234567890");
            usr1.setPassword(aesEncryptionService.encrypt("password1"));
            usr1.setTaxId("ABCD990101XX0");
    
            usr2.setEmail("user2@mail.com");
            usr2.setName("user2");
            usr2.setPhone("+15555555556");
            usr2.setPassword(aesEncryptionService.encrypt("password2"));
            usr2.setTaxId("ABCD990101XX1");
    
            usr3.setEmail("user3@mail.com");
            usr3.setName("user3");
            usr3.setPhone("+15555555557");
            usr3.setPassword(aesEncryptionService.encrypt("password3"));
            usr3.setTaxId("ABCD990101XX2");
    
            userRepository.save(usr1);
            userRepository.save(usr2);
            userRepository.save(usr3);
    
            Address adr1 = new Address();
            Address adr2 = new Address();
            Address adr3 = new Address();
            Address adr4 = new Address();
            Address adr5 = new Address();
    
            adr1.setName("workaddress");
            adr1.setStreet("street No. 1");
            adr1.setCountry_code(CountryCode.BR);
            adr1.setUser(usr1);
    
            adr2.setName("homeaddress");
            adr2.setStreet("street No. 2");
            adr2.setCountry_code(CountryCode.US);
            adr2.setUser(usr1);
    
            adr3.setName("workaddress");
            adr3.setStreet("street No. 3");
            adr3.setCountry_code(CountryCode.MX);
            adr3.setUser(usr2);
    
            adr4.setName("homeaddress");
            adr4.setStreet("street No. 4");
            adr4.setCountry_code(CountryCode.AL);
            adr4.setUser(usr2);
    
            adr5.setName("homeaddress");
            adr5.setStreet("street No. 5");
            adr5.setCountry_code(CountryCode.BA);
            adr5.setUser(usr3);
    
            addressRepository.save(adr1);
            addressRepository.save(adr2);
            addressRepository.save(adr3);
            addressRepository.save(adr4);
            addressRepository.save(adr5);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
	}

}
