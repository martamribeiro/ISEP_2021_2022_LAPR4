package eapli.base.ordermanagement.domain;

import eapli.framework.general.domain.model.Money;


public enum Shipment {
    STANDARD (Constants.STANDARD_COST),
    BLUE (Constants.BLUE_COST),
    GREEN (Constants.GREEN_COST);

    /**
     * Nested Class of Shipment, containing each cost according to the Shipment Method
     */
    private static class Constants {
        private static final Money STANDARD_COST = Money.euros(6.0);
        private static final Money BLUE_COST = Money.euros(7.0);
        private static final Money GREEN_COST = Money.euros(8.0);
    }

    private final Money cost;

    Shipment (final Money cost) {
        this.cost = cost;
    }

    public double cost() {
        return this.cost.amountAsDouble();
    }

    public String currency() {
        return this.cost.currency().toString();
    }


}
