package postman.test.postman.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import postman.test.postman.db.pojo.Farmer;

@Repository
public interface FarmerRepo extends JpaRepository<Farmer, Integer> {

}
