package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Money;

import java.util.Set;

/**
 * A Product builder. Used to avoid overloading constructors or too
 * much conditional logic on the constructor.
 * @author Marta Ribeiro 1201592
 */
public class ProductBuilder implements DomainFactory<Product> {

    private Product theProduct;

    private Code uniqueInternalCode;

    private Barcode barcode;

    private ShortDescription shortDescription;

    private ExtendedDescription extendedDescription;

    private Money priceWithoutTaxes;

    private Product.Status status;

    private Weight weight;

    private Volume volume;

    private Money priceWithTaxes;

    private ProductCategory category;

    public ProductBuilder ofCategory(final ProductCategory productCategory){
        category=productCategory;
        return this;
    }

    public ProductBuilder shortlyDescriptedAs(final String shortDescription){
        return shortlyDescriptedAs(ShortDescription.valueOf(shortDescription));
    }

    public ProductBuilder shortlyDescriptedAs(final ShortDescription shortDescription){
        this.shortDescription=shortDescription;
        return this;
    }

    public ProductBuilder extendedlyDescriptedAs(final String extendedDescription){
        return shortlyDescriptedAs(ShortDescription.valueOf(extendedDescription));
    }

    public ProductBuilder extendedlyDescriptedAs(final ExtendedDescription extendedDescription){
        this.extendedDescription=extendedDescription;
        return this;
    }

    public ProductBuilder codedAs(final String uniqueInternalCode){
        return codedAs(Code.valueOf(uniqueInternalCode));
    }

    public ProductBuilder codedAs(final Code uniqueInternalCode){
        this.uniqueInternalCode=uniqueInternalCode;
        return this;
    }

    public ProductBuilder ofBarcode(final String barcode){
        return ofBarcode(Barcode.valueOf(barcode));
    }

    public ProductBuilder ofBarcode(final Barcode barcode){
        this.barcode=barcode;
        return this;
    }

    public ProductBuilder initialyPricedAs(final Money priceWithoutTaxes){
        this.priceWithoutTaxes=priceWithoutTaxes;
        return this;
    }

    public ProductBuilder statusAs(final String status){
        return statusAs(Product.Status.valueOf(status));
    }

    public ProductBuilder statusAs(final Product.Status status){
        this.status=status;
        return this;
    }

    public ProductBuilder weightAs(final Weight weight){
        this.weight=weight;
        return this;
    }

    public ProductBuilder volumeAs(final Volume volume){
        this.volume=volume;
        return this;
    }

    public ProductBuilder afterTaxesPricedAs(final Money priceWithTaxes){
        this.priceWithTaxes=priceWithTaxes;
        return this;
    }

    private Product buildOrThrow(){
        if (theProduct!=null){
            return theProduct;
        } else if (category!=null && barcode!=null && shortDescription!=null && extendedDescription!=null &&
        priceWithoutTaxes!=null && status!=null && weight!=null && volume!=null && priceWithTaxes!=null){
            theProduct = new Product(uniqueInternalCode, barcode, shortDescription, extendedDescription,
                    priceWithoutTaxes, status, weight, volume, priceWithTaxes, category);
            return theProduct;
        } else {
            throw new IllegalStateException();
        }
    }

    public ProductBuilder withTechnicalDescription(final TechnicalDescription technicalDescription){
        if (technicalDescription!=null) {
            buildOrThrow();
            theProduct.changeTechnicalDescription(technicalDescription);
        }
        return this;
    }

    public ProductBuilder withBrandName(final BrandName brandName){
        if (brandName!=null){
            buildOrThrow();
            theProduct.changeBrandName(brandName);
        }
        return this;
    }

    public ProductBuilder withReference(final Reference reference){
        if (reference!=null) {
            buildOrThrow();
            theProduct.changeReference(reference);
        }
        return this;
    }

    public ProductBuilder withProductionCode(final ProductionCode productionCode){
        if (productionCode!=null) {
            buildOrThrow();
            theProduct.changeProductionCode(productionCode);
        }
        return this;
    }

    public ProductBuilder withPhotos(final Set<Photo> photos){
        if (photos != null) {
            for (Photo photo : photos) {
                withPhoto(photo);
            }
        }
        return this;
    }

    public ProductBuilder withPhoto(final Photo photo){
        buildOrThrow();
        theProduct.addPhoto(photo);
        return this;
    }

    @Override
    public Product build() {
        final Product ret = buildOrThrow();
        theProduct = null;
        return ret;
    }

}
