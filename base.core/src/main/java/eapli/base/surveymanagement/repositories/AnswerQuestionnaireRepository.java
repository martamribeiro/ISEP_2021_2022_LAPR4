package eapli.base.surveymanagement.repositories;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Map;

public interface AnswerQuestionnaireRepository extends DomainRepository<Long, Answer> {
    @Override
    Iterable<Answer> findAll();

    Iterable<Answer> findAnswersByClient(Client client, Questionnaire survey);

    Iterable<QuestionnaireDTO> findAnsweredQuestionnaires();

    int findNumberOfQuestionnaireResponses(QuestionnaireDTO survey);

    Map<String, List<List<String>>> findQuestionnaireQuestionsAnswers(Questionnaire survey);
}
