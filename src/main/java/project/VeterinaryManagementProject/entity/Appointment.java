package project.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "appointment") // Veritabanında appointment tablosunu temsil eder.
@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    @Column(name = "id")
    private Long id;

    @Column(name = "appointment_date") // Randevu tarihini tutar.
    private LocalDateTime appointmentDate;

    @ManyToOne(fetch = FetchType.EAGER) // Bir randevu bir doktora ait olabilir.
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER) // Bir randevu bir hayvana ait olabilir.
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true) // Bir randevunun bir raporu olabilir.
    @JsonIgnore
    private Report report;
}
