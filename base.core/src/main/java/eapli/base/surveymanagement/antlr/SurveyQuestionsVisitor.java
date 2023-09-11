package eapli.base.surveymanagement.antlr;

import java.util.*;

/**
 * @author Marta Ribeiro 1201592
 */
public class SurveyQuestionsVisitor extends questionnaireBaseVisitor{

    private final Map<String, List<List<String>>> questionsAnswers;

    public SurveyQuestionsVisitor(Map<String, List<List<String>>> questionsAnswers){
        this.questionsAnswers=questionsAnswers;
    }

    @Override
    public Boolean visitFree_text(questionnaireParser.Free_textContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a free text question. Therefore, there are no statistical analysis available.\n");
        return true;
    }

    @Override
    public Boolean visitNumeric(questionnaireParser.NumericContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a numeric question. Therefore, there are no statistical analysis available.\n");
        return true;
    }

    @Override
    public Boolean visitSingle_choice_with_input(questionnaireParser.Single_choice_with_inputContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a single choice with input question. Therefore, there are no statistical analysis available.\n");
        return true;
    }

    @Override
    public Boolean visitSingle_choice(questionnaireParser.Single_choiceContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a single choice question.");
        List<List<String>> answers = questionsAnswers.get(ctx.numeric_id().getText());
        Map<Integer, Integer> responses = new HashMap<>();
        int optionsNumber = ctx.option().size();
        for (int i = 0; i < optionsNumber; i++) {
            responses.put(optionsNumber,0);
        }
        Integer response;
        for (List<String> answer:
             answers) {
            response = Integer.valueOf(answer.get(0));
            if (!responses.containsKey(response)){
                responses.put(response,1);
            } else {
                responses.replace(response,responses.get(response)+1);
            }
        }
        int totalAnswers = answers.size();
        for (Map.Entry<Integer, Integer> entry :
                responses.entrySet()) {
            System.out.print("Option " + entry.getKey() + " = " + (double)((entry.getValue()*100/totalAnswers)* 10d)/10d + "%, ");
        }
        System.out.println("\n");
        return true;
    }

    @Override
    public Boolean visitMultiple_choice(questionnaireParser.Multiple_choiceContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a multiple choice question.");
        List<List<String>> answers = questionsAnswers.get(ctx.numeric_id().getText());
        System.out.println("Individual analysis of the responses:");
        Map<Integer, Integer> responses = new HashMap<>();
        int optionsNumber = ctx.option().size();
        for (int i = 0; i < optionsNumber; i++) {
            responses.put(optionsNumber,0);
        }
        for (List<String> answer:
                answers) {
            for (String oneOption:
                    answer) {
                if (!responses.containsKey(Integer.valueOf(oneOption))) {
                    responses.put(Integer.valueOf(oneOption), 1);
                } else {
                    responses.replace(Integer.valueOf(oneOption), responses.get(Integer.valueOf(oneOption)) + 1);
                }
            }
        }
        int totalAnswersIndividual = 0;
        for (List<String> answersGroup:
             answers) {
            for (String ignored :
                 answersGroup) {
                totalAnswersIndividual++;
            }
        }
        for (Map.Entry<Integer, Integer> entry :
                responses.entrySet()) {
            System.out.print("Option " + entry.getKey() + " = " + (double)((entry.getValue()*100/totalAnswersIndividual)* 10d)/10d + "%, ");
        }
        System.out.println("\nGroup analysis of the responses:");
        Map<String, Integer> responsesGroup = new HashMap<>();
        StringBuilder group = new StringBuilder();
        int i=0;
        for (List<String> answerGroup:
                questionsAnswers.get(questionID)) {
            for (String optionOfGroup:
                    answerGroup) {
                if (i==0) {
                    group.append(optionOfGroup);
                } else {
                    group.append(" + ").append(optionOfGroup);
                }
                i++;
            }
            if (!responsesGroup.containsKey(group.toString())){
                responsesGroup.put(group.toString(),1);
            } else {
                responsesGroup.replace(group.toString(),responsesGroup.get(group.toString())+1);
            }
            group = new StringBuilder();
            i=0;
        }
        int totalAnswers = answers.size();
        for (Map.Entry<String, Integer> entry :
                responsesGroup.entrySet()) {
            System.out.print("Option " + entry.getKey() + " = " + (double)((entry.getValue()*100/totalAnswers)* 10d)/10d + "%, ");
        }
        System.out.println("Other Possible Combinations = 0%\n");
        return true;
    }


    @Override
    public Boolean visitMultiple_choice_with_input(questionnaireParser.Multiple_choice_with_inputContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a multiple choice with input question. Therefore, there are no statistical analysis available.\n");
        return true;
    }

