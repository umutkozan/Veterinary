package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.WorkDayRequest;
import project.VeterinaryManagementProject.dto.response.WorkDayResponse;
import project.VeterinaryManagementProject.service.WorkDayManager;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workdays")
@RequiredArgsConstructor
public class WorkDayController {

    // WorkDayManager sınıfı, çalışma günleri ile ilgili iş mantığını içerir
    private final WorkDayManager workDayService;

    // Tüm çalışma günlerini sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<WorkDayResponse>> findAllWorkDays(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(workDayService.findAllWorkDays(pageNumber, pageSize));
    }

    // Belirli bir çalışma gününü kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<WorkDayResponse> findWorkDayById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(workDayService.findWorkDayById(id));
    }

    // Belirli bir doktorun çalışma günlerini listelemek için endpoint
    @GetMapping("/byDoctor/{doctorId}")
    public ResponseEntity<List<WorkDayResponse>> findWorkDaysByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.status(HttpStatus.OK).body(workDayService.findWorkDaysByDoctorId(doctorId));
    }

    // Yeni bir çalışma günü oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<WorkDayResponse> createWorkDay(@RequestBody WorkDayRequest workDayRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workDayService.createWorkDay(workDayRequest));
    }

    // Mevcut bir çalışma gününü güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<WorkDayResponse> updateWorkDay(@PathVariable Long id, @RequestBody WorkDayRequest workDayRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(workDayService.updateWorkDay(id, workDayRequest));
    }

    // Belirli bir çalışma gününü silmek için endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkDay(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(workDayService.deleteWorkDay(id));
    }
}
