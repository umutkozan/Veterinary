package project.VeterinaryManagementProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.VeterinaryManagementProject.entity.Animal;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Belirli özelliklere sahip bir hayvanı bulur
    Optional<Animal> findByAnimalNameAndAnimalSpeciesAndAnimalGenderAndAnimalDateOfBirth(
            String animalName, String animalSpecies, String animalGender, LocalDate animalDate_of_birth);

    // Hayvan ismine göre hayvanları sayfalı olarak arar
    Page<Animal> findByAnimalNameContaining(String animalName, Pageable pageable);

    // Müşteri ismine göre hayvanları sayfalı olarak arar
    Page<Animal> findByCustomer_CustomerNameContaining(String customerName, Pageable pageable);
}
