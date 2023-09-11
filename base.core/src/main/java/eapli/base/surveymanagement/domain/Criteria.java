package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import javax.persistence.Enumerated;

public enum Criteria{
    GENDER,
    AGE,
    PRODUCT_BOUGHT,
    PRODUCT_CATEGORY_BOUGHT;

    public Long age;

    public enum Signal {
        GREATER_THAN, LESS_THAN;
    }

    @Enumerated
    public Signal signal;

    public enum Gender {
        FEMININE, MASCULINE, OTHER;
    }

    @Enumerated
    public Gender gender;

    public String productCode;

    public String productCategoryCode;

    public boolean verifyAgeCriteria(Client client){
        if (client.birthdate()!=null) {
            if (this.signal.name().equalsIgnoreCase("GREATER_THAN")) {
                return client.age() > this.age.intValue();
            } else {
                return client.age() < this.age.intValue();
            }
        }
        return false;
    }

    public boolean verifyGenderCriteria(Client client){
        if (client.gender()!=null) {
            return client.gender().name().equalsIgnoreCase(this.gender.name());
        }
        return false;
    }

    public boolean verifyProductCategoryBoughtCriteria(Client client) {
        OrderRepository repository = PersistenceContext.repositories().orders();
        for (TheOrder order :
                repository.findByClient(client)) {
            for (OrderItem item :
                    order.orderItems()) {
                if (item.product().getProductCategory().identity().toString().equals(this.productCategoryCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyProductBoughtCriteria(Client client) {
        OrderRepository repository = PersistenceContext.repositories().orders();
        for (TheOrder order :
                repository.findByClient(client)) {
            for (OrderItem item :
                    order.orderItems()) {
                if (item.product().identity().code().equals(this.productCode)){
                    return true;
                }
            }
        }
        return false;
    }

}
