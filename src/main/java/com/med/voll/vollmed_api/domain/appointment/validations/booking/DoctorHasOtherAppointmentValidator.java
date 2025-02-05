package com.med.voll.vollmed_api.domain.appointment.validations.booking;

import com.med.voll.vollmed_api.domain.appointment.AppointmentRepository;
import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorHasOtherAppointmentValidator implements AppointmentBookingValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(BookingAppointmentData data) {
        var doctorHasOtherAppointmentAtThisTime = appointmentRepository.existsByDoctorIdAndDateAndCancellationReasonIsNull(data.doctorId(), data.date());
        if (doctorHasOtherAppointmentAtThisTime) {
            throw new ValidationException("Doctor has an other appointment at this time.");
        }
    }
}
