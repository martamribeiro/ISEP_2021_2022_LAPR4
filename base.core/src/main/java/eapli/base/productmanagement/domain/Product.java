package eapli.base.productmanagement.domain;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A Product.
 *
 * @author Marta Ribeiro 1201592
 */
@Entity
public class Product implements AggregateRoot<Code>, Serializable, Comparable<Code>, DTOable<ProductDTO> {

    /**
     * Changes the technical description of the product.
     *
     * @param newTechnicalDescription new technical description
     */
    public void changeTechnicalDescription(TechnicalDescription newTechnicalDescription) {
        technicalDescription = newTechnicalDescription;
    }

    /**
     * Changes the brand name of the product.
     *
     * @param newBrandName new brand name
     */
    public void changeBrandName(BrandName newBrandName) {
        brandName = newBrandName;

    }

    /**
     * Changes the reference of the product.
     *
     * @param newReference new reference
     */
    public void changeReference(Reference newReference) {
        reference = newReference;
    }

    /**
     * Changes the production code of the product.
     *
     * @param newProductionCode new production code
     */
    public void changeProductionCode(ProductionCode newProductionCode) {
        productionCode = newProductionCode;
    }

    public BrandName brandName() {
        return this.brandName;
    }

    @Override
    public ProductDTO toDTO() {
        return new ProductDTO(uniqueInternalCode.toString(),shortDescription.toString(),priceWithoutTaxes.amount());
    }

    /**
     * Possible values for product's status.
     */
    public enum Status {
        AVAILABLE, TEMPORARILY_UNAVAILABLE, UNAVAILABLE;
    }

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "unique_internal_code"))
    private Code uniqueInternalCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits."

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "short_description"))
    private ShortDescription shortDescription;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "extended_description"))
    private ExtendedDescription extendedDescription;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "technical_description"))
    private TechnicalDescription technicalDescription; //optional

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "brand_name"))
    private BrandName brandName; //optional

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "reference"))
    private Reference reference; //optional

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "taxes_currency"))
    })
    private Money priceWithoutTaxes;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "weight_in_kilograms"))
    private Weight weight;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "volume_in_cubic_meters"))
    private Volume volume;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "no_taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "no_taxes_currency"))
    })
    private Money priceWithTaxes;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "production_code"))
    private ProductionCode productionCode; //optional
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits." OPTIONAL

    @ManyToOne
    private ProductCategory productCategory;

    @ElementCollection
    @CollectionTable(name="product_photos")
    @Column(name="photo_path")
    private Set<Photo> photos = new HashSet<>(); //optional

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "barcode"))
    private Barcode barcode;

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public void setUniqueInternalCode(Code uniqueInternalCode) {
        this.uniqueInternalCode = uniqueInternalCode;
    }

    /**
     * Constructor for Product with the minimum attributes.
     *
     * @param uniqueInternalCode the product unique internal code
     * @param barcode the product barcode
     * @param shortDescription the product short description
     * @param extendedDescription the product extended description
     * @param priceWithoutTaxes the product price without taxes
     * @param status the product status
     * @param weight the product weight
     * @param volume the product volume
     * @param priceWithTaxes the product price with taxes
     * @param productCategory the product category
     */
    public Product(final Code uniqueInternalCode, final Barcode barcode, final ShortDescription shortDescription, final ExtendedDescription extendedDescription,
                   final Money priceWithoutTaxes, final Status status, final Weight weight, final Volume volume,
                   final Money priceWithTaxes, final ProductCategory productCategory){
        Preconditions.noneNull(uniqueInternalCode, shortDescription,extendedDescription,priceWithoutTaxes,status,weight,volume,priceWithTaxes,productCategory);
        this.uniqueInternalCode=uniqueInternalCode;
        this.barcode=barcode;
        this.shortDescription=shortDescription;
        this.extendedDescription=extendedDescription;
        this.priceWithoutTaxes=priceWithoutTaxes;
        this.status=status;
        this.weight=weight;
        this.volume=volume;
        this.priceWithTaxes=priceWithTaxes;
        this.productCategory=productCategory;
    }

    protected Product(){

    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        final var that = (Product) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    /**
     * @return the product category of this product
     */
    public ProductCategory getProductCategory(){
        return productCategory;
    }

    /**
     * @return the product identity (unique internal code)
     */
    @Override
    public Code identity() {
        return uniqueInternalCode;
    }

    /**
     * @return the product unique internal code
     */
    public Code getUniqueInternalCode() {
        return uniqueInternalCode;
    }

    /**
     * @return the product short description
     */
    public ShortDescription getShortDescription() {
        return shortDescription;
    }

    /**
     * @return the product extended description
     */
    public ExtendedDescription getExtendedDescription() {
        return extendedDescription;
    }

    /**
     * @return the product technical description
     */
    public Optional<TechnicalDescription> getTechnicalDescription() {
        return Optional.ofNullable(technicalDescription);
    }

    /**
     * @return the product brand name
     */
    public Optional<BrandName> getBrandName() {
        return Optional.ofNullable(brandName);
    }

    /**
     * @return the product reference
     */
    public Optional<Reference> getReference() {
        return Optional.ofNullable(reference);
    }

    /**
     * @return the product price without taxes
     */
    public Money getPriceWithoutTaxes() {
        return priceWithoutTaxes;
    }

    /**
     * @return the product status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @return the product weight
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * @return the product volume
     */
    public Volume getVolume() {
        return volume;
    }

    /**
     * @return the product price with taxes
     */
    public Money getPriceWithTaxes() {
        return priceWithTaxes;
    }

    /**
     * @return the product production code
     */
    public Optional<ProductionCode> getProductionCode() {
        return Optional.ofNullable(productionCode);
    }

    /**
     * @return the product photos
     */
    public Set<Photo> photos() {
        return Collections.unmodifiableSet(photos);
    }

    /**
     * Add a photo to the set of product photos
     * @param photo the photo to add
     * @return true if successfully added
     */
    public boolean addPhoto(final Photo photo){
        return photos.add(photo); //add(new ProductPhoto(photo)) ???
    }

    @Override
    public String toString(){
        return String.format("Product - %s, %s \n", uniqueInternalCode.toString(), shortDescription);
    }
}
