package com.med.voll.vollmed_api.domain.appointment.validations.booking;

import com.med.voll.vollmed_api.domain.appointment.AppointmentRepository;
import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientHasOtherAppointmentOnDayValidator implements AppointmentBookingValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(BookingAppointmentData data) {
        var firstAppointment = data.date().withHour(7);
        var lastAppointment = data.date().withHour(18);

        var patientHasOtherAppointmentOnDay = appointmentRepository.existsByPatient_IdAndDateBetween(data.patientId(), firstAppointment, lastAppointment);
        if (patientHasOtherAppointmentOnDay) {
            throw new ValidationException("Patient has an other appointment at this time.");
        }
    }
}
