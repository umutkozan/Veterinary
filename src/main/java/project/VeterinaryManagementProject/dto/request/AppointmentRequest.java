package project.VeterinaryManagementProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Lombok kütüphanesi kullanılarak getter, setter, tüm argümanlı ve argümansız constructor metodları otomatik olarak oluşturuluyor.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {

    // Randevu tarihini tutar. LocalDateTime türünde bir değişkendir.
    private LocalDateTime appointmentDate;

    // Randevuyu gerçekleştirecek doktorun ID'sini tutar. Long türünde bir değişkendir.
    private Long doctorId;

    // Randevunun yapılacağı hayvanın ID'sini tutar. Long türünde bir değişkendir.
    private Long animalId;
}
