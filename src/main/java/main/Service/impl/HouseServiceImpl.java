package main.Service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.Api.BookingApi;
import main.Model.Customer;
import main.Model.House;
import main.Repo.HouseRepo;
import main.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HouseServiceImpl implements HouseService {

    @Autowired
    private  HouseRepo houseRepo;

    @Override
    public void saveHouse(Long agencyId, House house) {
        houseRepo.saveHouse(agencyId, house);
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        return houseRepo.getAllHouseByAgencyId(agencyId);
    }

    @Override
    public List<House> getAllHouseByAgencyId() {
        return houseRepo.getAllHouseByAgencyId();
    }

    @Override
    public House getHouseById(Long id) {
        return houseRepo.getHouseById(id);
    }

    @Override
    public void updateHouseById(Long id, House house) {
        houseRepo.updateHouseById(id, house);
    }

    @Override
    public void deleteHouseById(Long id) {
        houseRepo.deleteHouseById(id);
    }

    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        return houseRepo.sortHouseByHouseType(ascOrDesc);
    }

    @Override
    public List<House> searchHouse(String word) {
        return houseRepo.searchHouse(word);
    }

    public void bookHouse(BookingApi bookingApi) {
        bookingApi.book();
    }

    public void unbookHouse(BookingApi bookingApi) {
        bookingApi.unbook();
    }

    @Override
    public void saveHouse1(House house1){
        houseRepo.saveHouse1(house1);
    }


}
