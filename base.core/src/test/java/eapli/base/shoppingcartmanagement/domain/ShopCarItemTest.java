package eapli.base.shoppingcartmanagement.domain;

import eapli.base.productmanagement.domain.*;
import eapli.framework.general.domain.model.Money;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopCarItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureShopCartItemHasProduct() {
        new ShopCarItem(3, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureShopCartItemHasQuantityGreater0() {
        new ShopCarItem(-1, new Product(Code.valueOf("aaaa.12345"), Barcode.valueOf("123456789012"), ShortDescription.valueOf("this is a short description"), ExtendedDescription.valueOf("this is a very very very very very extended description"), Money.euros(2.0), Product.Status.AVAILABLE,Weight.valueOf(2.0),Volume.valueOf(3.0),Money.euros(3.0),new ProductCategory(AlphaNumericCode.valueOf("22abc"),CategoryDescription.valueOf("this is a catory description"))));
    }

}