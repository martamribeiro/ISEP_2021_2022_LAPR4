package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Model implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer MAX_MODEL_LENGTH = 50;

    private static final Integer MAX_SHORT_DESCRIPTION_LENGTH = 30;

    private String modelID;

    private String shortDescription;

    private Double maxWeight;

    public Model(String modelID, String shortDescription, Double maxWeight) {
        Preconditions.nonEmpty(modelID, "Model can neither be empty nor null.");
        Preconditions.nonEmpty(shortDescription, "Description can neither be empty nor null.");
        Preconditions.ensure(modelID.length() <= MAX_MODEL_LENGTH);
        Preconditions.ensure(shortDescription.length() <= MAX_SHORT_DESCRIPTION_LENGTH);
        this.modelID = modelID;
        this.shortDescription = shortDescription;
        this.maxWeight = maxWeight;
    }

    protected Model(){
        this.modelID = null;
        this.shortDescription = null;
        this.maxWeight = null;
    }

    @Override
    public String toString(){
        return String.format("Model ID: %s\nShort Description: %s\nMax Weight: %.2f\n", modelID, shortDescription, maxWeight);
    }
}
