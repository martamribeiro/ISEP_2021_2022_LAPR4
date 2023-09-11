package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;
import org.json.simple.JSONObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class WarehouseName implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public WarehouseName(String name){
        Preconditions.nonEmpty(name, "Name can't be empty!");

        this.name=name;
    }

    public WarehouseName() {
        this.name="";
    }

    public static  WarehouseName valueOf(final String name){
        return new WarehouseName(name);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof WarehouseName)) {
            return false;
        } else {
            WarehouseName that = (WarehouseName) o;
            return this.name.equals(that.name);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.name).code();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
