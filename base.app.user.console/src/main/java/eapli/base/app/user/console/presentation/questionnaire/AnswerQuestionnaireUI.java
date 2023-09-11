package eapli.base.app.user.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.surveymanagement.antlr.SurveyVisitorWithAnswer;
import eapli.base.surveymanagement.antlr.questionnaireLexer;
import eapli.base.surveymanagement.antlr.questionnaireParser;
import eapli.base.surveymanagement.application.AnswerQuestionnaireController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class AnswerQuestionnaireUI extends AbstractUI {
    private AnswerQuestionnaireController controller = new AnswerQuestionnaireController();

    @Override
    protected boolean doShow() {


        QuestionnaireDTO survey = null;

        boolean hasNotAnsweredYet = false;

        while(!hasNotAnsweredYet) {
            Iterable<QuestionnaireDTO> surveys = this.controller.questionnairesForClient();
            final SelectWidget<QuestionnaireDTO> selector = new SelectWidget<>("Questionnaires For Client:", surveys, new QuestionnaireDTOPrinter());
            selector.show();
            survey = selector.selectedElement();
            if(survey == null)
                break;
            hasNotAnsweredYet = controller.verifyIfClientAnswered(survey);
            if(!hasNotAnsweredYet)
                System.out.println("You have already answered that survey! Please, choose another one.");

        }




        if(survey != null) { //the client doesn't want to exit
            String surveyString = extractSurvey(survey);

            Map<String, List<String>> answers = new HashMap<>();

            boolean validAnswers = parseSurveyAnswersWithVisitor(surveyString, answers);

            if(validAnswers) {
                System.out.println("Your answers will be now saved!");
                this.controller.sendAnswersToBeSaved(answers, survey);
                System.out.println("Answers successfuly saved!");
            }
        }



        return false;
    }

    private String extractSurvey(QuestionnaireDTO survey) {
        StringBuilder text = new StringBuilder();
        text.append(survey.code() + " " + survey.title() + "\n");
        if(!survey.welcomeMessage().isEmpty())
            text.append(survey.welcomeMessage() + "\n\n");
        text.append(survey.questionnaireContent() + "\n\n\n\n");
        text.append(survey.finalMessage());
        return text.toString();
    }

    private boolean parseSurveyAnswersWithVisitor(String survey, Map<String, List<String>> answers) {

        try {

            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromString(survey));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.survey();
            SurveyVisitorWithAnswer eval = new SurveyVisitorWithAnswer(answers);
            eval.visit(tree);
            return true;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return false;
        }
    }

    @Override
    public String headline() {
        return "Answer Questionnaire.";
    }
}