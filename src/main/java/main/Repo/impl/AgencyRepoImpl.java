package main.Repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import main.Model.Agency;
import main.Model.House;
import main.Repo.AgencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public class AgencyRepoImpl implements AgencyRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public AgencyRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveAgency(Agency agency) {
        boolean x = getAllAgency().stream().anyMatch(agency1 -> agency1.getName().equals(agency.getName()));
        if(!x){
            entityManager.merge(agency);
        }
        if(x){
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
            entityManager.merge(agency1);
        }
    }

    @Override
    public void deleteById(Long id) {
        Agency agencyToRemove = entityManager.find(Agency.class, id);

        if (agencyToRemove != null) {
            List<House> associatedHouses = agencyToRemove.getHouses();
            for (House house : associatedHouses) {
                house.setAgency(null);  // Set the agency to null
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
}
