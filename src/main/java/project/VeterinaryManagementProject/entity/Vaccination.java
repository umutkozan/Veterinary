package project.VeterinaryManagementProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Table(name = "vaccination") // Veritabanında vaccination tablosunu temsil eder.
@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    @Column(name = "id")
    private Long id;

    @Column(name = "name") // Aşının ismini tutar.
    private String name;

    @Column(name = "code") // Aşının kodunu tutar.
    private String code;

    @Column(name = "protection_start_date") // Koruma başlangıç tarihini tutar.
    private LocalDate protectionStartDate;

    @Column(name = "protection_finish_date") // Koruma bitiş tarihini tutar.
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.EAGER) // Bir aşının bir hayvana ait olabilir.
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY) // Bir aşının bir rapora ait olabilir.
    @JoinColumn(name = "report_id")
    private Report report;
}
