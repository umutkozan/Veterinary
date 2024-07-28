package project.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.Vaccination;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    // Belirli özelliklere sahip koruyucu aşıları bulur
    List<Vaccination> findByNameAndCodeAndAnimal_AnimalIdAndProtectionFinishDateGreaterThanEqual(String name, String code, Long animalId, LocalDate protectionStartDate);

    // Hayvan ismine göre aşıları sayfalı olarak arar
    @Query(value = "SELECT v FROM Vaccination v WHERE v.animal.animalName = :name")
    Page<Vaccination> findByAnimalName(String name, Pageable pageable);

    // Tarih aralığına göre aşıları sayfalı olarak arar
    @Query(value = "SELECT v FROM Vaccination v WHERE :startDate >= v.protectionStartDate AND :endDate <= v.protectionFinishDate")
    Page<Vaccination> findByDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

    // Aşı ismine göre aşıları sayfalı olarak arar
    Page<Vaccination> findByName(String name, Pageable pageable);

    // Koruma bitiş tarihine göre aşıları sayfalı olarak arar
    Page<Vaccination> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
