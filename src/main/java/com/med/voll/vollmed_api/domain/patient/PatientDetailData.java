package com.med.voll.vollmed_api.domain.patient;

import com.med.voll.vollmed_api.domain.address.Address;

public record PatientDetailData(Long id, String name, String email, String cpf, String phone, Address address) {
    public PatientDetailData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail()
                , patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
