package postman.test.postman.db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import postman.test.postman.db.pojo.Farmer;
import postman.test.postman.db.repo.FarmerRepo;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepo farmerRepo;

    public List<Farmer> getAllFarmers() {
        return farmerRepo.findAll();
    }

    public Optional<Farmer> getFarmerById(int id) {
        return farmerRepo.findById(id);
    }

    public void save(Farmer farmer) {
        farmerRepo.save(farmer);
    }

    public void delete(Farmer farmer) {
        farmerRepo.delete(farmer);
    }
}
