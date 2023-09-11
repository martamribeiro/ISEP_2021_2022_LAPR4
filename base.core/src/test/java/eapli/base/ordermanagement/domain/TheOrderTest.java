package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.*;
import eapli.base.clientmanagement.domain.Name;
import eapli.base.productmanagement.domain.*;
import eapli.base.usermanagement.application.AddUserController;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.util.Calendars;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TheOrderTest {
    private OrderAddress billingAddress;
    private OrderAddress shippingAdddress;
    private Client client;
    private Set<Address> addresses = new HashSet<>();
    private SystemUser salesClerk;
    private Set<Role> roles = new HashSet<>();
    private List<OrderItem> newOrderItems = new ArrayList<>();
    private OrderItem orderItem1;

    @Before
    public void setUp() {
        billingAddress = new OrderAddress("Travessa do Outeiro 1","7","4520-463","Rio Meão","Portugal");
        shippingAdddress = new OrderAddress("Travessa do Outeiro 1","7","4520-463","Rio Meão","Portugal");
        addresses.add(Address.valueOf("Travessa do Outeiro 1","7","4520-463","Rio Meão","Portugal"));
        client = new Client(Name.valueOf("Joao", "Sousa"), VAT.valueOf("PT123456789"), Email.valueOf("joao@gmail.com"), PhoneNumber.valueOf("+351918413784"), addresses);
        roles.add(BaseRoles.SALES_CLERK);
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        salesClerk = userBuilder.with("sales_clerk", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
        orderItem1 = new OrderItem(3,new Product(Code.valueOf("abcd.12345"), Barcode.valueOf("123456789012"), ShortDescription.valueOf("Short description."), ExtendedDescription.valueOf("Very very very very very very very extended description."),
                Money.euros(1.5), Product.Status.AVAILABLE,Weight.valueOf(10),Volume.valueOf(5.3),Money.euros(3),new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category"))));
        newOrderItems.add(orderItem1);
    }

    @Test
    public void createValidOrder() {
        new TheOrder(client,billingAddress,shippingAdddress,Shipment.BLUE,Payment.APPLE_PAY, TheOrder.SourceChannel.CALL,Calendars.now(),salesClerk,newOrderItems);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureOrderAddressMustHaveStreeName() {
        new OrderAddress(null, "7", "4520-463", "Rio Meão", "Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureOrderAddressMustHaveDoorNumber() {
        new OrderAddress("Travessa do Outeiro", null, "4520-463", "Rio Meão", "Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureOrderAddressMustHavePostalCode() {
        new OrderAddress("Travessa do Outeiro", "7", null, "Rio Meão", "Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureOrderAddressMustHaveCity() {
        new OrderAddress("Travessa do Outeiro", "7", "4520-463", null, "Portugal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureOrderAddressMustHaveCountry() {
        new OrderAddress("Travessa do Outeiro", "7", "4520-463", "Rio Meão", null);
    }

    @Test
    public void ensureChangeOrderStatusToExpected() {
        OrderStatus expOrderStatus = OrderStatus.valueOf(OrderStatus.Status.DELIVERED_BY_CARRIER);

        TheOrder order = new TheOrder(client,billingAddress,shippingAdddress,Shipment.BLUE,Payment.APPLE_PAY, TheOrder.SourceChannel.CALL,Calendars.now(),salesClerk,newOrderItems);
        order.changeOrderStatusTo(OrderStatus.Status.DELIVERED_BY_CARRIER);

        OrderStatus orderStatus = order.getOrderStatus();

        assertEquals(expOrderStatus, orderStatus);
    }
}