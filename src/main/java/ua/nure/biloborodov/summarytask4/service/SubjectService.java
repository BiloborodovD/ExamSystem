package ua.nure.biloborodov.summarytask4.service;

import java.util.List;
import ua.nure.biloborodov.summarytask4.db.Language;
import ua.nure.biloborodov.summarytask4.db.entity.Subject;
import ua.nure.biloborodov.summarytask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class SubjectService {

  private SubjectRepository repository;

  public SubjectService(SubjectRepository repository) {
    this.repository = repository;
  }

  public List<Subject> findAll() throws DBException {
    return repository.findAll();
  }

  public List<Subject> findSubjectsByLanguage(Language language) throws DBException {
    return repository.findSubjectsByLanguage(language);
  }

  public Subject findById(int id) throws DBException {
    return repository.findById(id);
  }

  public void deleteById(int id) throws DBException {
    repository.deleteById(id);
  }

  public Subject create(Subject subject) throws DBException {
    return repository.create(subject);
  }

  public Subject update(Subject subject) throws DBException {
    return repository.update(subject);
  }
}
