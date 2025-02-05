package com.med.voll.vollmed_api.domain.doctor;

public record ListingDoctorsData(Long id, String name, String email, String crm, Expertise expertise) {
    public ListingDoctorsData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise());
    }
}
