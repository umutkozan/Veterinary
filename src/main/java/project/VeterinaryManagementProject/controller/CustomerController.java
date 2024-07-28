package project.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.VeterinaryManagementProject.dto.request.CustomerRequest;
import project.VeterinaryManagementProject.dto.response.CustomerResponse;
import project.VeterinaryManagementProject.service.CustomerManager;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    // CustomerManager sınıfı, müşteri yönetimi ile ilgili iş mantığını içerir
    private final CustomerManager customerService;

    // Tüm müşterileri sayfalama ile listelemek için endpoint
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> findAllCustomers(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers(pageNumber, pageSize));
    }

    // Belirli bir müşteriyi kimliğine göre bulmak için endpoint
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerById(id));
    }

    // Belirli bir isme sahip müşterileri bulmak için endpoint
    @GetMapping("/searchByName")
    public ResponseEntity<Page<CustomerResponse>> findCustomersByName(
            @RequestParam String name,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomersByName(name, pageNumber, pageSize));
    }

    // Yeni bir müşteri oluşturmak için endpoint
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
    }

    // Mevcut bir müşteriyi güncellemek için endpoint
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerRequest));
    }

    // Belirli bir müşteriyi silmek için endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(id));
    }
}
