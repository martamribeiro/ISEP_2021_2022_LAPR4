package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.utils.MessageUtils;

import java.io.*;

public class VerifyIfClientHasAnsweredRequest extends OrderServerRequest {

    public VerifyIfClientHasAnsweredRequest(final OrderSrvController orderSrvController,
                                     final byte request,
                                     final ObjectOutputStream sOutObject,
                                     final DataInputStream sIn,
                                     final DataOutputStream sOut,
                                     final byte[] clientMessageUS,
                                     final ObjectInputStream sInObject) {
        super(orderSrvController, request, sOutObject, sIn, sOut, clientMessageUS, sInObject);
    }

    @Override
    public void execute() {
        try {
            String info = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
            String[] array = info.split(" ");
            String surveyCode = array[0];
            String email = array[1];
            boolean hasNotAnswered = this.orderSrvController.verifyIfClientAnswered(email, surveyCode);
            if(hasNotAnswered) {
                MessageUtils.writeMessageWithData((byte) 16, "True", sOut);
            } else {
                MessageUtils.writeMessageWithData((byte) 16, "False", sOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
