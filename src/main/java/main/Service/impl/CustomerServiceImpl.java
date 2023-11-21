package main.Service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.Model.Agency;
import main.Model.Customer;
import main.Repo.AgencyRepo;
import main.Repo.CustomerRepo;
import main.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AgencyRepo agencyRepo;
    @Override
    public void saveCustomer(Customer customer) {
        customerRepo.saveCustomer(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        customerRepo.updateCustomer(id, customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteCustomer(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomer(){
        return customerRepo.getAllCustomer();
    }

    @Transactional
    @Override
    public void assign(Long customerId, Long agencyId) {
        Agency agency = agencyRepo.getById(agencyId);
        Customer customer = customerRepo.getCustomerById(customerId);

        if (agency != null && customer != null) {
            if (!agency.getCustomers().contains(customer)) {
                agency.getCustomers().add(customer);
            }
            if (!customer.getAgency().contains(agency)) {
                customer.getAgency().add(agency);
            }
        }
    }
}
