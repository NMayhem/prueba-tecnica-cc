package com.aidaml.cc.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @Email
    private String email;

    private String name;

    // Notes 3: See UserCreationDto.
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10}$", message = "Enter a 10 digit phone number (Country code optional).")
    private String phone;

    private String password;

    // Notes 3: See UserCreationDto.
    @Pattern(regexp = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$", message = "Enter a tax id in Mexico's RFC format.")
    private String tax_id;

    private List<AddressDto> addresses;

}