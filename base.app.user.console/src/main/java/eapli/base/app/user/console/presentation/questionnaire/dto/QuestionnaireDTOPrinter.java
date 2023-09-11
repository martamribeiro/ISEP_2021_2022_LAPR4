package eapli.base.app.user.console.presentation.questionnaire.dto;

import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.visitor.Visitor;

public class QuestionnaireDTOPrinter implements Visitor<QuestionnaireDTO> {
    @Override
    public void visit(QuestionnaireDTO visitee) {
        System.out.print(visitee.toString());
    }
}
