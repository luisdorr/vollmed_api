package com.med.voll.vollmed_api.domain.appointment.validations.booking;

import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;

@Component
public class TimeInAdvanceValidator implements AppointmentBookingValidator{

    public void validate(BookingAppointmentData data) {
        var appointmentDate = data.date();

        var now = LocalDate.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("The appointment must be booked at least 30 minutes in advance.");
        }
    }
}
