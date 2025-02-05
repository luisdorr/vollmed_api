package com.med.voll.vollmed_api.domain.appointment.validations.booking;

import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkingTimeValidator implements AppointmentBookingValidator{

    public void validate(BookingAppointmentData data) {
        var appointmentDate = data.date();

        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpened = appointmentDate.getHour() < 7;
        var afterClinicClosed = appointmentDate.getHour() > 18;

        if (sunday || beforeClinicOpened || afterClinicClosed) {
            throw new ValidationException("Appointment out of clinic hours");
        }
    }
}
