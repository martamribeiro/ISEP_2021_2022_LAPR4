package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class TheRule implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ruleId;

    @ElementCollection
    @CollectionTable(name="rule_criteria")
    @Column(name="criteria_type")
    private List<Criteria> criteria;

    public TheRule(final List<Criteria> criteria){
        Preconditions.noneNull(criteria,"Rule needs to have criteria!");
        this.criteria=criteria;
    }

    public TheRule(){
        //for ORM purposes
    }

    public List<Criteria> criteria(){
        return this.criteria;
    }

    public boolean equals(final Object o){
        return this==o;
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.criteria).code();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof TheRule)) {
            return false;
        }

        final var that = (TheRule) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }


    @Override
    public Long identity() {
        return null;
    }
}
