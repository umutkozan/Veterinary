package project.VeterinaryManagementProject.core.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.VeterinaryManagementProject.dto.request.*;
import project.VeterinaryManagementProject.dto.response.*;
import project.VeterinaryManagementProject.entity.*;

@Configuration
public class ModelMapperConfig {

    // ModelMapper bean tanımlaması yapar. Bu bean, DTO ve entity dönüşümleri için kullanılır.
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Customer (Müşteri) entity ve DTO dönüşümleri
        modelMapper.typeMap(CustomerRequest.class, Customer.class).addMappings(mapper -> {
            mapper.map(CustomerRequest::getCustomerName, Customer::setCustomerName);
            mapper.map(CustomerRequest::getCustomerPhone, Customer::setCustomerPhone);
            mapper.map(CustomerRequest::getCustomerEmail, Customer::setCustomerEmail);
            mapper.map(CustomerRequest::getCustomerAddress, Customer::setCustomerAddress);
            mapper.map(CustomerRequest::getCustomerCity, Customer::setCustomerCity);
        });

        modelMapper.typeMap(Customer.class, CustomerResponse.class).addMappings(mapper -> {
            mapper.map(Customer::getCustomerId, CustomerResponse::setId);
            mapper.map(Customer::getCustomerName, CustomerResponse::setName);
            mapper.map(Customer::getCustomerPhone, CustomerResponse::setPhone);
            mapper.map(Customer::getCustomerEmail, CustomerResponse::setEmail);
            mapper.map(Customer::getCustomerAddress, CustomerResponse::setAddress);
            mapper.map(Customer::getCustomerCity, CustomerResponse::setCity);
        });

        // Doctor (Doktor) entity ve DTO dönüşümleri
        modelMapper.typeMap(DoctorRequest.class, Doctor.class).addMappings(mapper -> {
            mapper.map(DoctorRequest::getName, Doctor::setDoctorName);
            mapper.map(DoctorRequest::getPhone, Doctor::setDoctorPhone);
            mapper.map(DoctorRequest::getEmail, Doctor::setDoctorEmail);
            mapper.map(DoctorRequest::getAddress, Doctor::setDoctorAddress);
            mapper.map(DoctorRequest::getCity, Doctor::setDoctorCity);
        });

        modelMapper.typeMap(Doctor.class, DoctorResponse.class).addMappings(mapper -> {
            mapper.map(Doctor::getDoctorId, DoctorResponse::setId);
            mapper.map(Doctor::getDoctorName, DoctorResponse::setName);
            mapper.map(Doctor::getDoctorPhone, DoctorResponse::setPhone);
            mapper.map(Doctor::getDoctorEmail, DoctorResponse::setEmail);
            mapper.map(Doctor::getDoctorAddress, DoctorResponse::setAddress);
            mapper.map(Doctor::getDoctorCity, DoctorResponse::setCity);
        });

        // WorkDay (Çalışma Günü) entity ve DTO dönüşümleri
        modelMapper.typeMap(WorkDayRequest.class, WorkDay.class).addMappings(mapper -> {
            mapper.map(WorkDayRequest::getWorkDay, WorkDay::setWorkDay);
            mapper.map(WorkDayRequest::getDoctorId, (dest, value) -> dest.setDoctor(new Doctor()));
        });

        modelMapper.typeMap(WorkDay.class, WorkDayResponse.class).addMappings(mapper -> {
            mapper.map(WorkDay::getId, WorkDayResponse::setId);
            mapper.map(WorkDay::getWorkDay, WorkDayResponse::setWorkDay);
            mapper.map(WorkDay::getDoctor, WorkDayResponse::setDoctor);
        });

        // Animal (Hayvan) entity ve DTO dönüşümleri
        modelMapper.typeMap(AnimalRequest.class, Animal.class).addMappings(mapper -> {
            mapper.map(AnimalRequest::getName, Animal::setAnimalName);
            mapper.map(AnimalRequest::getSpecies, Animal::setAnimalSpecies);
            mapper.map(AnimalRequest::getBreed, Animal::setAnimalBreed);
            mapper.map(AnimalRequest::getGender, Animal::setAnimalGender);
            mapper.map(AnimalRequest::getColour, Animal::setAnimalColour);
            mapper.map(AnimalRequest::getDateOfBirth, Animal::setAnimalDateOfBirth);
            mapper.map(AnimalRequest::getCustomerId, (dest, value) -> dest.setCustomer(new Customer()));
        });

