package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.VeterinaryManagementProject.entity.Animal;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationResponse {

    private Long id; // Aşının kimlik bilgisi
    private String name; // Aşının ismi
    private String code; // Aşının kodu
    private LocalDate protectionStartDate; // Koruma başlangıç tarihi
    private LocalDate protectionFinishDate; // Koruma bitiş tarihi
    private Animal animal; // Aşının uygulandığı hayvan
    private ReportResponse report; // Aşının ilişkilendirildiği rapor
}
