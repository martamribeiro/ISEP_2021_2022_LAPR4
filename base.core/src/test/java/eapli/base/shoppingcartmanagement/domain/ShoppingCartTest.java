package eapli.base.shoppingcartmanagement.domain;

import eapli.base.clientmanagement.domain.*;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.productmanagement.domain.*;
import eapli.framework.general.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private static final Name CLIENT_NAME = Name.valueOf("Ana","de Castro Albergaria");
    private static final Email CLIENT_EMAIL = Email.valueOf("anacalbergaria@gmail.com");
    private static final VAT CLIENT_VAT = VAT.valueOf("PT999999999");
    private static final PhoneNumber CLIENT_PHONE_NUMBER = PhoneNumber.valueOf("+351918413784");
    private static final Address CLIENT_POSTAL_ADDRESS = Address.valueOf("Travessa do Outeiro 1","2","4520-463","Rio Meão","Portugal");

    private Set<Address> postalAddresses = new HashSet<>();

    private Client buildClient() {
        postalAddresses.add(CLIENT_POSTAL_ADDRESS);
        return new ClientBuilder().named(CLIENT_NAME).withEmail(CLIENT_EMAIL).withVAT(CLIENT_VAT).withPhoneNumber(CLIENT_PHONE_NUMBER).withAddresses(postalAddresses).build();
    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureShoppingCartHasClient() {
        new ShoppingCart(null);
    }

    @Test
    public void addProductToShoppingCar() {
        ShoppingCart shoppingCart = new ShoppingCart(buildClient());

        int expQuantity = 3;
        int expSize = 1;
        ShopCarItem item = new ShopCarItem(3,new Product(Code.valueOf("aaaa.12345"),Barcode.valueOf("123456789012"),ShortDescription.valueOf("this is a short description"),ExtendedDescription.valueOf("this is a very very very very very extended description"), Money.euros(2.0), Product.Status.AVAILABLE,Weight.valueOf(2.0),Volume.valueOf(3.0),Money.euros(3.0),new ProductCategory(AlphaNumericCode.valueOf("22abc"),CategoryDescription.valueOf("this is a catory description"))));

        shoppingCart.addProductToShoppingCar(item);
        assertEquals(expSize, shoppingCart.items().size());
        assertEquals(expQuantity, shoppingCart.items().get(0).quantity());
    }
}