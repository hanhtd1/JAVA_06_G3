package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.models.Clazz;

@Repository
public interface ClassRepository extends JpaRepository<Clazz, Integer> {
  
}
