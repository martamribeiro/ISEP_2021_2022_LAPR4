package eapli.base.surveymanagement.application;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;

import java.util.ArrayList;
import java.util.List;

public class ListQuestionnaireDTOService {
    private final SurveyQuestionnareRepository questionnareRepository = PersistenceContext.repositories().questionnaries();

    public Iterable<QuestionnaireDTO> questionnairesForClient(Client client){
        final Iterable<Questionnaire> questionnaires = this.questionnareRepository.findAllQuestionnaireWithClient(client);

        final List<QuestionnaireDTO> ret = new ArrayList<>();
        questionnaires.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
