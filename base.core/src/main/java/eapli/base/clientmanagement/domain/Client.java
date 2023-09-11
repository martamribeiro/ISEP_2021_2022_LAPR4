package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.time.util.Calendars;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

@Entity
public class Client implements AggregateRoot<Long>, Serializable {

    public enum Gender {
        FEMININE, MASCULINE, OTHER;
    }

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long clientId;

    @Embedded
    private Name name;

    @Embedded
    private VAT vat;

    @Embedded
    private Email email;

    @Embedded
    private PhoneNumber phoneNumber;

    @Temporal(TemporalType.DATE)
    private Calendar birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    private Set<Address> addresses;


    /**
     * Constructor for Client with the minimum attributes.
     *
     * @param name the costumer name
     * @param vat the costumer vat
     * @param email the costumer email
     * @param phoneNumber the costumer phone number
     */
    public Client(final Name name, final VAT vat, final Email email, final PhoneNumber phoneNumber, final Set<Address> addresses) {
        Preconditions.noneNull(name, vat, email, phoneNumber);
        Preconditions.noneNull(addresses, "The Client must have at least one address.");
        this.name = name;
        this.vat = vat;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

    protected Client() {
        //for ORM only
    }

    public void addGender(final Gender gender) {
        this.gender = gender;
    }

    public void insertBirthDate(final Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public Name name() {
        return this.name;
    }

    public void updateName(final Name name) {
        this.name = name;
    }

    public VAT vat() {
        return this.vat;
    }

    public void updateVat(VAT vat) {
        this.vat = vat;
    }

    public Email email() {
        return this.email;
    }

    public void setEmail(final Email email) {
        this.email = email;
    }

    public PhoneNumber phoneNumber() {
        return this.phoneNumber;
    }

    public void changePhoneNumber(final PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender gender(){
        return this.gender;
    }

    public Calendar birthdate(){
        return this.birthdate;
    }

    public int age(){
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return Period.between(LocalDateTime.ofInstant(this.birthdate.getTime().toInstant(), zid).toLocalDate() , LocalDateTime.ofInstant(Calendars.now().getTime().toInstant(), zid).toLocalDate()).getYears();
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
        return this.clientId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "version=" + version +
                ", clientId=" + clientId +
                ", name=" + name +
                ", vat=" + vat +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", addresses=" + addresses +
                '}';
    }
}
