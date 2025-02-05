package com.med.voll.vollmed_api.domain.appointment.validations.booking;

import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;

public interface AppointmentBookingValidator {

    public void validate(BookingAppointmentData data);
}
