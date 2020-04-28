package ba.unsa.etf.zavrsni.server.service;

import ba.unsa.etf.zavrsni.server.models.Answer;
import ba.unsa.etf.zavrsni.server.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> find(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer save(Answer answer) {
        return  answerRepository.save(answer);
    }
}
