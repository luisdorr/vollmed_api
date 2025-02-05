package com.med.voll.vollmed_api.controller;


import com.med.voll.vollmed_api.domain.appointment.AppointmentCancellationData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import com.med.voll.vollmed_api.domain.appointment.AppointmentBooking;
import com.med.voll.vollmed_api.domain.appointment.BookingAppointmentData;
import com.med.voll.vollmed_api.domain.appointment.DetailAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentBooking book;

    @PostMapping
    @Transactional
    public ResponseEntity booking(@RequestBody @Valid BookingAppointmentData data) {
        var appointment = book.booking(data);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancellationData data) {
        book.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
