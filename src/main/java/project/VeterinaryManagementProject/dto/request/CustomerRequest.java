package project.VeterinaryManagementProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok kütüphanesi kullanılarak getter, setter, tüm argümanlı ve argümansız constructor metodları otomatik olarak oluşturuluyor.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    // Müşterinin ismini tutar. String türünde bir değişkendir.
    private String customerName;

    // Müşterinin telefon numarasını tutar. String türünde bir değişkendir.
    private String customerPhone;

    // Müşterinin e-posta adresini tutar. String türünde bir değişkendir.
    private String customerEmail;

    // Müşterinin adresini tutar. String türünde bir değişkendir.
    private String customerAddress;

    // Müşterinin yaşadığı şehri tutar. String türünde bir değişkendir.
    private String customerCity;
}
