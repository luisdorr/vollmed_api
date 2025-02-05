package com.med.voll.vollmed_api.domain.appointment.validations.booking;
import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import com.med.voll.vollmed_api.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements AppointmentBookingValidator {

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(BookingAppointmentData data) {
        if (data.doctorId() == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findActiveById(data.doctorId());
        if (!doctorIsActive) {
            throw new ValidationException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
