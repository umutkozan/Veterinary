package project.VeterinaryManagementProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.VeterinaryManagementProject.entity.Appointment;

// Lombok kütüphanesi kullanılarak getter, setter, tüm argümanlı ve argümansız constructor metodları otomatik olarak oluşturuluyor.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    // Rapor başlığını tutar. String türünde bir değişkendir.
    private String title;

    // Teşhisi tutar. String türünde bir değişkendir.
    private String diagnosis;

    // Raporun fiyatını tutar. double türünde bir değişkendir.
    private double price;

    // Raporla ilişkilendirilen randevuyu tutar. Appointment türünde bir değişkendir.
    private Appointment appointment;
}
