package DataAccess.Entity;

import DataAccess.Entity.Tickets;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-23T13:56:05")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> role;
    public static volatile SingularAttribute<User, Double> balance;
    public static volatile SingularAttribute<User, String> documentType;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> document;
    public static volatile CollectionAttribute<User, Tickets> ticketsCollection;
    public static volatile SingularAttribute<User, Long> userId;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> username;

}