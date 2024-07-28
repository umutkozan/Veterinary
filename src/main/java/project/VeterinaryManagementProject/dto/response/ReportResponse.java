package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {

    // Raporun kimlik bilgisi
    private Long id;

    // Raporun başlığı
    private String title;

    // Raporun teşhis bilgisi
    private String diagnosis;

    // Raporun fiyatı
    private double price;

    // Raporla ilişkilendirilen randevu bilgisi
    private AppointmentReportResponse appointment;
}
