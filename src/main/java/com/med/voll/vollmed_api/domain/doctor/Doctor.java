package com.med.voll.vollmed_api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.med.voll.vollmed_api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Doctor {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String phoneNumber;
    @Getter
    private String crm;
    @Getter
    @Enumerated(EnumType.STRING)
    private Expertise expertise;
    @Getter
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(DoctorRegisterData data) {
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.crm = data.crm();
        this.expertise = data.expertise();
        this.address = new Address(data.addressDetail());
        this.active = true;
    }

    public void updateInfo(DoctorUpdateData data) {
        this.name = (data.name() != null) ? data.name() : this.name;
        this.phoneNumber = (data.phoneNumber() != null) ? data.phoneNumber() : this.phoneNumber;
        if(data.address() != null) { this.address.updateAddress(data.address()); }
    }

    public void exclude() {
        this.active = false;
    }
}
