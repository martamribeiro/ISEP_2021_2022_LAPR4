package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class OrderStatus implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status {
        TO_BE_PREPARED, BEING_PREPARED_ON_WAREHOUSE,
        READY_FOR_PACKAGING, READY_FOR_CARRIER_DISPATCHING, DISPATCHED, DELIVERED_BY_CARRIER, RECEIVED_BY_COSTUMER;
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public OrderStatus(final Status status) {
        this.status = status;
    }

    public OrderStatus() {
        //for ORM purposes
    }

    public Status status() {
        return this.status;
    }

    public static OrderStatus valueOf(final Status status) {
        return new OrderStatus(status);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof OrderStatus)) {
            return false;
        } else {
            OrderStatus that = (OrderStatus) o;
            return this.status.equals(that.status);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.status).code();
    }

    public String toString() {
        return "Order Status: " + this.status;
    }
}
