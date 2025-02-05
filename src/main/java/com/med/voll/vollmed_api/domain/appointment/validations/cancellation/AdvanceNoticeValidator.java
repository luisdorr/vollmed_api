package com.med.voll.vollmed_api.domain.appointment.validations.cancellation;

import com.med.voll.vollmed_api.domain.appointment.AppointmentCancellationData;
import com.med.voll.vollmed_api.domain.appointment.AppointmentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceNoticeValidator implements AppointmentCancellationValidator {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCancellationData data) {
        var appointment = repository.getReferenceById(data.appointmentId());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Appointments can only be canceled with a minimum of 24 hours' notice!");
        }
    }
}
