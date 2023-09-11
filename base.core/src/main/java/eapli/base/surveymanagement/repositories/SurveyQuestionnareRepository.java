package eapli.base.surveymanagement.repositories;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.DomainRepository;

public interface SurveyQuestionnareRepository extends DomainRepository<String, Questionnaire> {

    @Override
    Iterable<Questionnaire>findAll();

    Iterable<Questionnaire> findAllQuestionnaireWithClient(Client client);
}
