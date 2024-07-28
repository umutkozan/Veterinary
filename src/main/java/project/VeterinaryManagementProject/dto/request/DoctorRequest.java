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
public class DoctorRequest {

    // Doktorun ismini tutar. String türünde bir değişkendir.
    private String name;

    // Doktorun telefon numarasını tutar. String türünde bir değişkendir.
    private String phone;

    // Doktorun e-posta adresini tutar. String türünde bir değişkendir.
    private String email;

    // Doktorun adresini tutar. String türünde bir değişkendir.
    private String address;

    // Doktorun çalıştığı şehri tutar. String türünde bir değişkendir.
    private String city;
}
