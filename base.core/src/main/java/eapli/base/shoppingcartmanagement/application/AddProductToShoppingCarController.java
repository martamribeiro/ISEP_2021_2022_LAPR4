package eapli.base.shoppingcartmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AddProductToShoppingCarController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AddProductToShoppingCarService service = new AddProductToShoppingCarService();

    public void allProducts() {
        service.allProducts();
    }

    public boolean findByUniqueInternalCode(String productUniqueInternalCode) {
        return service.findByUniqueInternalCode(productUniqueInternalCode);
    }

    public boolean addProductShoppingCart(final int quantity, final String uniqueInternalCode){
        service.addProductToShoppingCarService(uniqueInternalCode,quantity, authz.session().get().authenticatedUser().email().toString());
        return false;
    }



}
