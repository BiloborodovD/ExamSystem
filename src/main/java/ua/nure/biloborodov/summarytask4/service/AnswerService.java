package ua.nure.biloborodov.summarytask4.service;

import ua.nure.biloborodov.summarytask4.db.entity.Answer;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.summarytask4.exception.DBException;

import java.util.List;

public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void addList(List<Answer> list, Question question) throws DBException {
        answerRepository.addList(list, question);
    }

    public List<Answer> findByQuestion(Question question) throws DBException {
        return answerRepository.findByQuestion(question);
    }

    public Answer findById(int id) throws DBException {
        return answerRepository.findById(id);
    }

    public void deleteById(int id) throws DBException {
        answerRepository.deleteById(id);
    }

    public Answer create(Answer answer) throws DBException {
        return answerRepository.create(answer);
    }

    public Answer update(Answer answer) throws DBException {
        return answerRepository.update(answer);
    }
}
