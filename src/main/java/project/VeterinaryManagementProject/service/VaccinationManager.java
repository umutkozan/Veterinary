package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.dto.request.VaccinationRequest;
import project.VeterinaryManagementProject.dto.response.ReportResponse;
import project.VeterinaryManagementProject.entity.Report;
import project.VeterinaryManagementProject.entity.Vaccination;
import project.VeterinaryManagementProject.exception.DuplicateEntryException;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.exception.ProtectionValidityException;
import project.VeterinaryManagementProject.dto.response.VaccinationResponse;
import project.VeterinaryManagementProject.repository.VaccinationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccinationManager {

    private final VaccinationRepository vaccinationRepository;
    private final ReportManager reportService;
    private final ModelMapper modelMapper;

    // Tüm aşıları sayfalı olarak getirir
    public Page<VaccinationResponse> findAllVaccinations(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vaccinationRepository.findAll(pageable).map(vaccination -> modelMapper.map(vaccination, VaccinationResponse.class));
    }

    // Belirli bir aşıyı kimlik numarasına göre getirir
    public VaccinationResponse findVaccinationById(Long id) {
        return modelMapper.map(vaccinationRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Vaccination.class)), VaccinationResponse.class);
    }

    // Aşının bitiş tarih aralığına göre hayvanları arar ve sayfalı olarak getirir
    public Page<VaccinationResponse> findAnimalsByVaccinationProtectionFinishDateRange(LocalDate startDate, LocalDate endDate, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vaccinationRepository.findByProtectionFinishDateBetween(startDate, endDate, pageable).map(vaccination -> modelMapper.map(vaccination, VaccinationResponse.class));
    }

    // Hayvan ismine göre aşıları arar ve sayfalı olarak getirir
    public Page<VaccinationResponse> findVaccinationsByAnimal(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vaccinationRepository.findByAnimalName(name, pageable).map(vaccination -> modelMapper.map(vaccination, VaccinationResponse.class));
    }

    // Aşı ismine göre aşıları arar ve sayfalı olarak getirir
    public Page<VaccinationResponse> findVaccinationsByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vaccinationRepository.findByName(name, pageable).map(vaccination -> modelMapper.map(vaccination, VaccinationResponse.class));
    }

    // Tarih aralığına göre aşıları arar ve sayfalı olarak getirir
    public Page<VaccinationResponse> findVaccinationsByDate(LocalDate startDate, LocalDate endDate, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return vaccinationRepository.findByDate(startDate, endDate, pageable).map(vaccination -> modelMapper.map(vaccination, VaccinationResponse.class));
    }

    // Yeni bir aşı oluşturur
    public VaccinationResponse createVaccination(VaccinationRequest vaccinationRequest) {
        List<Vaccination> existValidVaccinationWithSameSpecsAnd =
                vaccinationRepository.findByNameAndCodeAndAnimal_AnimalIdAndProtectionFinishDateGreaterThanEqual(vaccinationRequest.getName(), vaccinationRequest.getCode(), vaccinationRequest.getAnimalWithoutCustomer().getId(), vaccinationRequest.getProtectionStartDate());

        if (!existValidVaccinationWithSameSpecsAnd.isEmpty()) {
            throw new ProtectionValidityException("The vaccine you want to save is still protective for this animal.");
        }

        Vaccination newVaccination = modelMapper.map(vaccinationRequest, Vaccination.class);
        ReportResponse reportResponse = reportService.findReportById(vaccinationRequest.getReport().getId());
        newVaccination.setReport(modelMapper.map(reportResponse, Report.class));
        return modelMapper.map(vaccinationRepository.save(newVaccination), VaccinationResponse.class);
    }

    // Var olan bir aşıyı günceller
    public VaccinationResponse updateVaccination(Long id, VaccinationRequest vaccinationRequest) {
        Optional<Vaccination> vaccinationFromDb = vaccinationRepository.findById(id);
        List<Vaccination> existOtherValidVaccinationFromRequest =
                vaccinationRepository.findByNameAndCodeAndAnimal_AnimalIdAndProtectionFinishDateGreaterThanEqual(vaccinationRequest.getName(), vaccinationRequest.getCode(), vaccinationRequest.getAnimalWithoutCustomer().getId(), vaccinationRequest.getProtectionStartDate());

        if (vaccinationFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Vaccination.class);
        }

        if (!existOtherValidVaccinationFromRequest.isEmpty() && !existOtherValidVaccinationFromRequest.get(existOtherValidVaccinationFromRequest.size() - 1).getId().equals(id)) {
            throw new DuplicateEntryException(Vaccination.class);
        }

        if (!existOtherValidVaccinationFromRequest.isEmpty()) {
            throw new ProtectionValidityException("The vaccine you want to update is still protective for this animal.");
        }

        Vaccination updatedVaccination = vaccinationFromDb.get();
        modelMapper.map(vaccinationRequest, updatedVaccination);
        return modelMapper.map(vaccinationRepository.save(updatedVaccination), VaccinationResponse.class);
    }

    // Belirli bir aşıyı siler
    public String deleteVaccination(Long id) {
        Optional<Vaccination> vaccinationFromDb = vaccinationRepository.findById(id);
        if (vaccinationFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Vaccination.class);
        } else {
            vaccinationRepository.delete(vaccinationFromDb.get());
            return "Vaccine deleted.";
        }
    }

    // Vaccination entity'sini VaccinationResponse DTO'suna dönüştürür
    public VaccinationResponse vaccineResponseDtoFromVaccine(Vaccination vaccination) {
        return modelMapper.map(vaccination, VaccinationResponse.class);
    }
}
