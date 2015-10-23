package DataAccess.Entity;

import DataAccess.Entity.Plan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-23T13:56:05")
@StaticMetamodel(Hotel.class)
public class Hotel_ { 

    public static volatile SingularAttribute<Hotel, Float> price;
    public static volatile SingularAttribute<Hotel, String> name;
    public static volatile SingularAttribute<Hotel, String> location;
    public static volatile SingularAttribute<Hotel, Long> hotelId;
    public static volatile SingularAttribute<Hotel, String> category;
    public static volatile CollectionAttribute<Hotel, Plan> planCollection;

}