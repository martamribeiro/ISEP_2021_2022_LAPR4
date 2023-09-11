package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;

import javax.persistence.TypedQuery;
import java.util.*;

public class JpaAnswerQuestionnaireRepository extends BaseJpaRepositoryBase<Answer, Long, Long> implements AnswerQuestionnaireRepository {
    JpaAnswerQuestionnaireRepository(){
        super("answerID");
    }

    @Override
    public Iterable<Answer> findAnswersByClient(Client client, Questionnaire questionnaire) {
        final Map<String, Object> params = new HashMap<>();
        params.put("client", client);
        params.put("questionnaire", questionnaire);
        return match("e.client=:client AND e.questionnaire=:questionnaire", params);
    }

    @Override
    public Iterable<QuestionnaireDTO> findAnsweredQuestionnaires() {
        final TypedQuery<Answer> query = entityManager().createQuery(
                "SELECT a FROM Answer a",
                Answer.class);
        Iterable<Answer> answers = query.getResultList();
        List<Questionnaire> filteredByQuestionnaireList = new LinkedList<>();
        for (Answer a : answers){
            if (!filteredByQuestionnaireList.contains(a.questionnaire())){
                filteredByQuestionnaireList.add(a.questionnaire());
            }
        }
        List<QuestionnaireDTO> filteredByQuestionnaireListDTO = new LinkedList<>();
        QuestionnaireDTO questDTO;
        for (Questionnaire quest:
             filteredByQuestionnaireList) {
            questDTO = quest.toDTO();
            filteredByQuestionnaireListDTO.add(questDTO);
        }
        return filteredByQuestionnaireListDTO;
    }

    @Override
    public int findNumberOfQuestionnaireResponses(QuestionnaireDTO survey) {
        final TypedQuery<Answer> query = entityManager().createQuery(
                "SELECT a FROM Answer a",
                Answer.class);
        Iterable<Answer> answers = query.getResultList();
        List<Client> clientsWhoAnswered = new LinkedList<>();
        for (Answer a : answers){
            if (a.questionnaire().code().equalsIgnoreCase(survey.code()) && !clientsWhoAnswered.contains(a.client())){
                clientsWhoAnswered.add(a.client());
            }
        }
        return clientsWhoAnswered.size();
    }

    @Override
    public Map<String, List<List<String>>> findQuestionnaireQuestionsAnswers(Questionnaire survey) {
        final TypedQuery<Answer> query = entityManager().createQuery(
                "SELECT a FROM Answer a",
                Answer.class);
        Iterable<Answer> answers = query.getResultList();
        Map<String, List<List<String>>> questionsAndAnswers = new HashMap<>();

        for (Answer a : answers){
            if (a.questionnaire().sameAs(survey)) {
                if (!questionsAndAnswers.containsKey(a.questionID())) {
                    questionsAndAnswers.put(a.questionID(), new ArrayList<>());
                    questionsAndAnswers.get(a.questionID()).add(a.answers());
                } else {
                    questionsAndAnswers.get(a.questionID()).add(a.answers());
                }
            }
        }

        return questionsAndAnswers;
    }
}