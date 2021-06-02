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

    public List<Object[]> sqlDataQuery(String query, String[] parameters){

        Session session = getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery q = session.createNativeQuery(query);

        for (int i = 0; i < parameters.length; i++) {
            q.setParameter(i+1, parameters[i]);
        }

        List output = q.list();

        transaction.commit();
        session.close();

        return output;
    }

    public List<Map<String,Object>> sqlDataQueryMapped(String query, String[] columns, String[] parameters){

        //String[] combinedArray = combineArrays(columns, parameters);
        String columnQuery = addColumns(query, columns);

        List<Object[]> unformattedList = sqlDataQuery(columnQuery, parameters);

        return mapQuery(unformattedList, columns);
    }

    private String addColumns(String query, String[] columns) {
        StringBuilder sb = new StringBuilder(query);

        int idx = 0;
        for (int i = 0; i < columns.length; i++) {
            int x = sb.indexOf("?", idx);
            idx = x;

            sb.replace(x, x+1, makeColumn(columns[i]));
        }

        return sb.toString();
    }

    private String makeColumn(String column) {
        StringBuilder sb = new StringBuilder();
        String wrapper = "`";
        if (column.contains("\'")){
            sb.append(column);
        } else{
            if(!column.contains(".")) {

                sb.append(wrapper);
                sb.append(column);
                sb.append(wrapper);

            } else{
                String[] split = column.split("\\.");
                sb.append(split[0]);
                sb.append(".");
                sb.append(wrapper);
                sb.append(split[1]);
                sb.append(wrapper);
            }
        }

        return sb.toString();
    }

    private String[] combineArrays(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;

        String[] output = new String[aLen+bLen];

        int i = 0;
        for (; i < aLen; i++) {
            output[i] = a[i];
        }
        for (; i-aLen < bLen; i++) {
            output[i] = b[i-aLen];
        }
        return output;

    }

    /*
        @usage: sqlDataQueryMapped("SELECT ? FROM ...",["column1", "column2",...]);
     */
    public List<Map<String,Object>> sqlDataQueryMapped(String query, String[] columns){
        String columnsString = columnArrayToString(columns);
        StringBuilder sb = new StringBuilder(query);
        int indexofq = sb.indexOf("?");
        sb.replace(indexofq,indexofq+1, columnsString);

        List<Object[]> unformattedList = sqlDataQuery(sb.toString());

        return mapQuery(unformattedList, columns);
    }

    private List<Map<String,Object>> mapQuery(List<Object[]> unformattedList, String[] columns) {
        List<Map<String,Object>> formattedList = new ArrayList<>();

        if (columns.length == 1){
            for (Object stringResult:
                 unformattedList) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put(columns[0].replace("\'", ""), stringResult);
                formattedList.add(resultMap);
            }
        }
        else {
            for (Object[] result : unformattedList) {
                Map<String, Object> resultMap = new HashMap<>();

                for (int i = 0; i < result.length; i++) {
                    try {
                        resultMap.put(columns[i].replace("\'", ""), result[i]);
                    } catch (Exception e) {

                    }
                }
                formattedList.add(resultMap);
            }
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
