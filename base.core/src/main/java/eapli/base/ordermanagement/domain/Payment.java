package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;


public enum Payment {
    PAYPAL (Constants.PAYPAL_TEXT),
    APPLE_PAY (Constants.APPLE_PAY_TEXT);

    /**
     * Nested Class of Shipment, containing each cost according to the Shipment Method
     */
    private static class Constants {
        private static final String PAYPAL_TEXT = "Thank you, your payment has been successful";
        private static final String APPLE_PAY_TEXT = "Payment successful!";
    }

    private final String text;

    Payment (final String text) {
        this.text = text;
    }

    public String text() {
        return this.text;
    }
}
