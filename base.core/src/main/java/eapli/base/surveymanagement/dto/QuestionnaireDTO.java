package eapli.base.surveymanagement.dto;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.surveymanagement.domain.TheRule;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class QuestionnaireDTO implements Serializable {
    private String code;

    private String title;

    private String welcomeMessage;

    private String questionnaireContent;

    private String finalMessage;

    private List<Client> targetAudience;

    public QuestionnaireDTO(final String code, final String title, final String welcomeMessage, final String questionnaireContent, final String finalMessage, final List<Client> targetAudience){
        this.code = code;
        this.title = title;
        this.welcomeMessage = welcomeMessage;
        this.questionnaireContent = questionnaireContent;
        this.finalMessage = finalMessage;
        this.targetAudience = targetAudience;
    }


    public String code() {
        return this.code;
    }

    public String title() {
        return this.title;
    }

    public String welcomeMessage() {
        return this.welcomeMessage;
    }

    public String questionnaireContent() {
        return this.questionnaireContent;
    }

    public String finalMessage() {
        return this.finalMessage;
    }

    public List<Client> targetAudience(){
        return this.targetAudience;
    }

    @Override
    public String toString(){
        return String.format("Questionnaire: %s %s \n", code, title);
    }
}
