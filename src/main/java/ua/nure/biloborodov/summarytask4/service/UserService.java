package ua.nure.biloborodov.summarytask4.service;

import java.util.List;
import ua.nure.biloborodov.summarytask4.db.Role;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.db.repository.UserRepository;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class UserService {

  private UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> findAll() throws DBException {
    return repository.findAll();
  }

  public User findByLogin(String login) throws DBException {
    return repository.findByLogin(login);
  }

  public User findById(int id) throws DBException {
    return repository.findById(id);
  }

  public List<User> findUsersByRole(Role role) throws DBException {
    return repository.findUsersByRole(role);
  }

  public void deleteById(int id) throws DBException {
    repository.deleteById(id);
  }

  public User create(User user) throws DBException {
    return repository.create(user);
  }

  public User update(User user) throws DBException {
    return repository.update(user);
  }
}
