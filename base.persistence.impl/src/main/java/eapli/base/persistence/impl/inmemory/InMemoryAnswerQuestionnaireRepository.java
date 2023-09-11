package eapli.base.persistence.impl.inmemory;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;
import java.util.Map;

public class InMemoryAnswerQuestionnaireRepository extends InMemoryDomainRepository<Answer, Long> implements AnswerQuestionnaireRepository {
    static {
        InMemoryInitializer.init();
    }

    //NÃO TESTADO
    @Override
    public Iterable<Answer> findAnswersByClient(Client client, Questionnaire questionnaire) {
        return match(e -> (e.client().equals(client) && e.questionnaire().equals(questionnaire)));
    }

    @Override
    public Iterable<QuestionnaireDTO> findAnsweredQuestionnaires() {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }

    @Override
    public int findNumberOfQuestionnaireResponses(QuestionnaireDTO survey) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }

    @Override
    public Map<String, List<List<String>>> findQuestionnaireQuestionsAnswers(Questionnaire survey) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }

}
