package main.Repo;

import main.Model.Customer;

import java.util.List;

public interface CustomerRepo {
    void saveCustomer(Customer customer);
    void updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomer();

}
