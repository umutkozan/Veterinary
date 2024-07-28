package project.VeterinaryManagementProject.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AnimalRequest {

    // Hayvanın adını tutar. Schema anotasyonu ile Swagger dökümantasyonuna katkı sağlar.
    @Schema
    private String name;

    // Hayvanın türünü tutar (örneğin, kedi, köpek). Boş bırakılamaz ve Swagger dökümantasyonu için örnek değer içerir.
    @Schema(example = "Köpek")
    @NotBlank(message = "Tür kısmı zorunludur.")
    private String species;

    // Hayvanın cinsini tutar (örneğin, Labrador). Boş bırakılamaz ve Swagger dökümantasyonu için örnek değer içerir.
    @Schema(example = "Labrador")
    @NotBlank(message = "Cins zorunlu.")
    private String breed;

    // Hayvanın cinsiyetini tutar (örneğin, erkek, dişi). Boş bırakılamaz ve Swagger dökümantasyonu için örnek değer içerir.
    @Schema(example = "Male")
    @NotBlank(message = "Cinsiyet zorunlu.")
    private String gender;

    // Hayvanın doğum tarihini tutar. Tarih formatı belirtilir ve boş bırakılamaz.
    @Schema(example = "2020-01-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Doğum tarihi zorunlu.")
    private LocalDate dateOfBirth;

    // Hayvanın rengini tutar. Boş bırakılamaz ve Swagger dökümantasyonu için örnek değer içerir.
    @Schema(example = "Black")
    @NotBlank(message = "Renk zorunlu.")
    private String colour;

    // Hayvanın ait olduğu müşterinin ID'sini tutar. Boş bırakılamaz ve Swagger dökümantasyonu için örnek değer içerir.
    @Schema(example = "1")
    @NotNull(message = "Müşteri ıd'si zorunlu.")
    private Long customerId;
}
