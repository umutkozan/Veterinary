package project.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.Report;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // Belirli bir randevu kimliğine sahip raporu bulur
    Optional<Report> findByAppointmentId(Long appointmentId);

    // Belirli özelliklere sahip raporu bulur
    Optional<Report> findReportByTitleAndDiagnosisAndPriceAndAppointmentId(String title, String diagnosis, double price, Long appointment_id);
}
