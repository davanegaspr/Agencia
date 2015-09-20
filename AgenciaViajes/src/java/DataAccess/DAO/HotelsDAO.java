/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author USER
 */
public class HotelsDAO {
    public HotelsDAO persist(HotelsDAO hotel){
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("agenciaPU");
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try{
            em.persist(hotel);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
            return hotel;
        }       
    }   
}
