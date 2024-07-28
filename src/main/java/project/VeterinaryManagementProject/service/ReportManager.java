package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.dto.request.ReportRequest;
import project.VeterinaryManagementProject.dto.response.ReportResponse;
import project.VeterinaryManagementProject.entity.Appointment;
import project.VeterinaryManagementProject.entity.Report;
import project.VeterinaryManagementProject.exception.DuplicateEntryException;
import project.VeterinaryManagementProject.exception.EntityDuplicateException;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.repository.ReportRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportManager {

    private final ReportRepository reportRepository;
    private final AppointmentManager appointmentService;
    private final ModelMapper modelMapper;

    // Tüm raporları sayfalı olarak getirir
    public Page<ReportResponse> findAllReports(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reportRepository.findAll(pageable).map(this::reportResponseDtoFromReport);
    }

    // Belirli bir raporu kimlik numarasına göre getirir
    public ReportResponse findReportById(Long id) {
        return reportRepository.findById(id)
                .map(this::reportResponseDtoFromReport)
                .orElseThrow(() -> new EntityDoesNotExistException(id, Report.class));
    }

    // Yeni bir rapor oluşturur
    public ReportResponse createReport(ReportRequest reportRequest) {
        Optional<Report> existReportWithSameSpecs = reportRepository.findByAppointmentId(reportRequest.getAppointment().getId());

        if (existReportWithSameSpecs.isPresent()) {
            throw new EntityDuplicateException(Report.class);
        }

        Appointment appointmentFromDb = appointmentService.findAppointmentById(reportRequest.getAppointment().getId());
        reportRequest.getAppointment().setId(null);
        Report newReport = modelMapper.map(reportRequest, Report.class);
        newReport.setAppointment(appointmentFromDb);
        return reportResponseDtoFromReport(reportRepository.save(newReport));
    }

    // Var olan bir raporu günceller
    public ReportResponse updateReport(Long id, ReportRequest reportRequest) {
        Optional<Report> reportFromDb = reportRepository.findById(id);
        Optional<Report> existOtherReportFromRequest = reportRepository.findReportByTitleAndDiagnosisAndPriceAndAppointmentId(
                reportRequest.getTitle(), reportRequest.getDiagnosis(), reportRequest.getPrice(), reportRequest.getAppointment().getId());

        if (reportFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Report.class);
        }

        if (existOtherReportFromRequest.isPresent() && !existOtherReportFromRequest.get().getId().equals(id)) {
            throw new DuplicateEntryException(Report.class);
        }

        Appointment appointmentFromDb = appointmentService.findAppointmentById(reportRequest.getAppointment().getId());
        Report updatedReport = reportFromDb.get();
        updatedReport.setTitle(reportRequest.getTitle());
        updatedReport.setDiagnosis(reportRequest.getDiagnosis());
        updatedReport.setPrice(reportRequest.getPrice());
        updatedReport.setAppointment(appointmentFromDb);
        return reportResponseDtoFromReport(reportRepository.save(updatedReport));
    }

    // Belirli bir raporu siler
    public String deleteReport(Long id) {
        Optional<Report> reportFromDb = reportRepository.findById(id);
        if (reportFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Report.class);
        } else {
            reportRepository.delete(reportFromDb.get());
            return "Report deleted.";
        }
    }

    // Report entity'sini ReportResponse DTO'suna dönüştürür
    private ReportResponse reportResponseDtoFromReport(Report report) {
        ReportResponse reportResponse = modelMapper.map(report, ReportResponse.class);
        reportResponse.getAppointment().setCustomerName(report.getAppointment().getAnimal().getCustomer().getCustomerName());
        return reportResponse;
    }
}
