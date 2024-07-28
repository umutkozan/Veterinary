package project.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Belirli bir isim ve e-postaya sahip müşteriyi bulur
    Optional<Customer> findByCustomerNameAndCustomerEmail(String customerName, String customerEmail);

    // Müşteri ismine göre müşterileri sayfalı olarak arar
    Page<Customer> findByCustomerNameContaining(String customerName, Pageable pageable);
}
