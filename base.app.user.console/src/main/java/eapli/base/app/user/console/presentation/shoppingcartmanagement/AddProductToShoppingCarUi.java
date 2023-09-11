package eapli.base.app.user.console.presentation.shoppingcartmanagement;


import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.application.AddProductToShoppingCarController;
import eapli.base.shoppingcartmanagement.domain.ShopCarItem;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddProductToShoppingCarUi extends AbstractUI {

    private final AddProductToShoppingCarController controller = new AddProductToShoppingCarController();

    private boolean produtoValido;


    @Override
    protected boolean doShow() {


        controller.allProducts();

        String productUniqueInternalCode = null;
        do {
            productUniqueInternalCode = Console.readLine("Product Unique Internal Code");
            produtoValido =controller.findByUniqueInternalCode(productUniqueInternalCode);
            if (!produtoValido) {
                System.out.println("Não existe nenhum produto com esse Unique Internal Code!");
            }
        }while (!produtoValido);
        final int quantity = Integer.parseInt(Console.readLine("Quantity"));
        try {
            controller.addProductShoppingCart(quantity,productUniqueInternalCode);
        } catch (IllegalArgumentException e) {
            System.out.println("Something went wrong!");
        }

        return false;

    }

    @Override
    public String headline() {
        return "Add Product to Shopping Car";
    }
}
