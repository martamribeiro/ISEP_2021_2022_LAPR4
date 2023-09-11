package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.app.user.console.presentation.questionnaire.dto.QuestionnaireDTOPrinter;
import eapli.base.surveymanagement.antlr.SurveyQuestionsVisitor;
import eapli.base.surveymanagement.antlr.questionnaireLexer;
import eapli.base.surveymanagement.antlr.questionnaireParser;
import eapli.base.surveymanagement.application.GenerateReportController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.List;
import java.util.Map;

/**
 * @author Marta Ribeiro 1201592
 */
public class GenerateReportUI extends AbstractUI {

    private GenerateReportController controller = new GenerateReportController();

    @Override
    protected boolean doShow(){

        QuestionnaireDTO survey = null;
        if (this.controller.answeredQuestionnaires().iterator().hasNext()) {
            Iterable<QuestionnaireDTO> surveys = this.controller.answeredQuestionnaires();
            final SelectWidget<QuestionnaireDTO> selector = new SelectWidget<>("Answered Questionnaires:", surveys, new QuestionnaireDTOPrinter());
            selector.show();
            survey = selector.selectedElement();
            generateReport(survey);
        } else {
            System.out.println("There are no questionnaires with responses to analyse.");
        }
        return false;
    }

    private void generateReport(QuestionnaireDTO survey){
        System.out.println(survey.code() + " " + survey.title() + "\n");
        int targetNum = survey.targetAudience().size();
        System.out.print("Target Audience: " + targetNum + "\n");
        int answerNum = controller.numberOfQuestionnaireResponses(survey);
        System.out.print("Questionnaire's Answers: " + answerNum + "\n");
        double answerPercentage = (double) answerNum*100/targetNum;
        System.out.println("Answering Percentage: " + answerPercentage + "%\n");
        Map<String, List<List<String>>> questionsAnswers = controller.questionnaireQuestionsAnswers(survey);
        boolean questionsAndAnswersCollectedSuccessfully = parseSurveyQuestionsWithVisitor(survey, questionsAnswers);
        if (questionsAndAnswersCollectedSuccessfully){
            System.out.println();
            System.out.println("End of Report.");
        }
    }

    private String extractSurvey(QuestionnaireDTO survey) {
        StringBuilder text = new StringBuilder();
        text.append(survey.code()).append(" ").append(survey.title()).append("\n");
        if(!survey.welcomeMessage().isEmpty())
            text.append(survey.welcomeMessage()).append("\n\n");
        text.append(survey.questionnaireContent()).append("\n\n\n\n");
        text.append(survey.finalMessage());
        return text.toString();
    }

    private boolean parseSurveyQuestionsWithVisitor(QuestionnaireDTO survey, Map<String, List<List<String>>> questionsAnswers){
        try {
            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromString(extractSurvey(survey)));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.survey();
            SurveyQuestionsVisitor eval = new SurveyQuestionsVisitor(questionsAnswers);
            eval.visit(tree);
            return true;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return false;
        }
    }

    @Override
    public String headline(){
        return "See Questionnaire's Report.";
    }

}
