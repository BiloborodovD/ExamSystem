package ua.nure.biloborodov.summarytask4.service;

import java.util.List;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.db.entity.UsersTests;
import ua.nure.biloborodov.summarytask4.db.repository.UsersTestsRepository;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class UsersTestsService {

  private UsersTestsRepository repository;

  public UsersTestsService(
      UsersTestsRepository repository) {
    this.repository = repository;
  }

  public UsersTests create(UsersTests usersTests) throws DBException {
    return repository.create(usersTests);
  }

  public List<UsersTests> findByUser(User user) throws DBException {
    return repository.findByUser(user);
  }
}
