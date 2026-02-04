package com.aidaml.cc.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank // Notes 3: 10 digit phone number with an optional phone country code.
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10}$", message = "Enter a 10 digit phone number (Country code optional).")
    private String phone;

    @NotBlank
    private String password;

    @NotBlank // Notes 3: RFC format. Not too sure about this one.
    @Pattern(regexp = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$", message = "Enter a tax id in Mexico's RFC format.")
    private String tax_id;

    @NotEmpty
    private List<AddressDto> addresses;

}