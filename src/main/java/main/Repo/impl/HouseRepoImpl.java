package main.Repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import main.Api.BookingApi;
import main.Model.Agency;
import main.Model.House;
import main.Repo.HouseRepo;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class HouseRepoImpl implements HouseRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveHouse(Long agencyId, House house) {
        Agency agency = entityManager.find(Agency.class, agencyId);
        if (agency.getHouses() != null) {
            agency.getHouses().add(house);
        } else {
            agency.setHouses(Collections.singletonList(house));
        }
        house.setAgency(agency);
        entityManager.persist(house);
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        return entityManager.createQuery("SELECT h FROM House h WHERE h.agency.id = :agencyId", House.class)
                .setParameter("agencyId", agencyId)
                .getResultList();
    }

    @Override
    public List<House> getAllHouseByAgencyId() {
        return entityManager.createQuery("SELECT h FROM House h ", House.class)
                .getResultList();
    }


    @Override
    public House getHouseById(Long id) {
            return entityManager.find(House.class, id);
    }

    @Override
    public void updateHouseById(Long id, House house) {
        House house1 = getHouseById(id);

        house1.setAddress(house.getAddress());
        house1.setPrice(house.getPrice());
        house1.setRoom(house.getRoom());
        house1.setCountry(house.getCountry());
        house1.setDescription(house.getDescription());
        house1.setRoom(house.getRoom());
        house1.setHouseType(house.getHouseType());
        house1.setBooking(house.getBooking());
        entityManager.merge(house1);
    }

    @Override
    public void deleteHouseById(Long id) {
        House house = entityManager.find(House.class, id);

        if (house != null) {
            entityManager.remove(house);
        } else {
            throw new RuntimeException("House not found with id: " + id);
        }
    }


    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        try {
            if (ascOrDesc.equals("asc")) {
                return entityManager.createQuery("from House a order by houseType asc", House.class).getResultList();
            } else if (ascOrDesc.equals("desc")) {
                return entityManager.createQuery("from House a order by houseType desc ", House.class).getResultList();
            }
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<House> searchHouse(String word) {
        return entityManager.createQuery("select u from House u where u.address like :word", House.class)
                .setParameter("word", word).getResultList();
    }

    @Override
    public void saveHouse1(House house1) {
        entityManager.persist(house1);
    }
}
