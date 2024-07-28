package project.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Table(name = "animal") // Veritabanında animal tablosunu temsil eder.
@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    @Column(name = "id", nullable = false)
    private Long animalId;

    @Column(name = "animal_name") // Hayvanın ismini tutar.
    private String animalName;

    @Column(name = "animal_species") // Hayvanın türünü tutar.
    private String animalSpecies;

    @Column(name = "animal_breed") // Hayvanın cinsini tutar.
    private String animalBreed;

    @Column(name = "animal_gender") // Hayvanın cinsiyetini tutar.
    private String animalGender;

    @Column(name = "animal_colour") // Hayvanın rengini tutar.
    private String animalColour;

    @Column(name = "animal_date_of_birth") // Hayvanın doğum tarihini tutar.
    private LocalDate animalDateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER) // Bir hayvan bir müşteriye ait olabilir.
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true) // Bir hayvanın birçok aşısı olabilir.
    @JsonIgnore
    private List<Vaccination> vaccinations;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true) // Bir hayvanın birçok randevusu olabilir.
    @JsonIgnore
    private List<Appointment> appointments;
}
