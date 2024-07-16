package postman.test.postman.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import postman.test.postman.db.pojo.Farm;
import postman.test.postman.db.pojo.Farmer;
import postman.test.postman.db.service.FarmService;
import postman.test.postman.db.service.FarmerService;
import postman.test.postman.dto.FarmerDto;




@RestController
@RequestMapping("/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private FarmService farmService;

    @GetMapping("/test/add")
    public ResponseEntity<Void> addTestEntity() {

        Farm farm1 = new Farm("FarmName1", "Milano");
        Farm farm2 = new Farm("FarmName2", "Roma");
        Farm farm3 = new Farm("FarmName3", "Napoli");
        
        farmService.save(farm1);
        farmService.save(farm2);
        farmService.save(farm3);

        Farmer f1 = new Farmer("Mario", "Rossi", 30, farm1);
        Farmer f2 = new Farmer("Diego", "Bianchi", 50, farm2);
        Farmer f3 = new Farmer("Luigi", "Verdi", 40, farm3);

        farmerService.save(f1);
        farmerService.save(f2);
        farmerService.save(f3);

        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<List<Farmer>> getAllFarmers() {

        List<Farmer> farmers = farmerService.getAllFarmers();

        return ResponseEntity.ok(farmers);
    }
    @PostMapping("")
    public ResponseEntity<Farmer> addFarmer(
            @RequestBody FarmerDto farmerDto) {

        Farmer f = new Farmer(farmerDto.getName(), farmerDto.getSurname(), farmerDto.getAge(), null);

        Optional<Farm> optfFarm = farmService.getFarmById(farmerDto.getFarmId());

        if (optfFarm.isEmpty())
            return ResponseEntity.badRequest().build();

        Farm fFarm = optfFarm.get();
        f.setFarm(fFarm);

        farmerService.save(f);

        return ResponseEntity.ok(f);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFarmer(
            @PathVariable int id) {

        Optional<Farmer> optF = farmerService.getFarmerById(id);

        if (optF.isEmpty())
            return ResponseEntity.notFound().build();

        Farmer f = optF.get();
        farmerService.delete(f);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Farmer> updateFarmer(
            @PathVariable int id,
            @RequestBody FarmerDto farmerDto) {

        Optional<Farmer> optF = farmerService.getFarmerById(id);

        if (optF.isEmpty())
            return ResponseEntity.notFound().build();

        Farmer f = optF.get();
        f.update(farmerDto);

        Optional<Farm> optfFarm = farmService.getFarmById(farmerDto.getFarmId());

        if (optfFarm.isEmpty())
            return ResponseEntity.badRequest().build();

        Farm fFarm = optfFarm.get();
        f.setFarm(fFarm);

        farmerService.save(f);

        return ResponseEntity.ok(f);
    }

}

