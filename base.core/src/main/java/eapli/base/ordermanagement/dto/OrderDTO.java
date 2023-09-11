package eapli.base.ordermanagement.dto;

import java.io.Serializable;

/**
 * DTO for orders.
 *
 * @author Ana Albergaria
 *
 */
public class OrderDTO implements Serializable {

    private final long orderId;

    private final String orderDate;

    private final String status;

    public OrderDTO(final long id, final String date, final String status){
        this.orderId = id;
        this.orderDate = date;
        this.status = status;
    }

    public Long orderId() {
        return this.orderId;
    }

    public String status(){
        return this.status;
    }

    @Override
    public String toString() {
        return "------ Order ------\n" +
                "Id: " + orderId +
                "\nDate: " + orderDate +
                "\nStatus: " + status;
    }
}
