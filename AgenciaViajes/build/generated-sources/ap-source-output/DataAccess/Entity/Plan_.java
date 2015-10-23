package DataAccess.Entity;

import DataAccess.Entity.Hotel;
import DataAccess.Entity.Tickets;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-23T13:56:05")
@StaticMetamodel(Plan.class)
public class Plan_ { 

    public static volatile SingularAttribute<Plan, String> returnDate;
    public static volatile SingularAttribute<Plan, String> modeTransport;
    public static volatile SingularAttribute<Plan, Hotel> hotelhotelId;
    public static volatile SingularAttribute<Plan, String> departureCity;
    public static volatile SingularAttribute<Plan, String> name;
    public static volatile SingularAttribute<Plan, Double> baseCostByAdult;
    public static volatile SingularAttribute<Plan, Long> planId;
    public static volatile SingularAttribute<Plan, String> departureDate;
    public static volatile CollectionAttribute<Plan, Tickets> ticketsCollection;
    public static volatile SingularAttribute<Plan, String> arrivalCity;
    public static volatile SingularAttribute<Plan, Double> baseCostByChild;

}