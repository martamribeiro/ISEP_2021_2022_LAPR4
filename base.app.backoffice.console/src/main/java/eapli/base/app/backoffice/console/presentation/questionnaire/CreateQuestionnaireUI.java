package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.surveymanagement.application.CreateQuestionnaireController;
import eapli.base.surveymanagement.domain.Criteria;
import eapli.base.surveymanagement.domain.TheRule;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class CreateQuestionnaireUI extends AbstractUI {
    private final CreateQuestionnaireController controller = new CreateQuestionnaireController();
    private final String SECTION_OBLIGATORINESS = "Section Obligatoriness: ";
    private final String SECTION_REPEATABILITY = "Repeatability: Q";
    private final String QUESTION_TYPE = "Type: ";
    private final String SCALING_OPTION = "Scale: ";
    private final String WITH_INPUT = " with input";
    private final String DECIMALS_ARE_ALLOWED = "Decimal numbers are allowed!";
    private final String VALID_ANSWER_MESSAGE = "Please enter a valid answer.";
    private final String INTERROGATION_POINT = "?";
    private final String LEFT_PARETENSIS = "(";
    private final String RIGHT_PARETENSIS = ")";
    private String option;
    private String obligatoriness;
    private String questionType;
    private boolean isValidYesOrNo = false;
    private boolean isValidObligatoriness = false;
    private boolean isValidQuestionType = false;
    private boolean isValidNumberOfOptions = false;
    private int j=0;
    private  int m=0;
    private int z=0;
    private int a=0;
    private int numOfChoices;
    private final Scanner input = new Scanner(System.in);
    @Override
    protected boolean doShow() {
        boolean isValidNumSections = false, isValidRepeatability = false, isValidNumQuestions = false, isValidQuestionnaire = false;;
        int q=1, k=0, n=0, numSections, sectionID, numRepeats, numQuestionsPerSection, questionID, numOfChoicesOption, numOfSortingOptions, numOfScaleOptions, c=1;
        List<String> welcomeMessage = new ArrayList<>();
        HashMap<String, String> sectionsAndQuestions = new HashMap<>();
        String questionnaireID, questionnaireTitle, title, welcomeMessageOption, welcomeMessageNewLine, sectionHeader, sectionTitle, sectionMessageOption, sectionMessageNewLine,
                sectionObligatorinessOption, condition, repetability, questionHeader, questionText, questionObligatorinessOption, questionMessageOption,
                questionMessageNewLine, questionObligatorinessHeader, obligatorinessContent, questionTypeOption, questionInputChoice, questionTypeInput, questionTypeOptionDescription,
                questionSortingOptionDescription, questionScale, questionScalingOptionDescription, areDecimalsAllowed, questionFinalMessageOption, questionFinalMessage,
                filePath, finalMessage, questionTypeWithInputContent;
        StringBuilder welcomeMessageParam = new StringBuilder();
        StringBuilder questionnaireContent = new StringBuilder();

        questionnaireID = Console.readLine("What is the questionnaire ID?");

        questionnaireTitle = Console.readLine("What is the questionnaire title?");

        title = questionnaireID + " " + questionnaireTitle + "\n";

        filePath = controller.createQuestionnaireTextFile(questionnaireTitle);

        controller.writeQuestionnaireTextFile(title, filePath);

        System.out.println("Do you want to add an welcome message?");
        welcomeMessageOption = yesOrNo();

        if(welcomeMessageOption.equalsIgnoreCase("Yes") || welcomeMessageOption.equalsIgnoreCase("Y")){
            System.out.println("Message: ");
            while (input.hasNextLine()) {
                welcomeMessageNewLine = input.nextLine();
                if (welcomeMessageNewLine.isEmpty()) {
                    break;
                }
                welcomeMessage.add(welcomeMessageNewLine);
            }

            int index = welcomeMessage.size();

            for(String welMes : welcomeMessage){
                if(c == index) {
                    controller.writeQuestionnaireTextFile(welMes + "\n\n", filePath);
                }else{
                    controller.writeQuestionnaireTextFile(welMes + "\n", filePath);
                }
                c++;
                welcomeMessageParam.append(welMes);
            }
        }else{
            controller.writeQuestionnaireTextFile("\n", filePath);
        }

        System.out.println("How many sections do you want to have?");
        do {
            if(k > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            numSections = Console.readInteger("Number of Sections");

            if (numSections >= 1){
                isValidNumSections = true;
            }

            k++;
        }while (!isValidNumSections);

        for(int i=0; i<numSections; i++){
            int r=0;
            List<String> sectionMessage = new ArrayList<>();

            sectionID = i+1;

            sectionTitle = Console.readLine("Enter the section number " + (i+1) + " title.");

            sectionHeader = sectionID + ". " + sectionTitle + "\n";

            controller.writeQuestionnaireTextFile(sectionHeader, filePath);
            questionnaireContent.append(sectionHeader);

            System.out.println("Do you want to add a message to this section?");
            sectionMessageOption = yesOrNo();

            if(sectionMessageOption.equalsIgnoreCase("Yes") || sectionMessageOption.equalsIgnoreCase("Y")){
                System.out.println("Message: ");
                while (input.hasNextLine()) {
                    sectionMessageNewLine = input.nextLine();
                    if (sectionMessageNewLine.isEmpty()) {
                        break;
                    }

                    sectionMessage.add(sectionMessageNewLine);
                }

                for(String secMes : sectionMessage){
                    controller.writeQuestionnaireTextFile(secMes + "\n", filePath);
                    questionnaireContent.append(secMes);
                }
            }

            System.out.println("What is the obligatoriness of this section? (Mandatory | Optional | Condition Dependent)");
            sectionObligatorinessOption = defineObligatoriness();

            if(sectionObligatorinessOption.equalsIgnoreCase("Condition Dependent")){
                condition = Console.readLine("What is the condition?");
                //Escrever no ficheiro .txt SECTION_OBLIGATORINESS + obligatoriness + ":" + condition
                obligatorinessContent = SECTION_OBLIGATORINESS + (sectionObligatorinessOption + ":" + condition).toLowerCase() + "\n";
                controller.writeQuestionnaireTextFile(obligatorinessContent, filePath);
                questionnaireContent.append(obligatorinessContent);
            }else {
                //Escrever no ficheiro .txt SECTION_OBLIGATORINESS + obligatoriness
                obligatorinessContent = SECTION_OBLIGATORINESS + sectionObligatorinessOption.toLowerCase() + "\n";
                controller.writeQuestionnaireTextFile(obligatorinessContent, filePath);
                questionnaireContent.append(obligatorinessContent);
            }

            System.out.println("Is this section repeatable?");
            repetability = yesOrNo();

            if(repetability.equalsIgnoreCase("Yes") || repetability.equalsIgnoreCase("Y")){
                System.out.println("How many times can it be repeated?");
                do{
                    if(n > 0){
                        System.out.println(VALID_ANSWER_MESSAGE);
                    }

                    numRepeats = Console.readInteger("Repeatability");

                    if(numRepeats >= 1){
                        isValidRepeatability = true;
                    }

                    n++;
                }while(!isValidRepeatability);

                //Escrever no ficheiro .txt SECTION_REPEATABILITY + numRepeats
                String repeatabilityContent = SECTION_REPEATABILITY + numRepeats + "\n";
                controller.writeQuestionnaireTextFile(repeatabilityContent, filePath);
                questionnaireContent.append(repeatabilityContent);
            }

            System.out.println("How many questions do you want this section to have?");
            do{
                if(r > 0){
                    System.out.println(VALID_ANSWER_MESSAGE);
                }

                numQuestionsPerSection = Console.readInteger("(You need, at least, one)");

                if(numQuestionsPerSection >= 1){
                    isValidNumQuestions = true;
                }

                r++;
            }while(!isValidNumQuestions);

            for(int l=0; l<numQuestionsPerSection; l++){
                List<String> questionMessage = new ArrayList<>();

                questionID = q;

                questionText = Console.readLine("What is the question number " + (q) +" text?");

                sectionsAndQuestions.put(sectionTitle + (l+1), questionText);

                System.out.println("What is the obligatoriness of this question?");
                questionObligatorinessOption = defineObligatoriness();

                if(questionObligatorinessOption.equalsIgnoreCase("Condition Dependent")){
                    System.out.println("What is the condition?");
                    condition = Console.readLine("");
                    questionObligatorinessHeader = (sectionObligatorinessOption + ":" + condition).toLowerCase();
                }else {
                    questionObligatorinessHeader = sectionObligatorinessOption.toLowerCase();
                }

                if(questionText.contains("?")){
                    questionHeader = questionID + ". " + questionText + LEFT_PARETENSIS + questionObligatorinessHeader + RIGHT_PARETENSIS + "\n";
                }else{
                    questionHeader = questionID + ". " + questionText + INTERROGATION_POINT + LEFT_PARETENSIS + questionObligatorinessHeader + RIGHT_PARETENSIS + "\n";
                }

                controller.writeQuestionnaireTextFile(questionHeader, filePath);
                questionnaireContent.append(questionHeader);

                System.out.println("Do you want to add a message to this question?");
                questionMessageOption = yesOrNo();

                if(questionMessageOption.equalsIgnoreCase("Yes") || questionMessageOption.equalsIgnoreCase("Y")){
                    System.out.println("Message: ");
                    while (input.hasNextLine()) {
                        questionMessageNewLine = input.nextLine();
                        if (questionMessageNewLine.isEmpty()) {
                            break;
                        }

                        questionMessage.add(questionMessageNewLine);
                    }

                    for(String queMes : questionMessage){
                        controller.writeQuestionnaireTextFile(queMes + "\n", filePath);
                        questionnaireContent.append(queMes);
                    }
                }

                System.out.println("What is the question type?");
                questionTypeOption = defineQuestionType();

                if(questionTypeOption.equalsIgnoreCase("Single Choice") || questionTypeOption.equalsIgnoreCase("Multiple Choice")){
                    numOfChoicesOption = defineNumberOfOptions();
                    System.out.println("Will you like to introduce the input?");
                    questionInputChoice = yesOrNo();

                    if(questionInputChoice.equalsIgnoreCase("Yes") || questionInputChoice.equalsIgnoreCase("Y")){
                        questionTypeWithInputContent = QUESTION_TYPE + questionTypeOption + WITH_INPUT + "\n";
                        controller.writeQuestionnaireTextFile(questionTypeWithInputContent , filePath);
                        for(int h=0; h<numOfChoicesOption; h++){
                            questionTypeInput = Console.readLine((h+1) + " - Option description: ");
                            String questionTypeWithInputOptionsContent = writeOptions(h+1, questionTypeInput);
                            controller.writeQuestionnaireTextFile(questionTypeWithInputOptionsContent, filePath);
                            questionnaireContent.append(questionTypeWithInputOptionsContent);
                        }
                    }else{
                        questionTypeWithInputContent = QUESTION_TYPE + questionTypeOption + "\n";
                        controller.writeQuestionnaireTextFile(questionTypeWithInputContent , filePath);
                        for(int h=0; h<numOfChoicesOption; h++){
                            questionTypeOptionDescription = Console.readLine((h+1) + " - Option description: ");
                            String questionTypeOptionsContent = writeOptions(h+1, questionTypeOptionDescription);
                            controller.writeQuestionnaireTextFile(questionTypeOptionsContent, filePath);
                            questionnaireContent.append(questionTypeOptionsContent);
                        }
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Sorting Option")){
                    String questionTypeSortingContent = QUESTION_TYPE + questionTypeOption + "\n";
                    controller.writeQuestionnaireTextFile(questionTypeSortingContent , filePath);
                    questionnaireContent.append(questionTypeSortingContent);

                    numOfSortingOptions = defineNumberOfOptions();

                    for(int e=0; e<numOfSortingOptions; e++){
                        questionSortingOptionDescription = Console.readLine((e+1) + "- Option description: ");
                        String questionTypeSortingOptionsContent = writeOptions(e+1, questionSortingOptionDescription);
                        controller.writeQuestionnaireTextFile(questionTypeSortingOptionsContent, filePath);
                        questionnaireContent.append(questionTypeSortingOptionsContent);
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Scaling Option")){
                    String questionTypeScalingContent = QUESTION_TYPE + questionTypeOption + "\n";
                    controller.writeQuestionnaireTextFile(questionTypeScalingContent , filePath);
                    questionnaireContent.append(questionTypeScalingContent);

                    questionScale = Console.readLine("What is the scale?");

                    String questionTypeScalingScaleContent = SCALING_OPTION + questionScale + "\n";
                    controller.writeQuestionnaireTextFile(questionTypeScalingScaleContent, filePath);
                    questionnaireContent.append(questionTypeScalingScaleContent);

                    numOfScaleOptions = defineNumberOfOptions();

                    for(int u=0; u<numOfScaleOptions; u++){
                        questionScalingOptionDescription = Console.readLine("Option description: ");
                        String questionScaleOptionsContent = writeOptions(u+1, questionScalingOptionDescription);
                        controller.writeQuestionnaireTextFile(questionScaleOptionsContent, filePath);
                        questionnaireContent.append(questionScaleOptionsContent);
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Numeric")){
                    System.out.println("Are decimals allowed?");
                    areDecimalsAllowed = yesOrNo();

                    if(areDecimalsAllowed.equalsIgnoreCase("Yes") || areDecimalsAllowed.equalsIgnoreCase("Y")){
                        String questionTypeNumericWithDecimalsContent = QUESTION_TYPE + questionTypeOption + " " + LEFT_PARETENSIS +
                                DECIMALS_ARE_ALLOWED + RIGHT_PARETENSIS + "\n";
                        controller.writeQuestionnaireTextFile(questionTypeNumericWithDecimalsContent, filePath);
                        questionnaireContent.append(questionTypeNumericWithDecimalsContent);
                    }else{
                        String questionTypeNumericWithoutDecimalsContent = QUESTION_TYPE + questionTypeOption + "\n";
                        controller.writeQuestionnaireTextFile(questionTypeNumericWithoutDecimalsContent, filePath);
                        questionnaireContent.append(questionTypeNumericWithoutDecimalsContent);
                    }
                }else if(questionTypeOption.equalsIgnoreCase("Free Text")){
                    String questionTypeFreeTextContent = QUESTION_TYPE + questionTypeOption + "\n";
                    controller.writeQuestionnaireTextFile(questionTypeFreeTextContent, filePath);
                    questionnaireContent.append(questionTypeFreeTextContent);
                }

                /*System.out.println("Would you like to enter a final message for this question?");

                questionFinalMessageOption = yesOrNo();

                if(questionFinalMessageOption.equalsIgnoreCase("Yes") || questionFinalMessageOption.equalsIgnoreCase("Y")){
                    questionFinalMessage = Console.readLine("Enter the message:");
                    controller.writeQuestionnaireTextFile(questionFinalMessage + "\n", filePath);
                }*/

                controller.writeQuestionnaireTextFile("\n", filePath);
                questionnaireContent.append("\n");

                q++;
            }

            controller.writeQuestionnaireTextFile("\n", filePath);
            questionnaireContent.append("\n");
        }

        finalMessage = Console.readLine("Final survey message:");

        controller.writeQuestionnaireTextFile("\n" + finalMessage, filePath);

        //>>>>>>>>>> ADICIONAR RULES DA TARGET AUDIENCE

        Set<TheRule> rules = new HashSet<>();
        TheRule ruleToAdd;
        Criteria criteria = null;
        boolean cont = true, cont2=true;
        String opcao, opcao2, gender, signal, productCode, productCategoryCode;
        int age;
        List<Criteria> ruleCriteria = new ArrayList<>();

        int j;

        opcao = Console.readLine("Do you want to insert rules for the target audience?\n Yes or no?\n");
        if(opcao.equalsIgnoreCase("no")) {
            cont=false;
        }

        while (cont) {

            ruleToAdd = null;

            while (cont2) {

                criteria = null;

                j = 1;
                System.out.println("\n>> CRITERIA:");
                for (Criteria options : Criteria.values()) {
                    System.out.printf("%d. %s%n", j, options.name());
                    j++;
                }

                int optionCriteria = Console.readInteger("Select the option:") - 1;

                if (optionCriteria >= j || optionCriteria < 0) {
                    throw new UnsupportedOperationException("Invalid Criteria Option");
                }

                criteria = Criteria.values()[optionCriteria];

                if (criteria.equals(Criteria.GENDER)) {
                    j=1;
                    System.out.println("Genders:");
                    for (Criteria.Gender options : Criteria.Gender.values()) {
                        System.out.printf("%d. %s%n", j, options.name());
                        j++;
                    }
                    int optionGender = Console.readInteger("Select the option:") - 1;
                    if (optionGender >= j || optionGender < 0) {
                        throw new UnsupportedOperationException("Invalid Gender Option");
                    }
                    criteria.gender = Criteria.Gender.values()[optionGender];
                    ruleCriteria.add(criteria);
                } else if (criteria.equals(Criteria.AGE)) {
                    j=1;
                    System.out.println("Signal:");
                    for (Criteria.Signal options : Criteria.Signal.values()) {
                        System.out.printf("%d. %s%n", j, options.name());
                        j++;
                    }
                    int optionSignal = Console.readInteger("Select the option:") - 1;
                    if (optionSignal >= j || optionSignal < 0) {
                        throw new UnsupportedOperationException("Invalid Signal Option");
                    }
                    criteria.signal = Criteria.Signal.values()[optionSignal];
                    try{
                        criteria.age = Console.readLong("Age of reference:");
                    } catch (Exception e){
                        throw new UnsupportedOperationException("Age format is incorrect");
                    }
                    ruleCriteria.add(criteria);
                } else if (criteria.equals(Criteria.PRODUCT_BOUGHT)) {
                    productCode = Console.readLine("Product's Unique Internal Code:");
                    if (controller.productExists(productCode)) { //se existe produto com esse id
                        criteria.productCode = productCode;
                    } else {
                        throw new UnsupportedOperationException("Product with given code does not exist");
                    }
                    ruleCriteria.add(criteria);
                } else if (criteria.equals(Criteria.PRODUCT_CATEGORY_BOUGHT)) {
                    productCategoryCode = Console.readLine("Product Category's Alphanumeric Code:");
                    if (controller.productCategoryExists(productCategoryCode)) { //se existe categoria com esse id
                        criteria.productCategoryCode = productCategoryCode;
                    } else {
                        throw new UnsupportedOperationException("Product category with given code does not exist");
                    }
                    ruleCriteria.add(criteria);
                }

                opcao2 = Console.readLine("Do you want to add another criteria for the current rule?\n Yes or no?\n");
                if (opcao2.equalsIgnoreCase("no")) {
                    cont2 = false;
                }

            }

            ruleToAdd = new TheRule(ruleCriteria);
            rules.add(ruleToAdd);

            opcao = Console.readLine("Do you want to add another rule for the target audience?\n Yes or no?\n");
            if (opcao.equalsIgnoreCase("no")) {
                cont = false;
            }

        }

        System.out.printf("Questionnaire %s successfully created!\n\n", questionnaireTitle);

        System.out.println("The system will now validate if the questionnaire is valid...");

        isValidQuestionnaire = controller.isQuestionnaireValid(filePath);

        if(isValidQuestionnaire){
            System.out.println("This questionnaire is valid! It will now be saved.");
            controller.registerQuestionnaire(questionnaireID, questionnaireTitle, welcomeMessageParam.toString(), questionnaireContent.toString(), finalMessage, rules);
        }else{
            System.out.println("This questionnaire is not valid, thus it will not be saved.");
        }

        System.out.println("All done.");

        return false;
    }

    @Override
    public String headline() {
        return "Create Questionnaire.";
    }

    private String yesOrNo(){
        do{
            if(j > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            option = Console.readLine("Yes (Y) | No (N)");

            if(option.equalsIgnoreCase("Yes") || option.equalsIgnoreCase("Y") || option.equalsIgnoreCase("No") || option.equalsIgnoreCase("N")){
                isValidYesOrNo = true;
            }

            j++;
        }while(!isValidYesOrNo);

        j = 0;

        return option;
    }

    private String defineObligatoriness(){
        do{
            if(m > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            obligatoriness = Console.readLine("(Mandatory | Optional | Condition Dependent)");

            if(obligatoriness.equalsIgnoreCase("Mandatory") || obligatoriness.equalsIgnoreCase("Optional") || obligatoriness.equalsIgnoreCase("Condition Dependent")){
                isValidObligatoriness = true;
            }

            m++;
        }while(!isValidObligatoriness);

        m = 0;

        return obligatoriness;
    }

    private String defineQuestionType(){
        do{
            if(z > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            questionType = Console.readLine("(Free Text | Numeric | Single Choice | Multiple Choice | Sorting Option | Scaling Option)");

            if(questionType.equalsIgnoreCase("Free Text") || questionType.equalsIgnoreCase("Numeric") || questionType.equalsIgnoreCase("Single Choice")
                || questionType.equalsIgnoreCase("Multiple Choice") || questionType.equalsIgnoreCase("Sorting Option") || questionType.equalsIgnoreCase("Scaling Option")){
                isValidQuestionType = true;
            }

            z++;
        }while(!isValidQuestionType);

        z = 0;

        return questionType;
    }

    private Integer defineNumberOfOptions(){
        do{
            if(a > 0){
                System.out.println(VALID_ANSWER_MESSAGE);
            }

            numOfChoices = Console.readInteger("How many options would you like to have? (You need, at least, one)");

            if(numOfChoices >= 1){
                isValidNumberOfOptions = true;
            }

            a++;
        }while(!isValidNumberOfOptions);

        a = 0;

        return numOfChoices;
    }

    private String writeOptions(int numericID, String optionDescription){
        return numericID + RIGHT_PARETENSIS + optionDescription + "\n";
    }
}
