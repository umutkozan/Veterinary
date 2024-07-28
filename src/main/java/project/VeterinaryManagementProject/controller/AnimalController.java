package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.AnimalRequest;
import project.VeterinaryManagementProject.dto.response.AnimalResponse;
import project.VeterinaryManagementProject.service.AnimalManager;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    // AnimalManager sınıfı, hayvan yönetimi ile ilgili iş mantığını içerir
    private final AnimalManager animalService;

    // Tüm hayvanları sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<AnimalResponse>> findAllAnimals(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAllAnimals(pageNumber, pageSize));
    }

    // Belirli bir hayvanı kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findAnimalById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAnimalById(id));
    }

    // Hayvanları isimlerine göre aramak için endpoint
    @GetMapping("/searchByName")
    public ResponseEntity<Page<AnimalResponse>> findAnimalsByName(
            @RequestParam String name,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAnimalsByName(name, pageNumber, pageSize));
    }

    // Hayvanları sahiplerinin isimlerine göre aramak için endpoint
    @GetMapping("/searchByCustomerName")
    public ResponseEntity<Page<AnimalResponse>> findAnimalsByCustomerName(
            @RequestParam String customerName,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAnimalsByCustomerName(customerName, pageNumber, pageSize));
    }

    // Yeni bir hayvan oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.createAnimal(animalRequest));
    }

    // Mevcut bir hayvanı güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.updateAnimal(id, animalRequest));
    }

    // Belirli bir hayvanı silmek için endpoint
    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        return animalService.deleteAnimal(id);
    }
}
