package fa.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.models.Clazz;

/**
 * @author TrangDM2
 *
 */
@Service
public interface AdminClassService {

  List<Clazz> getClasses();

  Clazz getClass(Integer id);

  List<Clazz> findClazzByKeyword(String keyword, String status);

  Clazz saveClass(Clazz clazz, String status);

  String generateClassName(String location, String type, String category);

  Clazz getClassByName(String name);

  List<Clazz> getClassByUser(Integer userId);

  Clazz addUserToClass(Integer traineeId, Integer classId) throws IllegalArgumentException;

  Clazz removeUserFromClass(Integer traineeId, Integer classId) throws IllegalArgumentException;

  Clazz addSubjectToClass(Integer subjectId, Integer classId) throws IllegalArgumentException;

  Clazz removeSubjectFromClass(Integer subjectId, Integer classId) throws IllegalArgumentException;

}
