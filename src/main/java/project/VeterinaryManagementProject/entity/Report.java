package project.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Table // Veritabanında report tablosunu temsil eder.
@Data // Lombok kütüphanesi kullanılarak getter, setter ve diğer metodları otomatik olarak oluşturur.
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    private Long id;

    private String title; // Rapor başlığını tutar.

    private String diagnosis; // Teşhisi tutar.

    private double price; // Raporun fiyatını tutar.

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment; // Raporla ilişkilendirilen randevuyu tutar.

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true) // Bir raporun birçok aşısı olabilir.
    @JsonIgnore
    private List<Vaccination> vaccinationList;
}
