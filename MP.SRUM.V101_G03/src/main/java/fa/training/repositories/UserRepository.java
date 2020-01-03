package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  
  @Query("select u from User u where role = :role")
  List<User> getAllUserByRole(@Param("role") String role);
  
  @Query("select u from User u JOIN u.clazzList uc ON uc.id IN (SELECT c.id FROM Clazz c WHERE category= :category) and u.role= :role ")
  List<User> getAllUserByClassCategory(@Param("category") String category, @Param("role") String role);
  
  @Query("select u from User u JOIN u.clazzList uc ON uc.id =:clazzId and u.role= :role ")
  List<User> getAllUserByClass(@Param("clazzId") int classId, @Param("role") String role);
}
