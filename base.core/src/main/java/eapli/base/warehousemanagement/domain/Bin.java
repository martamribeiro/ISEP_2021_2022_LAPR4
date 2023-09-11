package eapli.base.warehousemanagement.domain;

import eapli.base.ordermanagement.domain.TheTask;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Bin.
 *
 * @author Marta Ribeiro 1201592
 */
@Entity
public class Bin implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long binID;

    @Column(name = "volume_in_cubic_meters")
    private Double size;

    @OneToOne
    @JoinColumn(name = "aisle_id")
    private Aisle aisle;

    @OneToOne
    @JoinColumn(name = "row_id")
    private TheRow row;

    @OneToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public enum BinStatus{
        IN_STOCK, OUT_OF_STOCK;
    }

    @Enumerated
    BinStatus status;

    public Bin(final Double size, final Aisle aisle, final TheRow row, final Shelf shelf, final Product product){
        Preconditions.noneNull(size,aisle,row,shelf,product);
        this.size=size;
        this.aisle=aisle;
        this.row=row;
        this.shelf=shelf;
        this.product=product;
        this.status=BinStatus.IN_STOCK;
    }

    protected Bin(){}

    public Double size() {
        return size;
    }

    public Aisle aisle() {
        return aisle;
    }

    public TheRow row() {
        return row;
    }

    public Shelf shelf() {
        return shelf;
    }

    public Product product() {
        return product;
    }

    public BinStatus status(){
        return status;
    }

    public void changeStatus(BinStatus status) {
        this.status = status;
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
        if (!(other instanceof Bin)) {
            return false;
        }

        final var that = (Bin) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return binID;
    }

    @Override
    public String toString() {
        return "Bin{" +
                "version=" + version +
                ", binID=" + binID +
                ", size=" + size +
                ", aisle=" + aisle +
                ", row=" + row +
                ", shelf=" + shelf +
                ", product=" + product +
                ", status=" + status +
                '}';
    }
}
