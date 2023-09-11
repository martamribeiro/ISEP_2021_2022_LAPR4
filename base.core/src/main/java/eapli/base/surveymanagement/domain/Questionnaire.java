package eapli.base.surveymanagement.domain;


import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.time.util.Calendars;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * A Questionnaire.
 *
 * @author Marta Ribeiro 1201592
 */
@Entity(name = "Questionnaire")
@Table(name = "questionnaire")
public class Questionnaire implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String code;

    private String title;

    private String welcomeMessage;

    @Column(length = 2000)
    private String questionnaireContent;

    private String finalMessage;

    //we're aware that ManytoMany relationships are to be avoided, however, due to lack of time, for this Sprint it will be mapped like this
    @ManyToMany
    private List<Client> targetAudience;
    //list<Client> targetAudience

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TheRule> rules;

    //createdOn
    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    //endDate -> opcional
    @Temporal(TemporalType.DATE)
    private Calendar endDate;


    public Questionnaire(final String code, final String title, final String welcomeMessage, final String questionnaireContent, final String finalMessage, final List<Client> targetAudience, final Set<TheRule> rules){
        Preconditions.noneNull(code, title, questionnaireContent, finalMessage, rules);
        this.code=code;
        this.title=title;
        this.welcomeMessage = welcomeMessage;
        this.questionnaireContent = questionnaireContent;
        this.finalMessage = finalMessage;
        this.targetAudience = targetAudience;
        this.rules = rules;
        this.createdOn = Calendars.now();
    }

    protected Questionnaire(){

    }

    public void changeEndDate(Calendar newEndDate){
        endDate = newEndDate;
    }

    public String code() {
        return code;
    }

    public String title() {
        return title;
    }

    public String welcomeMessage() {
        return welcomeMessage;
    }

    public String finalMessage() {
        return finalMessage;
    }

    public String content(){
        return questionnaireContent;
    }

    public List<Client> targetAudience(){
        return targetAudience;
    }

    public QuestionnaireDTO toDTO(){
        return new QuestionnaireDTO(this.code, this.title, this.welcomeMessage, this.questionnaireContent, this.finalMessage, this.targetAudience);
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Questionnaire)) {
            return false;
        }

        final var that = (Questionnaire) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public String identity() {
        return code;
    }


}
