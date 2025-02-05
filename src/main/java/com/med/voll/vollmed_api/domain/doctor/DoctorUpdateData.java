package com.med.voll.vollmed_api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import com.med.voll.vollmed_api.domain.address.AddressDetail;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String phoneNumber,
        AddressDetail address
) { }
