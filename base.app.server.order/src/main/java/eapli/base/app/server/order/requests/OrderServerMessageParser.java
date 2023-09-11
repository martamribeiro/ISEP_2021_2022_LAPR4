package eapli.base.app.server.order.requests;

import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.*;

public class OrderServerMessageParser {

    private final OrderSrvController orderSrvController;

    public OrderServerMessageParser(final OrderSrvController orderSrvController) {
        this.orderSrvController = orderSrvController;
    }

    public OrderSrvController getOrderSrvController() {
        return orderSrvController;
    }

    public OrderServerRequest parse(final byte messageRequest,
                                    final ObjectOutputStream sOutObject,
                                    final DataInputStream sIn,
                                    final DataOutputStream sOut,
                                    final byte[] clientMessageUS,
                                    final ObjectInputStream sInObject) throws IOException {

        // TODO Do NOT choose request 15 as it is a subrequest of request 14!

        OrderServerRequest request = null;

        if(messageRequest == 4) {
            request = new GetProductsCatalogRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }
        if(messageRequest == 3) {
            request = new VerifyIfProductExistsRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }
        if(messageRequest == 5) {
            request = new AddProductToShoppingCartRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 12){
            request = new GetQuestionnairesRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 13){
            request = new GetClientOpenOrdersRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 14) {
            request = new SaveAnswersRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(messageRequest == 16) {
            request = new VerifyIfClientHasAnsweredRequest(orderSrvController, messageRequest, sOutObject, sIn, sOut, clientMessageUS, sInObject);
        }

        if(request == null)
            throw new UnsupportedOperationException("The request " + messageRequest + " is not valid - unable to parse.");

        return request;
    }
}
