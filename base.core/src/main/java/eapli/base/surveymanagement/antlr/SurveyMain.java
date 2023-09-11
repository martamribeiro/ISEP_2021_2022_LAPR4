package eapli.base.surveymanagement.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class SurveyMain {
    public static void main(String[] args) throws IOException {
        parseWithVisitor("base.core/src/main/java/eapli/base/surveymanagement/antlr/surveys/Jackets.txt");
    }


    public static boolean parseWithVisitor(String filePath) {
        try {
            questionnaireLexer lexer = new questionnaireLexer(CharStreams.fromFileName(filePath));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            questionnaireParser parser = new questionnaireParser(tokens);
            ParseTree tree = parser.survey();
            SurveyVisitorSurvey eval = new SurveyVisitorSurvey();
            eval.visit(tree);
            return true;
        } catch(IOException e) {
            System.out.println("Make sure the file has the correct path");
            return false;
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return false;
        }
    }
}


