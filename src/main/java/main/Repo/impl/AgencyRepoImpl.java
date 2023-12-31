package main.Repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import main.Model.Agency;
import main.Model.Customer;
import main.Model.House;
import main.Repo.AgencyRepo;
import main.Service.AgencyService;
import main.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public class AgencyRepoImpl implements AgencyRepo {
    @PersistenceContext
    private  EntityManager entityManager;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private CustomerService customerService;



    @Override
    public void saveAgency(Agency agency) {
        boolean x = getAllAgency().stream().anyMatch(agency1 -> agency1.getName().equals(agency.getName()));
        if (!x) {
            entityManager.merge(agency);
        }
        if (x) {
            System.out.println("Данное агенство не найдено");
        }
    }

    @Override
    public List<Agency> getAllAgency() {
        return entityManager.createQuery("SELECT a FROM Agency a", Agency.class).getResultList();
    }

    @Override
    public Agency getById(Long id) {
        return entityManager.find(Agency.class, id);
    }

    @Override
    public void updateAgencyById(Long id, Agency agency) {
        Optional<Agency> optionalAgency = Optional.ofNullable(getById(id));
        if (optionalAgency.isPresent()) {
            Agency agency1 = optionalAgency.get();
            agency1.setName(agency.getName());
            agency1.setCountry(agency.getCountry());
            agency1.setPhoneNumber(agency.getPhoneNumber());
            agency1.setEmail(agency.getEmail());
            agency1.setImageLink(agency.getImageLink());
            agency1.setHouses(agency.getHouses());
            agency1.setCustomers(agency.getCustomers());
            entityManager.merge(agency1);
        }
    }

    @Override
    public void deleteById(Long id) {
        Agency agencyToRemove = entityManager.find(Agency.class, id);

        if (agencyToRemove != null) {
            List<House> associatedHouses = agencyToRemove.getHouses();
            for (House house : associatedHouses) {
                house.setAgency(null);
            }

            entityManager.remove(agencyToRemove);
        } else {
            throw new EntityNotFoundException("Agency with ID " + id + " not found");
        }
    }


    @Override
    public List<Agency> searchAgency(String name) {
        return entityManager.createQuery("SELECT a FROM Agency a WHERE a.name = :name", Agency.class).
                setParameter("name", name).getResultList();
    }

    @Override
    public void assignCustomerToAgency(Long agencyId, List<Long> customerIds){
        Agency agency = agencyService.getById(agencyId);
        if (agency != null) {
            List<Customer> customers = new ArrayList<>();
            for (Long customerId : customerIds) {
                Customer customer = customerService.getCustomerById(customerId);
                if (customer != null) {
                    customer.setAgency(agency);
                    customers.add(customer);
                }
            }
            agency.setCustomers(customers);
            agencyService.saveAgency(agency);
        }
    }
}