package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.DoctorRequest;
import project.VeterinaryManagementProject.dto.response.DoctorResponse;
import project.VeterinaryManagementProject.service.DoctorManager;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    // DoctorManager sınıfı, doktor yönetimi ile ilgili iş mantığını içerir
    public final DoctorManager doctorService;

    // Tüm doktorları sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<DoctorResponse>> findAllDoctors(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findAllDoctors(pageNumber, pageSize));
    }

    // Belirli bir doktoru kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> findDoctorById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findDoctorById(id));
    }

    // Belirli bir isme sahip doktorları bulmak için endpoint
    @GetMapping("/searchByName")
    public ResponseEntity<Page<DoctorResponse>> findDoctorsByName(
            @RequestParam String name,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findDoctorByName(name, pageNumber, pageSize));
    }

    // Yeni bir doktor oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody DoctorRequest doctorRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.createDoctor(doctorRequest));
    }

    // Mevcut bir doktoru güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequest doctorRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(id, doctorRequest));
    }

    // Belirli bir doktoru silmek için endpoint
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }

}
