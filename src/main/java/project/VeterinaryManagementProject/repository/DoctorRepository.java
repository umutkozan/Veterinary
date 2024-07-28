package project.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByDoctorEmail(String doctorEmail);
    Optional<Doctor> findByDoctorNameAndDoctorEmail(String doctorName, String doctorEmail);
    Page<Doctor> findByDoctorNameContaining(String doctorName, Pageable pageable);
}

