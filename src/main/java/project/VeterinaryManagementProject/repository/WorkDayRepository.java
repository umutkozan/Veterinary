package project.VeterinaryManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.WorkDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    // Belirli bir doktor ve tarihe sahip çalışma gününü bulur
    Optional<WorkDay> findByWorkDayAndDoctor_DoctorId(LocalDate availableDate, Long doctorId);

    // Belirli bir doktor ve tarihe sahip çalışma gününü bulur
    Optional<WorkDay> findByDoctor_DoctorIdAndWorkDay(Long doctorId, LocalDate availableDate);

    // Doktor kimlik numarasına göre çalışma günlerini bulur
    List<WorkDay> findByDoctor_DoctorId(Long doctorId);
}
