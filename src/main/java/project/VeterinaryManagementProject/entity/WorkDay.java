package project.VeterinaryManagementProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Table(name = "work_day") // Veritabanında work_day tablosunu temsil eder.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    @Column(name = "id")
    private Long id;

    @Column(name = "work_date") // Çalışma gününü tutar.
    private LocalDate workDay;

    @ManyToOne(fetch = FetchType.EAGER) // Bir çalışma günü bir doktora ait olabilir.
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
