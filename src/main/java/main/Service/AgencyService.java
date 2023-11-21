package main.Service;

import main.Model.Agency;

import java.util.List;

public interface AgencyService {
    void saveAgency(Agency agency);

    List<Agency> getAllAgency();

    Agency getById(Long id);

    void updateAgencyById(Long id, Agency agency);

    void deleteById(Long id);

    List<Agency> searchAgency(String word);
    void assignCustomerToAgency(Long agencyId, List<Long> customerIds);
}
