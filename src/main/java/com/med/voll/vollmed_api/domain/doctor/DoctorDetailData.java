package com.med.voll.vollmed_api.domain.doctor;

import com.med.voll.vollmed_api.domain.address.Address;

public record DoctorDetailData(Long id, String name, String email, String crm, String phoneNumber, Expertise expertise, Address address) {
    public DoctorDetailData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhoneNumber(), doctor.getExpertise(), doctor.getAddress());
    }
}
