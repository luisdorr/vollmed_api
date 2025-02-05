package com.med.voll.vollmed_api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDateAndCancellationReasonIsNull(long doctorId, LocalDateTime date);

    Boolean existsAppointmentsByPatient_IdAndDate(Long patientId, LocalDateTime date);

    Boolean existsByPatient_IdAndDateBetween(Long aLong, LocalDateTime firstAppointment, LocalDateTime lastAppointment);
}
