package com.med.voll.vollmed_api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pages);

    @Query("""
        SELECT d FROM Doctor d
        WHERE
        d.active = true
        and
        d.expertise = :specialty
        and
        d.id not in (
            SELECT a.doctor.id FROM Appointment a
            WHERE
            a.date = :date
            and
            a.cancellationReason is null
        )
        order by rand()
        limit 1
""")
    Doctor chooseDoctorWithFreeSlot(Expertise expertise, LocalDateTime date);

    @Query("""
            SELECT d.active
            FROM Doctor d
            WHERE d.id = :id
            """)
    Boolean findActiveById(Long id);
}
