package ua.nure.biloborodov.summarytask4.service;

import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.summarytask4.exception.DBException;

import java.util.List;

public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> findAll() throws DBException {
        return repository.findAll();
    }

    public List<Question> findByTest(Test test) throws DBException {
        return repository.findByTest(test);
    }

    public Question findById(int id) throws DBException {
        return repository.findById(id);
    }

    public void deleteById(int id) throws DBException {
        repository.deleteById(id);
    }

    public Question create(Question question) throws DBException {
        return repository.create(question);
    }

    public Question update(Question question) throws DBException {
        return repository.update(question);
    }
}
