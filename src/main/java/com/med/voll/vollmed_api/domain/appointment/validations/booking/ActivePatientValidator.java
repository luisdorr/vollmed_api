package com.med.voll.vollmed_api.domain.appointment.validations.booking;

import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import com.med.voll.vollmed_api.domain.patient.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentBookingValidator{
    @Autowired
    private PatientRepository patientRepository;

    public void validate(BookingAppointmentData data) {
        if (data.patientId() == null) {
            return;
        }

        var patientIsActive = patientRepository.findActiveById(data.patientId());

        if (!patientIsActive) {
            throw new ValidationException("Appointment could not be finished, patient is excluded");
        }
    }
}
