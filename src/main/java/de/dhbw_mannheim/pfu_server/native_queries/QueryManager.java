package de.dhbw_mannheim.pfu_server.native_queries;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> sqlDataQuery(String query){

        entityManager.getTransaction().begin();

        Query q = entityManager.createNativeQuery(query);

        List<Object[]> output = q.getResultList();

        entityManager.getTransaction().commit();
        //entityManager.close();

        return output;
    }

    /*
        @usage: sqlDataQueryMapped("SELECT ? FROM ...",["column1", "column2",...]);
     */
    public List<Map<String,Object>> sqlDataQueryMapped(String query, String[] columns){
        String columnsString = columnArrayToString(columns);
        query.replace("?", columnsString);

        List<Object[]> unformattedList = sqlDataQuery(query);

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

        for (String column:
             columns) {
            stringBuilder.append(column + ", ");
        }

        int len = stringBuilder.length();
        stringBuilder.delete(len-1, len);

        return stringBuilder.toString();
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
