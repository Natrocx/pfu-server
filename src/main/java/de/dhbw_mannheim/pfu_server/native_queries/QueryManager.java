package de.dhbw_mannheim.pfu_server.native_queries;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.QueryProducer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryManager {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("de.dhbw_mannheim.pfu_server");


    SessionFactory sessionFactory = getCurrentSessionFromJPA();


    public SessionFactory getCurrentSessionFromJPA() {
        EntityManager entityManager = getEntityManager();
        // Get the Hibernate Session from the EntityManager in JPA
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SessionFactory sessionFactory = session.getSessionFactory();
        return sessionFactory;
    }


    //@PersistenceContext
    //private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /*
    public List<Object[]> sqlDataQuery(String query){

        EntityManager entityManager = getEntityManager();

        entityManager.getTransaction().begin();

        Query q = entityManager.createNativeQuery(query);

        List<Object[]> output = q.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return output;
    }*/

    public List<Object[]> sqlDataQuery(String query){

        Session session = getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery q = session.createNativeQuery(query);
        List output = q.list();

        transaction.commit();
        session.close();

        return output;
    }

    /*
        @usage: sqlDataQueryMapped("SELECT ? FROM ...",["column1", "column2",...]);
     */
    public List<Map<String,Object>> sqlDataQueryMapped(String query, String[] columns){
        String columnsString = columnArrayToString(columns);
        //query.replace("?", columnsString);
        StringBuilder sb = new StringBuilder(query);
        int indexofq = sb.indexOf("?");
        sb.replace(indexofq,indexofq+1, columnsString);

        List<Object[]> unformattedList = sqlDataQuery(sb.toString());

        return mapQuery(unformattedList, columns);
    }

    private List<Map<String,Object>> mapQuery(List<Object[]> unformattedList, String[] columns) {
        List<Map<String,Object>> formattedList = new ArrayList<>();

        for (Object[] result: unformattedList) {
            Map<String, Object> resultMap = new HashMap<>();
            for (int i = 0; i< result.length; i++){
                try {
                    resultMap.put(columns[i], result[i]);
                }
                catch(Exception e){

                }
            }
            formattedList.add(resultMap);
        }

        return formattedList;
    }


    private String columnArrayToString(String[] columns){
        StringBuilder stringBuilder = new StringBuilder();

        /*for (String column:
             columns) {
            stringBuilder.append(column + ", ");
        }*/
        for (int i = 0; i < columns.length-1; i++) {
            stringBuilder.append(columns[i] + ", ");
        }
        stringBuilder.append(columns[columns.length-1]);

        //stringBuilder.deleteCharAt(stringBuilder.length()-1);

        return stringBuilder.toString();
    }

    public boolean sqlStatement(String query){

        try{
            EntityManager entityManager = getEntityManager();

            entityManager.getTransaction().begin();

            Query q = entityManager.createNativeQuery(query);

            q.executeUpdate();

            entityManager.getTransaction().commit();
            entityManager.close();

            return true;
        }
        catch (Exception e){
            throw e;
            //return false;
        }
    }

}
