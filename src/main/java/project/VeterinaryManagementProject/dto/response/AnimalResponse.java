package project.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.VeterinaryManagementProject.entity.Customer;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponse {

    private Long id; // Hayvanın kimlik bilgisi
    private String name; // Hayvanın ismi
    private String species; // Hayvanın türü
    private String breed; // Hayvanın cinsi
    private String gender; // Hayvanın cinsiyeti
    private String colour; // Hayvanın rengi
    private LocalDate dateOfBirth; // Hayvanın doğum tarihi
    private Customer customer; // Hayvanın sahibi olan müşteri
}
