package eapli.base.app.server.order.requests;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.shoppingcartmanagement.application.OrderSrvController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.utils.MessageUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

public class SaveAnswersRequest extends OrderServerRequest {

    public SaveAnswersRequest(final OrderSrvController ctrl,
                              final byte request,
                              final ObjectOutputStream sOutObject,
                              final DataInputStream sIn,
                              final DataOutputStream sOut,
                              final byte[] clientMessageUS,
                              final ObjectInputStream sInObject) {
        super(ctrl, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }

    @Override
    public void execute() {
        try {
            Map<String, List<String>> answers = (Map<String, List<String>>) sInObject.readObject();
            QuestionnaireDTO surveyDTO = (QuestionnaireDTO) sInObject.readObject();

            //to obtain the email from the current client
            String email = "";
            clientMessageUS = new byte[4];
            MessageUtils.readMessage(clientMessageUS, sIn);
            if(clientMessageUS[1] == 15) {
                email = MessageUtils.getDataFromMessage(clientMessageUS, sIn);
            }

            this.orderSrvController.saveQuestionnaireAnswers(answers, surveyDTO, email);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
