package com.med.voll.vollmed_api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    String street;
    String neighbourhood;
    String addressCode;
    String city;
    String fu;
    String number;
    String complement;

    public Address(AddressData addressData) {
        this.street = addressData.street();
        this.neighbourhood = addressData.neighbourhood();
        this.addressCode = addressData.addressCode();
        this.city = addressData.city();
        this.fu = addressData.fu();
        this.number = addressData.number();
        this.complement = addressData.complement();
    }

    public void updateAddress(AddressData data) {
        this.street = (data.street() != null) ? data.street() : this.street;
        this.neighbourhood = (data.neighbourhood() != null) ? data.neighbourhood() : this.neighbourhood;
        this.addressCode = (data.addressCode() != null) ? data.addressCode() : this.addressCode;
        this.city = (data.city() != null) ? data.city() : this.city;
        this.fu = (data.fu() != null) ? data.fu() : this.fu;
        this.number = (data.number() != null) ? data.number() : this.number;
        this.complement = (data.complement() != null) ? data.complement() : this.complement;
    }
}
