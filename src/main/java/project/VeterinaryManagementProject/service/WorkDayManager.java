package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.entity.Doctor;
import project.VeterinaryManagementProject.entity.WorkDay;
import project.VeterinaryManagementProject.exception.EntityDuplicateException;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.repository.WorkDayRepository;
import project.VeterinaryManagementProject.dto.request.WorkDayRequest;
import project.VeterinaryManagementProject.dto.response.WorkDayResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkDayManager {

    private final WorkDayRepository workDayRepository;
    private final ModelMapper modelMapper;
    private final DoctorManager doctorService;

    // Tüm çalışma günlerini sayfalı olarak getirir
    public Page<WorkDayResponse> findAllWorkDays(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return workDayRepository.findAll(pageable).map(workDay -> modelMapper.map(workDay, WorkDayResponse.class));
    }

    // Belirli bir çalışma gününü kimlik numarasına göre getirir
    public WorkDayResponse findWorkDayById(Long id) {
        return modelMapper.map(workDayRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, WorkDay.class))
                , WorkDayResponse.class);
    }

    // Doktor kimlik numarasına göre çalışma günlerini getirir
    public List<WorkDayResponse> findWorkDaysByDoctorId(Long doctorId) {
        return workDayRepository.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(workDay -> modelMapper.map(workDay, WorkDayResponse.class))
                .collect(Collectors.toList());
    }

    // Yeni bir çalışma günü oluşturur
    public WorkDayResponse createWorkDay(WorkDayRequest workDayRequest) {
        Doctor doctorFromDb = doctorService.findDoctor(workDayRequest.getDoctorId());

        Optional<WorkDay> existWorkDayWithSameSpecs
                = workDayRepository.findByWorkDayAndDoctor_DoctorId(workDayRequest.getWorkDay(), workDayRequest.getDoctorId());

        if (existWorkDayWithSameSpecs.isPresent()) {
            throw new EntityDuplicateException(WorkDay.class);
        }

        WorkDay newWorkDay = modelMapper.map(workDayRequest, WorkDay.class);
        newWorkDay.setDoctor(doctorFromDb);
        return modelMapper.map(workDayRepository.save(newWorkDay), WorkDayResponse.class);
    }

    // Var olan bir çalışma gününü günceller
    public WorkDayResponse updateWorkDay(Long id, WorkDayRequest workDayRequest) {
        Optional<WorkDay> workDayFromDb = workDayRepository.findById(id);
        Optional<WorkDay> existOtherWorkDayFromRequest
                = workDayRepository.findByWorkDayAndDoctor_DoctorId(workDayRequest.getWorkDay(), workDayRequest.getDoctorId());

        if (workDayFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, WorkDay.class);
        }

        if (existOtherWorkDayFromRequest.isPresent() && !existOtherWorkDayFromRequest.get().getId().equals(id)) {
            throw new EntityDuplicateException(WorkDay.class);
        }

        WorkDay updatedWorkDay = workDayFromDb.get();
        updatedWorkDay.setWorkDay(workDayRequest.getWorkDay());
        Doctor doctorFromDb = doctorService.findDoctor(workDayRequest.getDoctorId());
        updatedWorkDay.setDoctor(doctorFromDb);
        return modelMapper.map(workDayRepository.save(updatedWorkDay), WorkDayResponse.class);
    }

    // Belirli bir çalışma gününü siler
    public String deleteWorkDay(Long id) {
        Optional<WorkDay> workDayFromDb = workDayRepository.findById(id);

        if (workDayFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, WorkDay.class);
        } else {
            workDayRepository.delete(workDayFromDb.get());
            return "Work day deleted.";
        }
    }

    // Doktor kimlik numarası ve tarihe göre çalışma gününü getirir
    public Optional<WorkDay> findByDoctorIdAndDate(Long id, LocalDate workDay) {
        Optional<WorkDay> workDayOptional = workDayRepository.findByWorkDayAndDoctor_DoctorId(workDay, id);
        if (workDayOptional.isEmpty()) {
            // Hata detaylı olarak loglanır
            System.out.println("Work day not found for doctor ID: " + id + " on date: " + workDay);
        }
        return workDayOptional;
    }
}
