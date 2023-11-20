package main.Service;

import main.Api.BookingApi;
import main.Model.Customer;
import main.Model.House;

import java.util.List;

public interface HouseService {
    void saveHouse(Long agencyId,House house);
    List<House> getAllHouseByAgencyId(Long agencyId);
    House getHouseById(Long id);
    void updateHouseById(Long id,House house);
    void deleteHouseById(Long id) ;
    List<House> sortHouseByHouseType(String ascOrDesc);
    List<House> searchHouse(String word);
    void bookHouse(BookingApi bookingApi);
    void unbookHouse(BookingApi bookingApi);
}
