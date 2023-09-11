package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.*;

public class AnswerQuestionnaireController {
    private final SurveyQuestionnareRepository questionnairesRepository = PersistenceContext.repositories().questionnaries();
    private final AnswerQuestionnaireRepository answersRepository = PersistenceContext.repositories().answers();

    private final AuthorizationService authz= AuthzRegistry.authorizationService();

    private final AnswerQuestionnaireService service = new AnswerQuestionnaireService();

    private final String FILE_PATH = "base.core/src/main/java/eapli/base/surveymanagement/antlr/surveys/";
    private final String FILE_EXTENSION = ".txt";

    public Iterable<QuestionnaireDTO> questionnairesForClient(){
        return service.questionnairesForClient(authz.session().get().authenticatedUser().email().toString());
    }


    public void sendAnswersToBeSaved(Map<String, List<String>> answers, QuestionnaireDTO survey) {
        service.sendAnswersToBeSaved(answers, survey, authz.session().get().authenticatedUser().email().toString());
    }

    public boolean verifyIfClientAnswered(QuestionnaireDTO survey) {
        return service.verifyIfClientHasAnswered(authz.session().get().authenticatedUser().email().toString(), survey);
    }


}
