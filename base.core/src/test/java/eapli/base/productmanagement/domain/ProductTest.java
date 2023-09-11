package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Money;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ProductTest {

    @Test
    public void ensureProductWithObrigatoryParameters(){
        new Product(Code.valueOf("abcd.12345"),Barcode.valueOf("123456789012"),ShortDescription.valueOf("Short description."), ExtendedDescription.valueOf("Very very very very very very very extended description."),
                Money.euros(1.5), Product.Status.AVAILABLE,Weight.valueOf(10),Volume.valueOf(5.3),Money.euros(3),new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")));
        assertTrue(true);
    }

    @Test
    public void ensureCanBuildProductWithObrigatoryParameters(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
        assertNotNull(product);
    }

    @Test
    public void ensureCanBuildProductWithAllParameters(){
        Set<Photo> photos = new HashSet<>();
        photos.add(new Photo("docs/photos/shampoo.png"));
        photos.add(new Photo("docs/photos/shampoo_on_use.jpg"));
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .withTechnicalDescription(TechnicalDescription.valueOf("this is a very technical description"))
                .withBrandName(BrandName.valueOf("the brand"))
                .withReference(Reference.valueOf("12345brand"))
                .withProductionCode(ProductionCode.valueOf("dggd.34656"))
                .withPhotos(photos)
                .build();
        assertNotNull(product);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildProductWithNullUniqueInternalCode(){
        final Product product = new ProductBuilder()
                .codedAs((String) null)
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildProductWithNullBarcode(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode((String) null)
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildProductWithNullShortDescription(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs((String) null)
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildProductWithNullExtendedDescription(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs((String) null)
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullPriceWithoutTaxes(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(null)
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void ensureCannotBuildProductWithNullStatus(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs((String) null)
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullWeight(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(null)
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullVolume(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(null)
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullPriceWithTaxes(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(null)
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullProductCategory(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(null)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildProductWithNullUniqueInternalCode2(){
        final Product product = new ProductBuilder()
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullBarcode2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullShortDescription2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullExtendedDescription2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullPriceWithoutTaxes2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullStatus2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullWeight2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullVolume2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .afterTaxesPricedAs(Money.euros(2.3))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullPriceWithTaxes2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .ofCategory(new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category")))
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildProductWithNullProductCategory2(){
        final Product product = new ProductBuilder()
                .codedAs(Code.valueOf("abcd.12345"))
                .ofBarcode(Barcode.valueOf("123456789012"))
                .shortlyDescriptedAs(ShortDescription.valueOf("short description"))
                .extendedlyDescriptedAs(ExtendedDescription.valueOf("exteeeeeeeeeeeeendeeeeed descriptioooon"))
                .initialyPricedAs(Money.euros(1))
                .statusAs("AVAILABLE")
                .weightAs(Weight.valueOf(1))
                .volumeAs(Volume.valueOf(0.05))
                .afterTaxesPricedAs(Money.euros(2.3))
                .build();
    }

}