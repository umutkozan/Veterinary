package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.AppointmentRequest;
import project.VeterinaryManagementProject.dto.response.AppointmentResponse;
import project.VeterinaryManagementProject.service.AppointmentManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    // AppointmentManager sınıfı, randevu yönetimi ile ilgili iş mantığını içerir
    private final AppointmentManager appointmentService;

    // ISO_DATE_TIME formatında tarih ve saat için formatter
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    // Tüm randevuları sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<AppointmentResponse>> findAllAppointments(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findAllAppointments(pageNumber, pageSize));
    }

    // Belirli bir randevuyu kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> findAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(appointmentService.findAppointmentByIdResponse(id));
    }

    // Belirli bir doktora ait ve belirli bir tarih aralığında olan randevuları bulmak için endpoint
    @GetMapping("/searchByDoctorAndDateRange")
    public ResponseEntity<Page<AppointmentResponse>> findAppointmentByDoctorIdAndDateRange(
            @RequestParam(value = "doctorId", required = false) Long doctorId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findAppointmentByDoctorIdAndDateRange(doctorId, start, end, pageNumber, pageSize));
    }

    // Belirli bir hayvana ait ve belirli bir tarih aralığında olan randevuları bulmak için endpoint
    @GetMapping("/searchByAnimalAndDateRange")
    public ResponseEntity<Page<AppointmentResponse>> findAppointmentByAnimalIdAndDateRange(
            @RequestParam(value = "animalId", required = false) Long animalId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        return ResponseEntity.ok().body(appointmentService.findAppointmentByAnimalIdAndDateRange(animalId, start, end, pageNumber, pageSize));
    }

    // Yeni bir randevu oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointmentRequest));
    }

    // Mevcut bir randevuyu güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.updateAppointment(id, appointmentRequest));
    }

    // Belirli bir randevuyu silmek için endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.deleteAppointment(id));
    }
}
