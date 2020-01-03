package fa.training.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.training.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  /**
   * @author TranDM2
   * 
   */
  @Query("select u from User u where role = :role")
  List<User> getAllUserByRole(@Param("role") String role);

  /**
   * @author TranDM2
   * 
   */
  @Query("select u from User u JOIN u.clazzList uc ON uc.id IN (SELECT c.id FROM Clazz c WHERE category= :category) and u.role= :role ")
  List<User> getAllUserByClassCategory(@Param("category") String category, @Param("role") String role);

  /**
   * @author TranDM2
   * 
   */
  @Query("select u from User u JOIN u.clazzList uc ON uc.id =:clazzId and u.role= :role ")
  List<User> getAllUserByClass(@Param("clazzId") int classId, @Param("role") String role);

  /**
   * @author HoangLV7
   * 
   * @param user
   * @return list classmate of trainee
   */
  @Query(value = "SELECT u.id, u.account, u.birth_day, u.email, u.first_name, u.gender, u.last_name, u.password, u.phone, u.role, u.status\r\n"
      + "FROM uzer u INNER JOIN user_clazz uc\r\n" + "ON u.role = 'ROLE_TRAINEE' AND u.id = uc.user_id \r\n"
      + "WHERE uc.clazz_id = (SELECT uc.clazz_id\r\n"
      + "FROM uzer u INNER JOIN user_clazz uc ON u.id = :user AND u.id  = uc.user_id)", nativeQuery = true)
  public List<User> getMembers(User user);

  /**
   * @author HoangLV7
   *
   * @param account
   * @param password
   * @return trainee login
   */
  public User findByAccountAndPassword(String account, String password);

}
