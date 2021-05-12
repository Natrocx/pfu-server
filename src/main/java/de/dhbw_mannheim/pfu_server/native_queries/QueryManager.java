package de.dhbw_mannheim.pfu_server.native_queries;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class QueryManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object> sqlDataQuery(String query){

        entityManager.getTransaction().begin();

        Query q = entityManager.createNativeQuery(query);

        List<Object> output = q.getResultList();

        entityManager.getTransaction().commit();
        //entityManager.close();

        return output;
    }

    public boolean sqlStatement(String query){
        try{
            entityManager.getTransaction().begin();

            Query q = entityManager.createNativeQuery(query);

            List<Object> output = q.getResultList();

            entityManager.getTransaction().commit();

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
