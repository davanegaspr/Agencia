package DataAccess.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-20T03:43:01")
@StaticMetamodel(Plan.class)
public class Plan_ { 

    public static volatile SingularAttribute<Plan, Date> returnDate;
    public static volatile SingularAttribute<Plan, String> modeTransport;
    public static volatile SingularAttribute<Plan, String> departureCity;
    public static volatile SingularAttribute<Plan, String> name;
    public static volatile SingularAttribute<Plan, Double> baseCostByAdult;
    public static volatile SingularAttribute<Plan, Long> planId;
    public static volatile SingularAttribute<Plan, Date> departureDate;
    public static volatile SingularAttribute<Plan, Long> hotelId;
    public static volatile SingularAttribute<Plan, String> arrivalCity;
    public static volatile SingularAttribute<Plan, Double> baseCostByChild;

}