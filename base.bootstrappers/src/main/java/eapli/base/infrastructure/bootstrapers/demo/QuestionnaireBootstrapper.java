package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.surveymanagement.application.CreateQuestionnaireController;
import eapli.base.surveymanagement.domain.Criteria;
import eapli.base.surveymanagement.domain.TheRule;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionnaireBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionnaireBootstrapper.class);

    private final CreateQuestionnaireController controller = new CreateQuestionnaireController();

    @Override
    public boolean execute() {
        String booksContent = "1. About You\n" +
                "Section Obligatoriness: mandatory\n" +
                "1. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "2. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "\n" +
                "2. Product feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "3. Do you like our products?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Yes\n" +
                "2)No\n" +
                "3)Maybe";

        String drinksContent = "1. About you\n" +
                "Section Obligatoriness: mandatory\n" +
                "1. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "2. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "3. What is your gender?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Male\n" +
                "2)Female\n" +
                "3)Other\n" +
                "\n" +
                "\n" +
                "2. Product Feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "4. How do you rate our drinks?(mandatory)\n" +
                "Type: scaling option\n" +
                "Scale: Strongly agree, agree, neutral, disagree, strongly disagree\n" +
                "1)Our drinks are the best on the market\n" +
                "2)Our drinks are better than most on the market\n" +
                "3)Our drinks are as good as another in the market\n" +
                "4)Our drinks are worse than most on the market\n" +
                "5)Our drinks are the worst on the market";

        String sportsContent = "1. About you\n" +
                "Section Obligatoriness: mandatory\n" +
                "1. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "2. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "\n" +
                "2. Product feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "3. Would you recommend our sports products to another person?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Yes\n" +
                "2)No\n" +
                "3)Maybe";

        String serviceContent = "1. About you\n" +
                "Section Obligatoriness: mandatory\n" +
                "1. How old are you?(mandatory)\n" +
                "Type: numeric\n" +
                "\n" +
                "2. Where are you from?(mandatory)\n" +
                "Type: free text\n" +
                "\n" +
                "\n" +
                "2. Product feedback\n" +
                "Section Obligatoriness: mandatory\n" +
                "3. Would you recommend our tuna to another person?(mandatory)\n" +
                "Type: single choice\n" +
                "1)Yes\n" +
                "2)No\n" +
                "3)Maybe\n" +
                "\n" +
                "4. How do you rate our shampoos?(mandatory)\n" +
                "Type: scaling option\n" +
                "Scale: Strongly agree, agree, neutral, disagree, strongly disagree\n" +
                "1)Our shampoos are the best on the market\n" +
                "2)Our shampoos are delievered as fast as desired\n" +
                "3)Our shampoos have nice scents\n" +
                "\n" +
                "5. Which of our products have you tried?(mandatory)\n" +
                "Type: multiple choice\n" +
                "1)Lemon scent shampoo from Bobbles\n" +
                "2)Apple scent shampoo from Bobbles\n" +
                "3)Can of tuna from Freshy\n" +
                "\n" +
                "6. Which of our products do you prefer?(mandatory)\n" +
                "Type: sorting option\n" +
                "1)Lemon scent shampoo from Bobbles\n" +
                "2)Apple scent shampoo from Bobbles\n" +
                "3)Can of tuna from Freshy";

        Set<TheRule> rules = new HashSet<>();
        Set<TheRule> rules1 = new HashSet<>();
        Set<TheRule> rules2 = new HashSet<>();
        Criteria criteria1;
        Criteria criteria2;
        criteria1 = Criteria.values()[0];
        criteria2 = Criteria.values()[2];
        criteria1.gender = Criteria.Gender.values()[1];
        criteria2.productCode = "lmsp.00001";
        List<Criteria> criterias1 = new ArrayList<>();
        List<Criteria> criterias2 = new ArrayList<>();
        criterias1.add(criteria1);
        criterias2.add(criteria2);
        TheRule rule1 = new TheRule(criterias1);
        TheRule rule2 = new TheRule(criterias2);
        rules1.add(rule1);
        rules2.add(rule2);

        register("BOOKS21-22", "Books Questionnaire", "Hello, welcome to the Books Questionnaire", booksContent, "Thanks for answering the Books Questionnaire",rules);
        register("DRI21-22", "Drinks Questionnaire", "Hello, welcome to the Drinks Questionnaire", drinksContent, "Thanks for answering the Drinks Questionnaire",rules1);
        register("SPO21-22", "Sports Questionnaire", "Hello, welcome to the Sports Questionnaire", sportsContent, "Thanks for answering the Sports Questionnaire",rules2);
        register("SRV21-22", "Service Questionnaire", "Hello, welcome to the Sports Questionnaire", serviceContent, "Thanks for answering the Books Questionnaire",rules);

        return true;
    }

    public void register(final String code, final String title, final String welcomeMessage, final String questionnaireContent, final String finalMessage, final Set<TheRule> rules){
        try{
            controller.registerQuestionnaire(code, title, welcomeMessage, questionnaireContent, finalMessage, rules);
            LOGGER.debug(code);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})", code,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }
}
