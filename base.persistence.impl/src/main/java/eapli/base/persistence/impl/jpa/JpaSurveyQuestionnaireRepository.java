package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.warehousemanagement.domain.AGV;

import javax.persistence.TypedQuery;

public class JpaSurveyQuestionnaireRepository extends BaseJpaRepositoryBase<Questionnaire, String, String> implements SurveyQuestionnareRepository {
    JpaSurveyQuestionnaireRepository(){
        super("code");
    }

    @Override
    public Iterable<Questionnaire> findAllQuestionnaireWithClient(Client client) {
        Long clientId = client.identity();
        final TypedQuery<Questionnaire> query = entityManager().createQuery(
                "SELECT d FROM Questionnaire d JOIN d.targetAudience ta WHERE ta.clientId = :clientId",
                Questionnaire.class);
        query.setParameter("clientId", clientId);

        return query.getResultList();
    }
}
