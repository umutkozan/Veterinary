package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.dto.request.AnimalRequest;
import project.VeterinaryManagementProject.dto.response.AnimalResponse;
import project.VeterinaryManagementProject.entity.Animal;
import project.VeterinaryManagementProject.entity.Customer;
import project.VeterinaryManagementProject.exception.EntityDuplicateException;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.repository.AnimalRepository;
import project.VeterinaryManagementProject.repository.CustomerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalManager {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    // Tüm hayvanları sayfalı olarak getirir
    public Page<AnimalResponse> findAllAnimals(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return animalRepository.findAll(pageable)
                .map(animal -> modelMapper.map(animal, AnimalResponse.class));
    }

    // Belirli bir hayvanı kimlik numarasına göre getirir
    public AnimalResponse findAnimalById(Long id) {
        return modelMapper.map(animalRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException(id, Animal.class)), AnimalResponse.class);
    }

    // Hayvanları isme göre arar ve sayfalı olarak getirir
    public Page<AnimalResponse> findAnimalsByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return animalRepository.findByAnimalNameContaining(name, pageable)
                .map(animal -> modelMapper.map(animal, AnimalResponse.class));
    }

    // Müşteri adına göre hayvanları arar ve sayfalı olarak getirir
    public Page<AnimalResponse> findAnimalsByCustomerName(String customerName, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return animalRepository.findByCustomer_CustomerNameContaining(customerName, pageable)
                .map(animal -> modelMapper.map(animal, AnimalResponse.class));
    }

    // Yeni bir hayvan oluşturur
    public AnimalResponse createAnimal(AnimalRequest animalRequest) {
        Optional<Customer> customer = customerRepository.findById(animalRequest.getCustomerId());
        if (customer.isEmpty()) {
            throw new EntityDoesNotExistException(animalRequest.getCustomerId(), Customer.class);
        }

        Optional<Animal> existAnimalWithSameSpecs = animalRepository.findByAnimalNameAndAnimalSpeciesAndAnimalGenderAndAnimalDateOfBirth(
                animalRequest.getName(), animalRequest.getSpecies(), animalRequest.getGender(), animalRequest.getDateOfBirth());

        if (existAnimalWithSameSpecs.isPresent()) {
            throw new EntityDuplicateException(Animal.class);
        }

        Animal newAnimal = modelMapper.map(animalRequest, Animal.class);
        newAnimal.setCustomer(customer.get());

        return modelMapper.map(animalRepository.save(newAnimal), AnimalResponse.class);
    }

    // Var olan bir hayvanı günceller
    public AnimalResponse updateAnimal(Long id, AnimalRequest animalRequest) {
        Animal animalFromDb = animalRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException(id, Animal.class));

        Customer customer = customerRepository.findById(animalRequest.getCustomerId())
                .orElseThrow(() -> new EntityDoesNotExistException(animalRequest.getCustomerId(), Customer.class));

        animalFromDb.setAnimalName(animalRequest.getName());
        animalFromDb.setAnimalSpecies(animalRequest.getSpecies());
        animalFromDb.setAnimalBreed(animalRequest.getBreed());
        animalFromDb.setAnimalGender(animalRequest.getGender());
        animalFromDb.setAnimalColour(animalRequest.getColour());
        animalFromDb.setAnimalDateOfBirth(animalRequest.getDateOfBirth());
        animalFromDb.setCustomer(customer);

        return modelMapper.map(animalRepository.save(animalFromDb), AnimalResponse.class);
    }

    // Belirli bir hayvanı siler
    public String deleteAnimal(Long id) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isEmpty()) {
            throw new EntityDoesNotExistException(id, Animal.class);
        } else {
            animalRepository.delete(animalFromDb.get());
            return "Animal deleted.";
        }
    }
}
