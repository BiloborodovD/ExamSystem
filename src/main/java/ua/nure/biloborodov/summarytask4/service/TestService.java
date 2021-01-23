package ua.nure.biloborodov.summarytask4.service;

import java.util.List;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.db.repository.TestRepository;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class TestService {

  private final TestRepository repository;

  public TestService(TestRepository repository) {
    this.repository = repository;
  }

  public List<Test> findAll() throws DBException {
    return repository.findAll();
  }

  public List<Test> findBySubjectId(int subjectId, String order) throws DBException {
    return repository.findBySubjectId(subjectId, order);
  }

  public Test findById(int id) throws DBException {
    return repository.findById(id);
  }

  public void deleteById(int id) throws DBException {
    repository.deleteById(id);
  }

  public Test create(Test test) throws DBException {
    return repository.create(test);
  }

  public Test update(Test test) throws DBException {
    return repository.update(test);
  }
}
