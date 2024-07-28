package project.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.Appointment;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Belirli bir doktor ve hayvan için belirli bir tarihteki randevuyu bulur
    Optional<Appointment> findByAppointmentDateAndDoctorDoctorIdAndAnimalAnimalId(LocalDateTime date, Long doctorId, Long animalId);

    // Belirli bir doktor için belirli bir tarihteki randevuyu bulur
    Optional<Appointment> findByAppointmentDateAndDoctorDoctorId(LocalDateTime date, Long doctorId);

    // Belirli bir tarih aralığındaki randevuları sayfalı olarak getirir
    @Query("select a from Appointment a where a.appointmentDate between :startDate and :endDate")
    Page<Appointment> findByAppointmentDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    // Belirli bir doktor ve tarih aralığındaki randevuları sayfalı olarak getirir
    @Query("select a from Appointment a where a.appointmentDate between :startDate and :endDate and a.doctor.doctorId = :doctorId")
    Page<Appointment> findByDoctor_DoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    // Belirli bir hayvan ve tarih aralığındaki randevuları sayfalı olarak getirir
    @Query("select a from Appointment a where a.appointmentDate between :startDate and :endDate and a.animal.animalId = :animalId")
    Page<Appointment> findByAnimal_AnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
