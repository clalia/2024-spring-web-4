package postman.test.postman.db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import postman.test.postman.db.pojo.Farm;
import postman.test.postman.db.repo.FarmRepo;

@Service
public class FarmService {

    @Autowired
    private FarmRepo farmRepo;

    public List<Farm> getAllFarms() {
        return farmRepo.findAll();
    }

    public Optional<Farm> getFarmById(int id) {
        return farmRepo.findById(id);
    }
    public void save(Farm farm) {

        farmRepo.save(farm);
    }

    public void delete(Farm farm) {

        farmRepo.delete(farm);
    } 

}
