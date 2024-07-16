package postman.test.postman.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import postman.test.postman.db.pojo.Farm;
import postman.test.postman.db.service.FarmService;


@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping("/test/add")
    public ResponseEntity<Void> addTest() {

        Farm farm1 = new Farm("FarmName1", "Milano");
        Farm farm2 = new Farm("FarmName2", "Roma");
        Farm farm3 = new Farm("FarmName3", "Napoli");
        
        farmService.save(farm1);
        farmService.save(farm2);
        farmService.save(farm3);

        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<List<Farm>> getAllFarms() {
        List<Farm> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }

    
    @PostMapping("")
    public ResponseEntity<Farm> addFarm(@RequestBody Farm farm) {
        farmService.save(farm);
        return ResponseEntity.ok(farm);
    }

    
    @PatchMapping("{id}")
    public ResponseEntity<Farm> updateFarm(
            @PathVariable int id,
            @RequestBody Farm updatedFarm) {

        Optional<Farm> optFarm = farmService.getFarmById(id);

        if (optFarm.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Farm existingFarm = optFarm.get();
        existingFarm.setName(updatedFarm.getName());
        existingFarm.setCity(updatedFarm.getCity());

        farmService.save(existingFarm);

        return ResponseEntity.ok(existingFarm);
    }
}
