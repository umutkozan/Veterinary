package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Long id; // Doktorun kimlik bilgisi
    private String name; // Doktorun ismi
    private String phone; // Doktorun telefon numarası
    private String email; // Doktorun e-posta adresi
    private String address; // Doktorun adresi
    private String city; // Doktorun şehri
}
