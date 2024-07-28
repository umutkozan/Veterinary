package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.VaccinationRequest;
import project.VeterinaryManagementProject.dto.response.VaccinationResponse;
import project.VeterinaryManagementProject.service.VaccinationManager;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {

    // VaccinationManager sınıfı, aşı yönetimi ile ilgili iş mantığını içerir
    private final VaccinationManager vaccinationService;

    // Tüm aşıları sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<VaccinationResponse>> findAllVaccinations(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.findAllVaccinations(pageNumber, pageSize));
    }

    // Belirli bir aşıyı kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<VaccinationResponse> findVaccinationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(vaccinationService.findVaccinationById(id));
    }

    // Belirli bir tarih aralığında biten aşı koruma tarihleriyle hayvanları bulmak için endpoint
    @GetMapping("/searchByVaccinationRange")
    public ResponseEntity<Page<VaccinationResponse>> findAnimalsByVaccinationProtectionFinishDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.findAnimalsByVaccinationProtectionFinishDateRange(startDate, endDate, pageNumber, pageSize));
    }

    // Hayvan adına göre aşıları bulmak için endpoint
    @GetMapping("/searchByAnimal")
    public ResponseEntity<Page<VaccinationResponse>> findVaccinationsByAnimal(
            @RequestParam String name,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.findVaccinationsByAnimal(name, pageNumber, pageSize));
    }

    // Aşı adına göre aşıları bulmak için endpoint
    @GetMapping("/searchByName")
    public ResponseEntity<Page<VaccinationResponse>> findVaccinationsByName(
            @RequestParam String name,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.findVaccinationsByName(name, pageNumber, pageSize));
    }

    // Belirli bir tarih aralığında aşıları bulmak için endpoint
    @GetMapping("/searchByDate")
    public ResponseEntity<Page<VaccinationResponse>> findVaccinationsByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.findVaccinationsByDate(startDate, endDate, pageNumber, pageSize));
    }

    // Yeni bir aşı oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<VaccinationResponse> createVaccination(@RequestBody VaccinationRequest vaccinationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vaccinationService.createVaccination(vaccinationRequest));
    }

    // Mevcut bir aşıyı güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<VaccinationResponse> updateVaccination(@PathVariable Long id, @RequestBody VaccinationRequest vaccinationRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.updateVaccination(id, vaccinationRequest));
    }

    // Belirli bir aşıyı silmek için endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccination(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(vaccinationService.deleteVaccination(id));
    }
}
