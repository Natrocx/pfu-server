package de.dhbw_mannheim.pfu_server.native_queries;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Queries {
    //TODO Nutzerinfos
    //
    public boolean createUser(String first_name, String last_name, String eMail, String password){
        QueryManager qm = new QueryManager();
        String query = "INSERT INTO user (first_name, last_name, `e-mail`, password_encrypted)" +
                " VALUES (\"" + first_name + "\", \"" + last_name + "\", \"" + eMail + "\", \"" + password + "\");";

        return qm.sqlStatement(query);
    }

    public boolean createStudent(String ID_User, String Biography, String Course_Name){
        QueryManager qm = new QueryManager();

        String query = "INSERT INTO student (ID_User, Biography, Course_Name)" +
                " VALUES (" + ID_User + ", " + Biography + ", " + Course_Name  + ")";

        return qm.sqlStatement(query);
    }

    public List<Object[]> getAllUsers(){
        QueryManager qm = new QueryManager();
        String query = "SELECT * FROM user;";

        return qm.sqlDataQuery(query);
    }

    public List<Map<String,Object>> getUserIDFromEmail(String email){
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ? FROM user WHERE `e-mail` = ?;";

        String[] columns = {"ID_USER", "e-mail"};
        String[] values = {email};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public List<Map<String, Object>> getMessageIDs(String userID_1, String userID_2) {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ?, ?, ?, ? FROM user_sends_message_to_user " +
                "JOIN message ON user_sends_message_to_user.ID_Message = message.ID_Message " +
                "JOIN messagecontent ON message.ID_MessageContent = messagecontent.ID_MessageContent " +
                "WHERE ID_User1 = ? AND ID_User2 = ?;";

        String[] columns = {"ID_USER1", "ID_USER2", "message.ID_Message", "Timestamp", "messagecontent.ID_MessageContent",
                "ContentType", "Content"};
        String[] values = {userID_1, userID_2};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public List<Map<String, Object>> getMessageContent(String id_messageContent) {
        QueryManager qm = new QueryManager();

        String query = "SELECT ? FROM messagecontent WHERE ID_MessageContent = \'" + id_messageContent +"\';";

        String[] columns = {"`ID_MessageContent`", "`ContentType`", "`Content`"};

        return qm.sqlDataQueryMapped(query, columns);
    }

    public boolean addTextMessage(String senderID, String receiverID, String content) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String query1 = "INSERT INTO messagecontent (ContentType, Content) VALUES (\'text\', :content);";
            String query2 = "INSERT INTO message (ID_MessageContent) VALUES (LAST_INSERT_ID()); ";
            String query3 = "INSERT INTO user_sends_message_to_user (ID_User1, ID_User2, ID_Message, Timestamp) " +
                    "VALUES (:senderid, :receiverid, LAST_INSERT_ID(), CURRENT_TIMESTAMP()); ";

            NativeQuery q1 = session.createNativeQuery(query1)
                    .setParameter("content", content);
            q1.executeUpdate();

            NativeQuery q2 = session.createNativeQuery(query2);
            q2.executeUpdate();

            NativeQuery q3 = session.createNativeQuery(query3)
                    .setParameter("senderid", senderID)
                    .setParameter("receiverid", receiverID);

            q3.executeUpdate();


            transaction.commit();
            session.close();

            return true;
        }
        catch (Exception e){
            throw e;
            //return false;
        }

        //return qm.sqlStatement(query);
    }

}
