package project.VeterinaryManagementProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.VeterinaryManagementProject.entity.Report;
import java.time.LocalDate;

// Lombok kütüphanesi kullanılarak getter, setter, tüm argümanlı ve argümansız constructor metodları otomatik olarak oluşturuluyor.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationRequest {

    // Aşının ismini tutar. String türünde bir değişkendir.
    private String name;

    // Aşının kodunu tutar. String türünde bir değişkendir.
    private String code;

    // Aşının koruma başlangıç tarihini tutar. LocalDate türünde bir değişkendir.
    private LocalDate protectionStartDate;

    // Aşının koruma bitiş tarihini tutar. LocalDate türünde bir değişkendir.
    private LocalDate protectionFinishDate;

    // Müşteri bilgisi olmadan hayvan bilgilerini tutar. AnimalCustomerRequest türünde bir değişkendir.
    private AnimalCustomerRequest animalWithoutCustomer;

    // Rapor bilgilerini tutar. Report türünde bir değişkendir.
    private Report report;
}
