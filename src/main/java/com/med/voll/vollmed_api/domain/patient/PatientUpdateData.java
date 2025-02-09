package com.med.voll.vollmed_api.domain.patient;

import jakarta.validation.constraints.NotNull;
import com.med.voll.vollmed_api.domain.address.AddressData;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
