package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.ReportRequest;
import project.VeterinaryManagementProject.dto.response.ReportResponse;
import project.VeterinaryManagementProject.service.ReportManager;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
class ReportController {

    // ReportManager sınıfı, rapor yönetimi ile ilgili iş mantığını içerir
    private final ReportManager reportService;

    // Tüm raporları sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<ReportResponse>> findAllReports (
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findAllReports(pageNumber,pageSize));
    }

    // Belirli bir raporu kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> findReportById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findReportById(id));
    }

    // Yeni bir rapor oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<ReportResponse> createReport (@RequestBody ReportRequest reportRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.createReport(reportRequest));
    }

    // Mevcut bir raporu güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport (@PathVariable Long id, @RequestBody ReportRequest reportRequest){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReport(id, reportRequest));
    }

    // Belirli bir raporu silmek için endpoint
    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id){
        return reportService.deleteReport(id);
    }

}
