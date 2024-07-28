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
public class AnimalCustomerRequest {

    // Hayvanın ID'sini tutar.
    private Long id;

    // Hayvanın adını tutar.
    private String name;

    // Hayvanın türünü tutar (örneğin, kedi, köpek).
    private String species;

    // Hayvanın cinsini tutar (örneğin, Golden Retriever).
    private String breed;

    // Hayvanın cinsiyetini tutar (örneğin, erkek, dişi).
    private String gender;

    // Hayvanın doğum tarihini tutar.
    private LocalDate dateOfBirth;

    // Hayvanın rengini tutar.
    private String colour;
}
