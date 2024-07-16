package postman.test.postman.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import postman.test.postman.db.pojo.Farm;

@Repository
public interface FarmRepo extends JpaRepository<Farm, Integer> {
}

