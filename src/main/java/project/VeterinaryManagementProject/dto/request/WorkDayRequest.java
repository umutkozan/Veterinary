package project.VeterinaryManagementProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

// Lombok kütüphanesi kullanılarak getter, setter, tüm argümanlı ve argümansız constructor metodları otomatik olarak oluşturuluyor.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDayRequest {

    // Çalışma gününü tutar. LocalDate türünde bir değişkendir.
    private LocalDate workDay;

    // Çalışma gününün ait olduğu doktorun id'sini tutar. Long türünde bir değişkendir.
    private Long doctorId;
}
