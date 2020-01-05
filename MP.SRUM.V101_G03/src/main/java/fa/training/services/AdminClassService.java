package fa.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fa.training.models.Clazz;

@Service
public interface AdminClassService {

  List<Clazz> getClasses();

}
