package com.med.voll.vollmed_api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String neighbourhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String addressCode,
        @NotBlank
        String city,
        @NotBlank
        String fu,
        String number,
        String complement
) { }
