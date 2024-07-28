package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.VeterinaryManagementProject.entity.Doctor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDayResponse {

    private Long id; // Çalışma gününün kimlik bilgisi
    private LocalDate workDay; // Çalışma tarihi
    private Doctor doctor; // Çalışma gününün doktoru
}
