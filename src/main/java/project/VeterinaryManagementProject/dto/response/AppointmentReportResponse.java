package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentReportResponse {

    private Long id; // Randevu raporunun kimlik bilgisi
    private LocalDateTime date; // Randevu tarihi
    private String customerName; // Müşteri ismi
    private String animalName; // Hayvan ismi
    private String doctorName; // Doktor ismi
}
