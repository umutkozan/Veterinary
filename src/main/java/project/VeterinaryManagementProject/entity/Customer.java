package project.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity // Bu sınıfın bir JPA entity olduğunu belirtir.
@Table(name = "customer") // Veritabanında customer tablosunu temsil eder.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik olarak artırılacağını belirtir.
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", length = 100) // Müşteri ismini tutar.
    private String customerName;

    @Column(name = "customer_phone", length = 20) // Müşteri telefonunu tutar.
    private String customerPhone;

    @Column(name = "customer_email", length = 100) // Müşteri e-posta adresini tutar.
    private String customerEmail;

    @Column(name = "customer_address", length = 250) // Müşteri adresini tutar.
    private String customerAddress;

    @Column(name = "customer_city", length = 50) // Müşteri şehrini tutar.
    private String customerCity;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE) // Bir müşterinin birden fazla hayvanı olabilir.
    @JsonIgnore
    private List<Animal> animals;
}
