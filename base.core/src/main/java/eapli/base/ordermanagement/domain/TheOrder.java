package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.time.util.Calendars;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class TheOrder implements AggregateRoot<Long>, Serializable, DTOable<OrderDTO> {
    private static final long serialVersionUID = 1L;


    public enum SourceChannel {
        CALL, EMAIL, MEETING;
    }

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private Client client;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    private OrderStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "billing_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "billing_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "billing_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country"))
    })
    private OrderAddress billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "shipping_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "shipping_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country"))
    })
    private OrderAddress shippingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "no_taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "no_taxes_currency"))
    })
    private Money totalAmountWithoutTaxes;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "taxes_currency"))
    })
    private Money totalAmountWithTaxes;

    @Enumerated(EnumType.STRING)
    private Shipment shipment;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Embedded
    private OrderVolume orderVolume;

    @Embedded
    private OrderWeight orderWeight;

    @Enumerated(EnumType.STRING)
    private SourceChannel sourceChannel;

    @Temporal(TemporalType.DATE)
    private Calendar interactionDate;

    @Embedded
    private AdditionalComment additionalComment;

    @ManyToOne
    private SystemUser salesClerk;

    @Embedded
    private Notification notification;

    @OneToMany(cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> newOrderItems;

    public Notification getNotification() {
        return notification;
    }

    public AdditionalComment getAdditionalComment() {
        return additionalComment;
    }

    public OrderWeight getOrderWeight() {
        return orderWeight;
    }

    public OrderVolume getOrderVolume() {
        return orderVolume;
    }

    public TheOrder(final Client client, final OrderAddress billingAddress, final OrderAddress shippingAddress, final Shipment shipment, final Payment payment, final SourceChannel sourceChannel, final Calendar interactionDate, final AdditionalComment additionalComment, final SystemUser salesClerk, final List<OrderItem> newOrderItems) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.shipment = shipment;
        this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
        this.additionalComment = additionalComment;
        this.salesClerk = salesClerk;
        this.newOrderItems = newOrderItems;
        this.totalAmountWithoutTaxes = obtainTotalAmountWithoutTaxes();
        this.totalAmountWithTaxes = obtainTotalAmountWithTaxes();
        this.orderWeight = obtainTotalOrderWeight();
        this.orderVolume = obtainTotalOrderVolume();
        this.status = new OrderStatus(OrderStatus.Status.TO_BE_PREPARED);
        /*if(this.client != null) {
            this.notification = new Notification(this.client.email().toString(),
                    String.format("Client Order registered", this.orderId), "Your order has been registered! Thank you, \nG1-2DF.");
        }*/
    }

    public TheOrder(final Client client, final OrderAddress billingAddress, final OrderAddress shippingAddress, final Shipment shipment, final Payment payment, final SourceChannel sourceChannel, final Calendar interactionDate, final SystemUser salesClerk, final List<OrderItem> newOrderItems) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.shipment = shipment;
        this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
        this.salesClerk = salesClerk;
        this.newOrderItems = newOrderItems;
        this.totalAmountWithoutTaxes = obtainTotalAmountWithoutTaxes();
        this.totalAmountWithTaxes = obtainTotalAmountWithTaxes();
        this.orderWeight = obtainTotalOrderWeight();
        this.orderVolume = obtainTotalOrderVolume();
        this.status = new OrderStatus(OrderStatus.Status.TO_BE_PREPARED);
        /*if(this.client != null) {
            this.notification = new Notification(this.client.email().toString(),
                    String.format("Client Order registered", this.orderId), "Your order has been registered! Thank you, \nG1-2DF.");
        }*/
    }

    protected TheOrder() {
        //for ORM purposes
    }

    public Money obtainTotalAmountWithoutTaxes() {
        double totalAmountWithoutTaxes = 0;

        for (OrderItem orderItem : newOrderItems) {
            Product product = orderItem.product();
            totalAmountWithoutTaxes += (orderItem.quantity() * product.getPriceWithoutTaxes().amountAsDouble());
        }
        return this.totalAmountWithoutTaxes = Money.euros(totalAmountWithoutTaxes);
    }

    public Money obtainTotalAmountWithTaxes() {
        double totalAmountWithTaxes = 0;

        for (OrderItem orderItem : newOrderItems) {
            Product product = orderItem.product();
            totalAmountWithTaxes += (orderItem.quantity() * product.getPriceWithTaxes().amountAsDouble());
        }
        return this.totalAmountWithTaxes = Money.euros(totalAmountWithTaxes + this.shipment.cost());
    }

    public OrderWeight obtainTotalOrderWeight() {
        double totalWeight = 0;

        for (OrderItem orderItem : newOrderItems) {
            Product product = orderItem.product();
            totalWeight += (orderItem.quantity() * product.getWeight().weight());
        }
        return this.orderWeight = new OrderWeight(totalWeight);
    }

    public OrderVolume obtainTotalOrderVolume() {
        double totalVolume = 0;

        for (OrderItem orderItem : newOrderItems) {
            Product product = orderItem.product();
            totalVolume += (orderItem.quantity() * product.getVolume().volume());
        }
        return this.orderVolume = new OrderVolume(totalVolume);
    }

    public void changeOrderStatusTo(final OrderStatus.Status status) {
        this.status = new OrderStatus(status);
    }

    public OrderStatus getOrderStatus(){
        return this.status;
    }

    public Long getOrderId() {
        return orderId;
    }
    public Client ofClient(){
        return this.client;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> orderItems() {
        return newOrderItems;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.orderId;
    }

    public Calendar getCreatedOn() {
        return createdOn;
    }


    @Override
    public OrderDTO toDTO() {
        return new OrderDTO(this.orderId, new SimpleDateFormat("yyyy/MM/dd").format(this.createdOn.getTime()), this.status.status().toString());
    }
}
