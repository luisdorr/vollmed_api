package com.med.voll.vollmed_api.domain.appointment.validations.cancellation;

import com.med.voll.vollmed_api.domain.appointment.AppointmentCancellationData;

public interface AppointmentCancellationValidator {

    public void validate(AppointmentCancellationData data);

}
