package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.entity.Doctor;
import project.VeterinaryManagementProject.exception.DuplicateEntryException;
import project.VeterinaryManagementProject.exception.EntityDuplicateException;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.dto.request.DoctorRequest;
import project.VeterinaryManagementProject.dto.response.DoctorResponse;
import project.VeterinaryManagementProject.repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorManager {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    // Tüm doktorları sayfalı olarak getirir
    public Page<DoctorResponse> findAllDoctors(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return doctorRepository.findAll(pageable).map(doctor -> modelMapper.map(doctor, DoctorResponse.class));
    }

    // Belirli bir doktoru kimlik numarasına göre getirir
    public DoctorResponse findDoctorById(Long id) {
        return modelMapper.map(doctorRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Doctor.class)), DoctorResponse.class);
    }

    // Doktorları isme göre arar ve sayfalı olarak getirir
    public Page<DoctorResponse> findDoctorByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return doctorRepository.findByDoctorNameContaining(name, pageable).map(doctor -> modelMapper.map(doctor, DoctorResponse.class));
    }

    // Belirli bir doktoru kimlik numarasına göre bulur
    public Doctor findDoctor(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Doctor.class));
    }

    // Yeni bir doktor oluşturur
    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {
        boolean doctorExists = doctorRepository.existsByDoctorEmail(doctorRequest.getEmail());
        if (doctorExists) {
            throw new EntityDuplicateException(Doctor.class);
        }

        Doctor newDoctor = modelMapper.map(doctorRequest, Doctor.class);
        return modelMapper.map(doctorRepository.save(newDoctor), DoctorResponse.class);
    }

    // Var olan bir doktoru günceller
    public DoctorResponse updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Doctor.class));
        boolean existsOtherDoctorWithSameEmail = doctorRepository.existsByDoctorEmail(doctorRequest.getEmail());

        if (existsOtherDoctorWithSameEmail && !existingDoctor.getDoctorId().equals(id)) {
            throw new DuplicateEntryException(Doctor.class);
        }

        modelMapper.map(doctorRequest, existingDoctor);
        return modelMapper.map(doctorRepository.save(existingDoctor), DoctorResponse.class);
    }

    // Belirli bir doktoru siler
    public String deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Doctor.class));
        doctorRepository.delete(doctor);
        return "Doctor deleted.";
    }
}
