package com.med.voll.vollmed_api.domain.appointment;

import com.med.voll.vollmed_api.domain.appointment.validations.booking.AppointmentBookingValidator;
import com.med.voll.vollmed_api.domain.appointment.validations.cancellation.AppointmentCancellationValidator;
import jakarta.validation.ValidationException;
import com.med.voll.vollmed_api.domain.doctor.Doctor;
import com.med.voll.vollmed_api.domain.doctor.DoctorRepository;
import com.med.voll.vollmed_api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentBooking {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private List<AppointmentBookingValidator> appointmentBookingValidators;
    @Autowired
    private List<AppointmentCancellationValidator> appointmentCancellationValidators;

    public DetailAppointmentData booking(BookingAppointmentData data){
        if (!patientRepository.existsById(data.patientId())) {
            throw new ValidationException("Patient Id does not exist!");
        }
        if ( data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationException("Doctor Id does not exist!");
        }

        appointmentBookingValidators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.patientId());
        var doctor = chooseDoctor(data);
        if (doctor == null) {
            throw new ValidationException("There is no doctor available for this date!");
        }
        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new DetailAppointmentData(appointment);
    }

    private Doctor chooseDoctor(BookingAppointmentData data) {
        if (data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if (data.expertise() == null) {
            throw new ValidationException("Expertise is obligatory if doctor_id not informed");
        }

        return  doctorRepository.chooseDoctorWithFreeSlot(data.expertise(), data.date());
    }

    public void cancel(AppointmentCancellationData data) {
        if (!appointmentRepository.existsById(data.appointmentId())) {
            throw new ValidationException("The provided appointment ID does not exist!");
        }

        appointmentCancellationValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        appointment.cancel(data.reason());
    }
}
