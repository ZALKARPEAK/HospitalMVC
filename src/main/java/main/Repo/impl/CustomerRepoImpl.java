package main.Repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import main.Model.Agency;
import main.Model.Customer;
import main.Repo.CustomerRepo;
import main.Service.AgencyService;
import main.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerRepoImpl implements CustomerRepo {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveCustomer(Customer customer) {
        LocalDate birthDate = customer.getDateOfBirth();
        if (birthDate == null) {
            throw new IllegalArgumentException("Дата рождения не установлена");
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 18) {
            throw new IllegalArgumentException("Вы несовершенно летний по закону КР");
        }

        entityManager.persist(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(getCustomerById(id));
        if (optionalCustomer.isPresent()) {
            Customer customer1 = optionalCustomer.get();
            customer1.setName(customer.getName());
            customer1.setSurname(customer.getSurname());
            customer1.setEmail(customer.getEmail());
            customer1.setGender(customer.getGender());
            customer1.setPhoneNumber(customer.getPhoneNumber());
            customer1.setDateOfBirth(customer.getDateOfBirth());
            entityManager.merge(customer1);
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
            customer.getAgency().getCustomers().remove(customer);
        entityManager.remove(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).
                getResultList();
    }




}