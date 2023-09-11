// Generated from D:/Faculdade/ano 2/semestre2/LAPR4/projeto_lapr4/base.core/src/main/java/eapli/base/surveymanagement/antlr\questionnaire.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link questionnaireParser}.
 */
public interface questionnaireListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#alfanumerico}.
	 * @param ctx the parse tree
	 */
	void enterAlfanumerico(questionnaireParser.AlfanumericoContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#alfanumerico}.
	 * @param ctx the parse tree
	 */
	void exitAlfanumerico(questionnaireParser.AlfanumericoContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#frase}.
	 * @param ctx the parse tree
	 */
	void enterFrase(questionnaireParser.FraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#frase}.
	 * @param ctx the parse tree
	 */
	void exitFrase(questionnaireParser.FraseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lengthTitle}
	 * labeled alternative in {@link questionnaireParser#title}.
	 * @param ctx the parse tree
	 */
	void enterLengthTitle(questionnaireParser.LengthTitleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lengthTitle}
	 * labeled alternative in {@link questionnaireParser#title}.
	 * @param ctx the parse tree
	 */
	void exitLengthTitle(questionnaireParser.LengthTitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(questionnaireParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(questionnaireParser.MessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#survey}.
	 * @param ctx the parse tree
	 */
	void enterSurvey(questionnaireParser.SurveyContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#survey}.
	 * @param ctx the parse tree
	 */
	void exitSurvey(questionnaireParser.SurveyContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#questionnaire_id}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaire_id(questionnaireParser.Questionnaire_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#questionnaire_id}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaire_id(questionnaireParser.Questionnaire_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(questionnaireParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(questionnaireParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#numeric_id}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_id(questionnaireParser.Numeric_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#numeric_id}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_id(questionnaireParser.Numeric_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#obligatoriness}.
	 * @param ctx the parse tree
	 */
	void enterObligatoriness(questionnaireParser.ObligatorinessContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#obligatoriness}.
	 * @param ctx the parse tree
	 */
	void exitObligatoriness(questionnaireParser.ObligatorinessContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#repeatability}.
	 * @param ctx the parse tree
	 */
	void enterRepeatability(questionnaireParser.RepeatabilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#repeatability}.
	 * @param ctx the parse tree
	 */
	void exitRepeatability(questionnaireParser.RepeatabilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(questionnaireParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(questionnaireParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#free_text}.
	 * @param ctx the parse tree
	 */
	void enterFree_text(questionnaireParser.Free_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#free_text}.
	 * @param ctx the parse tree
	 */
	void exitFree_text(questionnaireParser.Free_textContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#numeric}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(questionnaireParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#numeric}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(questionnaireParser.NumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#single_choice}.
	 * @param ctx the parse tree
	 */
	void enterSingle_choice(questionnaireParser.Single_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#single_choice}.
	 * @param ctx the parse tree
	 */
	void exitSingle_choice(questionnaireParser.Single_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice(questionnaireParser.Multiple_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice(questionnaireParser.Multiple_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#single_choice_with_input}.
	 * @param ctx the parse tree
	 */
	void enterSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#single_choice_with_input}.
	 * @param ctx the parse tree
	 */
	void exitSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#multiple_choice_with_input}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#multiple_choice_with_input}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#sorting_option}.
	 * @param ctx the parse tree
	 */
	void enterSorting_option(questionnaireParser.Sorting_optionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#sorting_option}.
	 * @param ctx the parse tree
	 */
	void exitSorting_option(questionnaireParser.Sorting_optionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#scaling_option}.
	 * @param ctx the parse tree
	 */
	void enterScaling_option(questionnaireParser.Scaling_optionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#scaling_option}.
	 * @param ctx the parse tree
	 */
	void exitScaling_option(questionnaireParser.Scaling_optionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(questionnaireParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(questionnaireParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link questionnaireParser#question_text}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_text(questionnaireParser.Question_textContext ctx);
	/**
	 * Exit a parse tree produced by {@link questionnaireParser#question_text}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_text(questionnaireParser.Question_textContext ctx);
}