package DataAccess.Entity;

import DataAccess.Entity.Plan;
import DataAccess.Entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-23T13:56:05")
@StaticMetamodel(Tickets.class)
public class Tickets_ { 

    public static volatile SingularAttribute<Tickets, Plan> planplanId;
    public static volatile SingularAttribute<Tickets, Long> idticket;
    public static volatile SingularAttribute<Tickets, String> dateStart;
    public static volatile SingularAttribute<Tickets, Double> price;
    public static volatile SingularAttribute<Tickets, User> useruserId;
    public static volatile SingularAttribute<Tickets, String> dateBuy;
    public static volatile SingularAttribute<Tickets, Short> status;

}