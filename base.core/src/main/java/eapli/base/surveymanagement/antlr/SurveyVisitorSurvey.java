package eapli.base.surveymanagement.antlr;


import java.util.*;

public class SurveyVisitorSurvey extends questionnaireBaseVisitor {
    private static final int MAX_LENGTH_TITLE = 50;
    private static final int MAX_LENGTH_ALPHA_CODE = 15;

    //to check whether all questions have different ID's
    private Map<String, String> questionIDs = new HashMap<>();

    @Override
    public Boolean visitQuestionnaire_id(questionnaireParser.Questionnaire_idContext ctx) {
        String surveyCode = ctx.getText();

        if (surveyCode.length()>MAX_LENGTH_ALPHA_CODE)
            throw new IllegalArgumentException("The survey alphanumeric code can't have more than 15 chars!");

        return true;
    }

    @Override
    public Boolean visitLengthTitle(questionnaireParser.LengthTitleContext ctx) {
        String title = ctx.getText();

        if (title.length()>MAX_LENGTH_TITLE)
            throw new IllegalArgumentException("Title length is too long!");

        return true;
    }

    @Override
    public Boolean visitSection(questionnaireParser.SectionContext ctx) {
        String sectionID = ctx.numeric_id().getText();
        String questionIdOfRepeatability;
        if(sectionID.equals("1") && ctx.repeatability() != null)
            throw new IllegalArgumentException("The 1st Section cannot have repeatability!");
        else if(ctx.repeatability() != null) {
            questionIdOfRepeatability = ctx.repeatability().getText();
            String questionType = questionIDs.get(questionIdOfRepeatability);
            if(questionType == null) {
                throw new IllegalArgumentException("You cannot associate repeatability to a question that does not exist!");
            } else {
                if(!(questionType.equals("numeric") || questionType.equals("multiple choice") || (questionType.equals("multiple choice with input")))) {
                    throw new IllegalArgumentException("Repeatability must only be associated to a numeric or a question for selected values!");
                }
            }

        }

        visitChildren(ctx);
        return true;
    }


    @Override
    public Boolean visitQuestion(questionnaireParser.QuestionContext ctx) {
        visitChildren(ctx);
        return true;
    }

    @Override
    public Boolean visitFree_text(questionnaireParser.Free_textContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "free text");
        return true;
    }



    @Override
    public Boolean visitNumeric(questionnaireParser.NumericContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "numeric");
        return true;
    }

    @Override
    public Boolean visitSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "singe choice with input");
        return true;
    }

    @Override
    public Boolean visitSingle_choice(questionnaireParser.Single_choiceContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "single choice");
        return true;
    }

    @Override
    public Boolean visitMultiple_choice(questionnaireParser.Multiple_choiceContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "multiple choice");
        return true;
    }


    @Override
    public Boolean visitMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "multiple choice with input");
        return true;
    }

    @Override
    public Boolean visitSorting_option(questionnaireParser.Sorting_optionContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "sorting");
        return true;
    }

    @Override
    public Boolean visitScaling_option(questionnaireParser.Scaling_optionContext ctx) {
        String questionID = ctx.numeric_id().getText();
        ensureQuestionHasDifferentID(questionID, "scaling");
        return true;
    }

    private void ensureQuestionHasDifferentID(String questionID, String type) {
        if(questionIDs.get(questionID) == null)
            questionIDs.put(questionID, type);
        else
            throw new IllegalArgumentException("All questions must have a different ID!");
    }
}
