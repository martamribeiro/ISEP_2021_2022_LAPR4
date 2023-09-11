package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long answerID;

    @OneToOne
    private Questionnaire questionnaire;

    @OneToOne
    private Client client;

    private String questionID;

    @ElementCollection
    private List<String> answers;

    public Answer(Questionnaire questionnaire, Client client, String questionID, List<String> answers) {
        Preconditions.noneNull(questionnaire, client, questionID, answers);
        this.questionnaire = questionnaire;
        this.client = client;
        this.questionID = questionID;
        this.answers = answers;
    }

    protected Answer() {
        //for ORM only
    }

    public Client client() {
        return client;
    }

    public Questionnaire questionnaire() {
        return questionnaire;
    }

    public String questionID(){
        return questionID;
    }

    public List<String> answers(){
        return answers;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Answer)) {
            return false;
        }

        final var that = (Answer) other;
        if (this == that) {
            return true;
        }
        return identity().equals(that.identity());
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.answerID;
    }


}
