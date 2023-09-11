// Generated from D:/Faculdade/ano 2/semestre2/LAPR4/projeto_lapr4/base.core/src/main/java/eapli/base/surveymanagement/antlr\questionnaire.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link questionnaireParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface questionnaireVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#alfanumerico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlfanumerico(questionnaireParser.AlfanumericoContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#frase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrase(questionnaireParser.FraseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lengthTitle}
	 * labeled alternative in {@link questionnaireParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthTitle(questionnaireParser.LengthTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(questionnaireParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#survey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSurvey(questionnaireParser.SurveyContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#questionnaire_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaire_id(questionnaireParser.Questionnaire_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(questionnaireParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#numeric_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_id(questionnaireParser.Numeric_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#obligatoriness}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObligatoriness(questionnaireParser.ObligatorinessContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#repeatability}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatability(questionnaireParser.RepeatabilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(questionnaireParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#free_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_text(questionnaireParser.Free_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(questionnaireParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#single_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_choice(questionnaireParser.Single_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#multiple_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice(questionnaireParser.Multiple_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#single_choice_with_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#multiple_choice_with_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#sorting_option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSorting_option(questionnaireParser.Sorting_optionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#scaling_option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScaling_option(questionnaireParser.Scaling_optionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(questionnaireParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link questionnaireParser#question_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_text(questionnaireParser.Question_textContext ctx);
}