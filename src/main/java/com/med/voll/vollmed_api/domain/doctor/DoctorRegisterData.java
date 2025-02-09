package com.med.voll.vollmed_api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import com.med.voll.vollmed_api.domain.address.AddressData;

public record DoctorRegisterData(

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phoneNumber,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Expertise expertise,
        @NotNull
        @Valid
        AddressData addressData
) {}
