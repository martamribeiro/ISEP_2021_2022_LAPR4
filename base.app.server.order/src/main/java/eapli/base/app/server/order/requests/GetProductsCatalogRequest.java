package eapli.base.app.server.order.requests;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.shoppingcartmanagement.application.OrderSrvController;

import java.io.*;

public class GetProductsCatalogRequest extends OrderServerRequest {

    public GetProductsCatalogRequest(final OrderSrvController ctrl,
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
            Iterable<ProductDTO> productCatalog = this.orderSrvController.allProducts();
            sOutputObject.writeObject(productCatalog);
            sOutputObject.flush();
        } catch (IOException e) {
            System.out.println("[ERROR] An error because of the ObjectOutputStream has occured");
        }
    }
}
