package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id; // Müşterinin kimlik bilgisi
    private String name; // Müşterinin ismi
    private String phone; // Müşterinin telefon numarası
    private String email; // Müşterinin e-posta adresi
    private String address; // Müşterinin adresi
    private String city; // Müşterinin şehri
}
