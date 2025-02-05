package com.med.voll.vollmed_api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import com.med.voll.vollmed_api.domain.doctor.Expertise;

import java.time.LocalDateTime;

public record BookingAppointmentData(
        Long doctorId,

        @NotNull
        Long patientId,

        @NotNull
        @Future
        LocalDateTime date,

        Expertise expertise
) {
}
