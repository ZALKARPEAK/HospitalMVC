package main.Service.impl;

import main.Model.Agency;
import main.Repo.AgencyRepo;
import main.Service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepo agencyRepo;

    @Autowired
    public AgencyServiceImpl(AgencyRepo agencyRepo) {
        this.agencyRepo = agencyRepo;
    }

    @Override
    public void saveAgency(Agency agency) {
        agencyRepo.saveAgency(agency);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyRepo.getAllAgency();
    }

    @Override
    public Agency getById(Long id) {
        return agencyRepo.getById(id);
    }

    @Override
    public void updateAgencyById(Long id, Agency agency) {
        agencyRepo.updateAgencyById(id, agency);
    }

    @Override
    public void deleteById(Long id) {
        agencyRepo.deleteById(id);
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return agencyRepo.searchAgency(word);
    }
}
