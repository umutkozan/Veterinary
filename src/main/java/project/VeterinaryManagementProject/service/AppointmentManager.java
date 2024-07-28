package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.dto.request.AppointmentRequest;
import project.VeterinaryManagementProject.dto.response.AppointmentResponse;
import project.VeterinaryManagementProject.entity.Animal;
import project.VeterinaryManagementProject.entity.Appointment;
import project.VeterinaryManagementProject.entity.Doctor;
import project.VeterinaryManagementProject.entity.WorkDay;
import project.VeterinaryManagementProject.exception.DoctorAppointmentClashException;
import project.VeterinaryManagementProject.exception.DoctorBusyException;
import project.VeterinaryManagementProject.exception.EntityDuplicateException;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.repository.AnimalRepository;
import project.VeterinaryManagementProject.repository.AppointmentRepository;
import project.VeterinaryManagementProject.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager {

    private final AppointmentRepository appointmentRepository;
    private final WorkDayManager workDayService;
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    // Tüm randevuları sayfalı olarak getirir
    public Page<AppointmentResponse> findAllAppointments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return appointmentRepository.findAll(pageable).map(appointment -> modelMapper.map(appointment, AppointmentResponse.class));
    }

    // Kimlik numarasına göre randevu getirir
    public AppointmentResponse findAppointmentByIdResponse(Long id) {
        return modelMapper.map(appointmentRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Appointment.class))
                , AppointmentResponse.class);
    }

    // Belirli bir randevuyu kimlik numarasına göre getirir
    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Appointment.class));
    }

    // Doktor kimlik numarası ve tarih aralığına göre randevuları arar ve sayfalı olarak getirir
    public Page<AppointmentResponse> findAppointmentByDoctorIdAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (doctorId == null) {
            return appointmentRepository.findByAppointmentDateBetween(startDate, endDate, pageable).map(appointment -> modelMapper.map(appointment, AppointmentResponse.class));
        }
        return appointmentRepository.findByDoctor_DoctorIdAndAppointmentDateBetween(doctorId, startDate, endDate, pageable).map(appointment -> modelMapper.map(appointment, AppointmentResponse.class));
    }

    // Hayvan kimlik numarası ve tarih aralığına göre randevuları arar ve sayfalı olarak getirir
    public Page<AppointmentResponse> findAppointmentByAnimalIdAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (animalId == null) {
            return appointmentRepository.findByAppointmentDateBetween(startDate, endDate, pageable).map(appointment -> modelMapper.map(appointment, AppointmentResponse.class));
        }
        return appointmentRepository.findByAnimal_AnimalIdAndAppointmentDateBetween(animalId, startDate, endDate, pageable).map(appointment -> modelMapper.map(appointment, AppointmentResponse.class));
    }

    // Yeni bir randevu oluşturur
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        Optional<Appointment> existAppointmentWithSameSpecs = appointmentRepository.findByAppointmentDateAndDoctorDoctorIdAndAnimalAnimalId(appointmentRequest.getAppointmentDate(), appointmentRequest.getDoctorId(), appointmentRequest.getAnimalId());
        Optional<WorkDay> existsWorkDayByDoctorIdAndDate = workDayService.findByDoctorIdAndDate(appointmentRequest.getDoctorId(), appointmentRequest.getAppointmentDate().toLocalDate());
        Optional<Appointment> existAppointmentWithDateAndDoctorId = appointmentRepository.findByAppointmentDateAndDoctorDoctorId(appointmentRequest.getAppointmentDate(), appointmentRequest.getDoctorId());

        if (existAppointmentWithSameSpecs.isPresent()) {
            throw new EntityDuplicateException(Appointment.class);
        }

        if (existsWorkDayByDoctorIdAndDate.isEmpty()) {
            throw new DoctorBusyException(appointmentRequest.getAppointmentDate().toLocalDate());
        }

        if (existAppointmentWithDateAndDoctorId.isPresent()) {
            throw new DoctorAppointmentClashException(appointmentRequest.getAppointmentDate().toLocalDate());
        }

        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(() -> new EntityDoesNotExistException(appointmentRequest.getDoctorId(), Doctor.class));
        Animal animal = animalRepository.findById(appointmentRequest.getAnimalId()).orElseThrow(() -> new EntityDoesNotExistException(appointmentRequest.getAnimalId(), Animal.class));

        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        newAppointment.setDoctor(doctor);
        newAppointment.setAnimal(animal);

        return modelMapper.map(appointmentRepository.save(newAppointment), AppointmentResponse.class);
    }

    // Var olan bir randevuyu günceller
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);
        if (appointmentFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Appointment.class);
        }

        Optional<Appointment> existAppointmentWithSameSpecs = appointmentRepository.findByAppointmentDateAndDoctorDoctorIdAndAnimalAnimalId(appointmentRequest.getAppointmentDate(), appointmentRequest.getDoctorId(), appointmentRequest.getAnimalId());
        if (existAppointmentWithSameSpecs.isPresent()) {
            throw new EntityDuplicateException(Appointment.class);
        }

        Optional<WorkDay> existsWorkDayByDoctorIdAndDate = workDayService.findByDoctorIdAndDate(appointmentRequest.getDoctorId(), appointmentRequest.getAppointmentDate().toLocalDate());
        if (existsWorkDayByDoctorIdAndDate.isEmpty()) {
            throw new DoctorBusyException(appointmentRequest.getAppointmentDate().toLocalDate());
        }

        Optional<Appointment> existAppointmentWithDateAndDoctorId = appointmentRepository.findByAppointmentDateAndDoctorDoctorId(appointmentRequest.getAppointmentDate(), appointmentRequest.getDoctorId());
        if (existAppointmentWithDateAndDoctorId.isPresent()) {
            throw new DoctorAppointmentClashException(appointmentRequest.getAppointmentDate().toLocalDate());
        }

        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(() -> new EntityDoesNotExistException(appointmentRequest.getDoctorId(), Doctor.class));
        Animal animal = animalRepository.findById(appointmentRequest.getAnimalId()).orElseThrow(() -> new EntityDoesNotExistException(appointmentRequest.getAnimalId(), Animal.class));

        Appointment updatedAppointment = appointmentFromDb.get();
        updatedAppointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        updatedAppointment.setDoctor(doctor);
        updatedAppointment.setAnimal(animal);

        return modelMapper.map(appointmentRepository.save(updatedAppointment), AppointmentResponse.class);
    }

    // Belirli bir randevuyu siler
    public String deleteAppointment(Long id) {
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);
        if (appointmentFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Appointment.class);
        } else {
            appointmentRepository.delete(appointmentFromDb.get());
            return "Appointment deleted.";
        }
    }
}
