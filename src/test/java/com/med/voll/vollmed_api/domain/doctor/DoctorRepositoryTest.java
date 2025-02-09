package com.med.voll.vollmed_api.domain.doctor;

import com.med.voll.vollmed_api.domain.address.AddressData;
import com.med.voll.vollmed_api.domain.appointment.Appointment;
import com.med.voll.vollmed_api.domain.patient.Patient;
import com.med.voll.vollmed_api.domain.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return null when the only registered doctor is not available on the date")
    void chooseDoctorWithFreeSlotScenario1() {
        //arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456" ,Expertise.CARDIOLOGY);
        var patient = registerPatient("Patient", "patient@email.com", "00000000000" );
        registerAppointment(doctor, patient, nextMondayAt10);

        //act
        var availableDoctor = doctorRepository.chooseDoctorWithFreeSlot(Expertise.CARDIOLOGY, nextMondayAt10);

        //assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return the available doctor when only one doctor is registered and free")
    void chooseDoctorWithFreeSlotScenario2() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Expertise.CARDIOLOGY);

        // Act
        var availableDoctor = doctorRepository.chooseDoctorWithFreeSlot(Expertise.CARDIOLOGY, nextMondayAt10);

        // Assert
        assertThat(availableDoctor).isNotNull();
        assertThat(availableDoctor.getId()).isEqualTo(doctor.getId());
    }

    @Test
    @DisplayName("Should return the available doctor when multiple doctors are registered but only one is free")
    void chooseDoctorWithFreeSlotScenario3() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor1 = registerDoctor("Doctor 1", "doctor1@voll.med", "123456", Expertise.CARDIOLOGY);
        var doctor2 = registerDoctor("Doctor 2", "doctor2@voll.med", "654321", Expertise.CARDIOLOGY);

        var patient = registerPatient("Patient", "patient@email.com", "00000000000");
        registerAppointment(doctor1, patient, nextMondayAt10); // Doctor 1 is busy

        // Act
        var availableDoctor = doctorRepository.chooseDoctorWithFreeSlot(Expertise.CARDIOLOGY, nextMondayAt10);

        // Assert
        assertThat(availableDoctor).isNotNull();
        assertThat(availableDoctor.getId()).isEqualTo(doctor2.getId());
    }

    @Test
    @DisplayName("Should return null when multiple doctors are registered but none are available")
    void chooseDoctorWithFreeSlotScenario5() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor1 = registerDoctor("Doctor 1", "doctor1@voll.med", "123456", Expertise.CARDIOLOGY);
        var doctor2 = registerDoctor("Doctor 2", "doctor2@voll.med", "654321", Expertise.CARDIOLOGY);

        var patient = registerPatient("Patient", "patient@email.com", "00000000000");
        registerAppointment(doctor1, patient, nextMondayAt10); // Doctor 1 is busy
        registerAppointment(doctor2, patient, nextMondayAt10); // Doctor 2 is busy

        // Act
        var availableDoctor = doctorRepository.chooseDoctorWithFreeSlot(Expertise.CARDIOLOGY, nextMondayAt10);

        // Assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return null when no doctors match the requested expertise")
    void chooseDoctorWithFreeSlotScenario6() {
        // Arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Expertise.DERMATOLOGY); // Different expertise

        // Act
        var availableDoctor = doctorRepository.chooseDoctorWithFreeSlot(Expertise.CARDIOLOGY, nextMondayAt10);

        // Assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return null when the requested date is in the past")
    void chooseDoctorWithFreeSlotScenario7() {
        // Arrange
        var pastDate = LocalDate.now().minusDays(1).atTime(10, 0); // Yesterday at 10:00

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Expertise.CARDIOLOGY);

        // Act
        var availableDoctor = doctorRepository.chooseDoctorWithFreeSlot(Expertise.CARDIOLOGY, pastDate);

        // Assert
        assertThat(availableDoctor).isNull();
    }











    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor registerDoctor(String name, String email, String crm, Expertise expertise) {
        var doctor = new Doctor(doctorData(name, email, crm, expertise));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorRegisterData doctorData(String name, String email, String crm, Expertise expertise) {
        return new DoctorRegisterData(
                name,
                email,
                "61999999999", // Phone number
                crm,
                expertise,
                addressData()
        );
    }

    private PatientRegistrationData patientData(String name, String email, String cpf) {
        return new PatientRegistrationData(
                name,
                email,
                "61999999999", // Phone number
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "xpto street", // Street
                "neighborhood", // Neighborhood
                "00000000", // ZIP code
                "Brasilia", // City
                "DF", // State
                null, // Complement (optional)
                null // Number (optional)
        );
    }
}