        modelMapper.typeMap(Animal.class, AnimalResponse.class).addMappings(mapper -> {
            mapper.map(Animal::getAnimalId, AnimalResponse::setId);
            mapper.map(Animal::getAnimalName, AnimalResponse::setName);
            mapper.map(Animal::getAnimalSpecies, AnimalResponse::setSpecies);
            mapper.map(Animal::getAnimalBreed, AnimalResponse::setBreed);
            mapper.map(Animal::getAnimalGender, AnimalResponse::setGender);
            mapper.map(Animal::getAnimalColour, AnimalResponse::setColour);
            mapper.map(Animal::getAnimalDateOfBirth, AnimalResponse::setDateOfBirth);
            mapper.map(Animal::getCustomer, AnimalResponse::setCustomer);
        });

        // Vaccination (Aşılama) entity ve DTO dönüşümleri
        modelMapper.typeMap(VaccinationRequest.class, Vaccination.class).addMappings(mapper -> {
            mapper.map(VaccinationRequest::getName, Vaccination::setName);
            mapper.map(VaccinationRequest::getCode, Vaccination::setCode);
            mapper.map(VaccinationRequest::getProtectionStartDate, Vaccination::setProtectionStartDate);
            mapper.map(VaccinationRequest::getProtectionFinishDate, Vaccination::setProtectionFinishDate);
            mapper.map(src -> src.getAnimalWithoutCustomer().getId(), (dest, value) -> dest.setAnimal(new Animal()));
            mapper.map(src -> src.getReport().getId(), (dest, value) -> dest.setReport(new Report()));
        });

        modelMapper.typeMap(Vaccination.class, VaccinationResponse.class).addMappings(mapper -> {
            mapper.map(Vaccination::getId, VaccinationResponse::setId);
            mapper.map(Vaccination::getName, VaccinationResponse::setName);
            mapper.map(Vaccination::getCode, VaccinationResponse::setCode);
            mapper.map(Vaccination::getProtectionStartDate, VaccinationResponse::setProtectionStartDate);
            mapper.map(Vaccination::getProtectionFinishDate, VaccinationResponse::setProtectionFinishDate);
            mapper.map(Vaccination::getAnimal, VaccinationResponse::setAnimal);
            mapper.map(Vaccination::getReport, VaccinationResponse::setReport);
        });

        // Report (Rapor) entity ve DTO dönüşümleri
        modelMapper.typeMap(ReportRequest.class, Report.class).addMappings(mapper -> {
            mapper.map(ReportRequest::getTitle, Report::setTitle);
            mapper.map(ReportRequest::getDiagnosis, Report::setDiagnosis);
            mapper.map(ReportRequest::getPrice, Report::setPrice);
            mapper.map(src -> src.getAppointment().getId(), (dest, value) -> dest.setAppointment(new Appointment()));
        });

        modelMapper.typeMap(Report.class, ReportResponse.class).addMappings(mapper -> {
            mapper.map(Report::getId, ReportResponse::setId);
            mapper.map(Report::getTitle, ReportResponse::setTitle);
            mapper.map(Report::getDiagnosis, ReportResponse::setDiagnosis);
            mapper.map(Report::getPrice, ReportResponse::setPrice);
            mapper.map(Report::getAppointment, ReportResponse::setAppointment);
        });

        // Appointment (Randevu) entity ve DTO dönüşümleri
        modelMapper.typeMap(Appointment.class, AppointmentResponse.class).addMappings(mapper -> {
            mapper.map(Appointment::getId, AppointmentResponse::setId);
            mapper.map(Appointment::getAppointmentDate, AppointmentResponse::setAppointmentDate);
            mapper.map(Appointment::getDoctor, AppointmentResponse::setDoctor);
            mapper.map(Appointment::getAnimal, AppointmentResponse::setAnimal);
        });

        return modelMapper;
    }
}