    @Override
    public Boolean visitSorting_option(questionnaireParser.Sorting_optionContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a sorting question.");
        List<List<String>> answers = questionsAnswers.get(ctx.numeric_id().getText());
        List<List<String>> answersSplited = new ArrayList<>();
        String[] toAdd;
        List<String> toAddList;
        String answerWithCommas;
        for (List<String> answer:
             answers) {
            answerWithCommas = answer.get(0);
            toAdd = answerWithCommas.split(", ");
            toAddList = Arrays.asList(toAdd);
            answersSplited.add(toAddList);
        }
        Map<Integer, Map<Integer, Integer>> sortingResponses = new HashMap<>();
        int optionsNumber = ctx.option().size();
        int index=1;
        for (List<String> answerSplited:
             answersSplited) {
            for (String answer:
                 answerSplited) {
                if (!sortingResponses.containsKey(index)){
                    sortingResponses.put(index, new HashMap<>());
                    if (!sortingResponses.get(index).containsKey(Integer.valueOf(answer))){
                        sortingResponses.get(index).put(Integer.valueOf(answer),1);
                    } else {
                        sortingResponses.get(index).replace(Integer.valueOf(answer),sortingResponses.get(index).get(Integer.valueOf(answer))+1);
                    }
                } else {
                    if (!sortingResponses.get(index).containsKey(Integer.valueOf(answer))){
                        sortingResponses.get(index).put(Integer.valueOf(answer),1);
                    } else {
                        sortingResponses.get(index).replace(Integer.valueOf(answer),sortingResponses.get(index).get(Integer.valueOf(answer))+1);
                    }
                }
                index++;
            }
            index=1;
        }
        int totalAnswers = answers.size();
        for (Map.Entry<Integer, Map<Integer, Integer>> answersSort: //1 pergunta -> x escalas -> y pessoas escolheram cada escala
                sortingResponses.entrySet()) {
            System.out.print("Place " + answersSort.getKey() + " = ");
            for (Map.Entry<Integer, Integer> response:
                    answersSort.getValue().entrySet()) {
                System.out.print(response.getKey() + " (" + (double)((response.getValue()*100/totalAnswers)* 10d)/10d + "%) ");
            }
            System.out.println();
        }
        System.out.println("\n");
        return true;
    }

    @Override
    public Boolean visitScaling_option(questionnaireParser.Scaling_optionContext ctx) {
        String questionID = ctx.numeric_id().getText();
        System.out.println("Question " + questionID + " is a scaling question.");
        List<List<String>> answers = questionsAnswers.get(ctx.numeric_id().getText()); //index scaling option
        Map<Integer, Map<Integer, Integer>> responses = new HashMap<>();
        int affirmationsNumber = ctx.option().size();
        int scaleLevelsNumber = Arrays.asList(ctx.frase().getText().split(", ")).size();
        for (int i = 0; i < affirmationsNumber; i++) {
            responses.put(i+1,new HashMap<>());
            for (int j = 0; j < scaleLevelsNumber; j++) {
                responses.get(i+1).put(j+1,0);
            }
        }
        int i=1;
        int j;
        int stringNum = 0;
        for (List<String> clientAnswers:
             answers) {
            for (String answer:
                 clientAnswers) {
                j=1;
                for (String level:
                        ctx.frase().getText().split(", ")) {
                    if (answer.equalsIgnoreCase(level)){
                        stringNum = j;
                    }
                    j++;
                }
                if (!responses.containsKey(i)){
                    responses.put(i,new HashMap<>());
                    if (!responses.get(i).containsKey(stringNum)){
                        responses.get(i).put(stringNum,1);
                    } else {
                        responses.get(i).replace(stringNum,responses.get(i).get(stringNum)+1);
                    }
                } else {
                    if (!responses.get(i).containsKey(stringNum)){
                        responses.get(i).put(stringNum,1);
                    } else {
                        responses.get(i).replace(stringNum,responses.get(i).get(stringNum)+1);
                    }
                }
                i++;
            }
            i=1;
        }
        int totalAnswers = answers.size();
        for (Map.Entry<Integer, Map<Integer, Integer>> answersScale: //1 pergunta -> x escalas -> y pessoas escolheram cada escala
             responses.entrySet()) {
            System.out.print("Phrase " + answersScale.getKey() + " = ");
            for (Map.Entry<Integer, Integer> scalingNumbers:
                 answersScale.getValue().entrySet()) {
                System.out.print("Scale Level " + scalingNumbers.getKey() + " (" + (double)((scalingNumbers.getValue()*100/totalAnswers)* 10d)/10d + "%) , ");
            }
            System.out.println();
        }
        System.out.println();
        return true;
    }

}
