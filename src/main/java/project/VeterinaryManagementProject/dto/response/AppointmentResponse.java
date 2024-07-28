package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.VeterinaryManagementProject.entity.Animal;
import project.VeterinaryManagementProject.entity.Doctor;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id; // Randevunun kimlik bilgisi
    private LocalDateTime appointmentDate; // Randevu tarihi
    private Doctor doctor; // Randevunun doktoru
    private Animal animal; // Randevunun hayvanı
}
