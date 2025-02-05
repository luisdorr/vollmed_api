package com.med.voll.vollmed_api.domain.appointment;

import java.time.LocalDateTime;

public record DetailAppointmentData(Long id, Long doctorId, Long patientId, LocalDateTime date) {
    public DetailAppointmentData(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
