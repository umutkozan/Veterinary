package project.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Table(name = "doctor") // Veritabanında doctor tablosunu temsil eder.
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "doctor_name") // Doktorun ismini tutar.
    private String doctorName;

    @Column(name = "doctor_phone") // Doktorun telefon numarasını tutar.
    private String doctorPhone;

    @Column(name = "doctor_email") // Doktorun e-posta adresini tutar.
    private String doctorEmail;

    @Column(name = "doctor_address") // Doktorun adresini tutar.
    private String doctorAddress;

    @Column(name = "doctor_city") // Doktorun şehrini tutar.
    private String doctorCity;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Bir doktorun birden fazla çalışma günü olabilir.
    @JsonIgnore
    private List<WorkDay> workDays;

    @OneToMany(mappedBy = "doctor") // Bir doktorun birden fazla randevusu olabilir.
    @JsonIgnore
    private List<Appointment> appointments;
}
