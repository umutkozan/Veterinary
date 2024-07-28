package project.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.VeterinaryManagementProject.dto.request.CustomerRequest;
import project.VeterinaryManagementProject.dto.response.CustomerResponse;
import project.VeterinaryManagementProject.entity.Customer;
import project.VeterinaryManagementProject.exception.EntityDoesNotExistException;
import project.VeterinaryManagementProject.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerManager {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    // Tüm müşterileri sayfalı olarak getirir
    public Page<CustomerResponse> findAllCustomers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return customerRepository.findAll(pageable).map(customer -> modelMapper.map(customer, CustomerResponse.class));
    }

    // Belirli bir müşteriyi kimlik numarasına göre getirir
    public CustomerResponse findCustomerById(Long id) {
        return modelMapper.map(customerRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Customer.class)), CustomerResponse.class);
    }

    // Müşterileri isme göre arar ve sayfalı olarak getirir
    public Page<CustomerResponse> findCustomersByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return customerRepository.findByCustomerNameContaining(name, pageable).map(customer -> modelMapper.map(customer, CustomerResponse.class));
    }

    // Yeni bir müşteri oluşturur
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer newCustomer = modelMapper.map(customerRequest, Customer.class);
        return modelMapper.map(customerRepository.save(newCustomer), CustomerResponse.class);
    }

    // Var olan bir müşteriyi günceller
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Customer.class));
        modelMapper.map(customerRequest, existingCustomer);
        return modelMapper.map(customerRepository.save(existingCustomer), CustomerResponse.class);
    }

    // Belirli bir müşteriyi siler
    public String deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException(id, Customer.class));
        customerRepository.delete(customer);
        return "Customer deleted successfully";
    }
}
