package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.exceptions.ResourceNotFoundException;
import ba.unsa.etf.zavrsni.server.models.Answer;
import ba.unsa.etf.zavrsni.server.service.AnswerService;
import ba.unsa.etf.zavrsni.server.service.QuestionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/questions/{questionId}/answers")
    public List<Answer>  getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerService.find(questionId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(@PathVariable Long questionId, @Valid @RequestBody Answer answer) {
        return questionService.findById(questionId).map(question -> {
            answer.setQuestion(question);
            return answerService.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

}
