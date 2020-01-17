package fa.training.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fa.training.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * @author TrangDM2
   * @param status
   * @param id
   */
  @Transactional
  @Modifying
  @Query("update User u set u.status= :status where u.id= :id")
  void updateUserStatus(@Param("status") String status, @Param("id") int id);

  /**
   * @author TrangDM2
   * @param clazzId
   * @param role
   * @return
   */
  @Query(value = "SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc WHERE uc.clazz_id = :clazzId) AND u.role= :role ", nativeQuery = true)
  List<User> findUserByClazzAndRole(@Param("clazzId") Integer clazzId, @Param("role") String role);

  /**
   * @author TrangDM2
   * @return
   */
  @Query(value = "SELECT * FROM uzer u WHERE u.id NOT IN (SELECT uc.user_id FROM user_clazz uc WHERE uc.clazz_id = :clazzId) AND role= :role", nativeQuery = true)
  List<User> findTrainerNotInClassByClassId(@Param("clazzId") Integer clazzId, @Param("role") String role);

  /**
   * @author TrangDM2
   * 
   */
  List<User> findAllByRole(String role);

  /**
   * @author TrangDM2
   * 
   */
  @Query("select u from User u JOIN u.clazzList uc ON uc.id IN (SELECT c.id FROM Clazz c WHERE category= :category) and u.role= :role ")
  List<User> getAllUserByClassCategory(@Param("category") String category, @Param("role") String role);

  /**
   * @author TrangDM2
   * @param account
   * @return
   */
  Optional<User> findByAccount(String account);

  /**
   * @author TrangDM2
   * @return
   */
  @Query("select u from User u Where (u.firstName like :keyword or u.lastName like :keyword or u.account like :keyword) and role= :role and status like :status order by id DESC")
  List<User> findUsersByKeyword(@Param("keyword") String keyword, @Param("role") String role,
      @Param("status") String status);

  /**
   * @author TrangDM2
   * @param email
   * @return number of row where email = param
   */
  @Query("select count(u) from User u where account like :account")
  int getNumberOfAccount(@Param("account") String account);

  /**
   * @author TrangDM2
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
      + "FROM uzer u INNER JOIN user_clazz uc\r\n"
      + "ON u.role = :role AND u.id = uc.user_id AND u.status = :status \r\n"
      + "WHERE uc.clazz_id in (SELECT uc.clazz_id\r\n"
      + "FROM uzer u INNER JOIN user_clazz uc ON u.id = :user AND u.id  = uc.user_id)", nativeQuery = true)
  public List<User> getMembers(@Param("role") String role, @Param("status") String status, User user);

  /**
   * @author HoangLV7
   *
   * @param account
   * @param password
   * @return trainee login
   */
  public User findByAccountAndPassword(String account, String password);

  /**
   * @author HoangLV7
   *
   * @param userId
   * @return
   */
  public User findUserById(int userId);

  /**
   * @author ToanNT18
   * @param userId
   * @return User
   * @return find User by user id.
   */
  @Query(value = "SELECT * FROM uzer u WHERE u.id = :userId", nativeQuery = true)
  List<User> findTraineeByUserId(@Param("userId") Integer userId);

  /**
   * @author ToanNT18
   * @return top 10 trainee.
   */
  @Query(value = "SELECT TOP 10 *  FROM uzer u WHERE u.role = :role", nativeQuery = true)
  List<User> findTop10Trainee(@Param("role") String role);

  /**
   * @author ToanNT18
   * @param clazzId
   * @return List<User>
   * @return all trainee by clazz.
   */
  @Query(value = "SELECT * FROM [udf_findTraineeByClazz](:clazzId)", nativeQuery = true)
  List<User> findTraineeByClazzId(@Param("clazzId") Integer clazzId, Pageable pageable);

  /**
   * @author ToanNT18
   * @param category
   * @param role     : Trainee
   * @return All trainee by category
   */
  @Query(value = "SELECT * FROM udf_findTraineeByCategory(:category, :role)", nativeQuery = true)
  List<User> findTraineeByCategory(@Param("category") String category, Pageable pageable);
}
