package de.dhbw_mannheim.pfu_server.native_queries;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Queries {
    //TODO Nutzerinfos
    //
    public boolean createUser(String first_name, String last_name, String eMail, String password){
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String query = "INSERT INTO user (first_name, last_name, `e-mail`, password_encrypted)" +
                    " VALUES (:firstname, :lastname, :email, :password);";


            NativeQuery q = session.createNativeQuery(query)
                    .setParameter("firstname", first_name)
                    .setParameter("lastname", last_name)
                    .setParameter("email", eMail)
                    .setParameter("password", password);
            q.executeUpdate();

            transaction.commit();
            session.close();

            return true;
        }
        catch (Exception e){
            throw e;
            //return false;
        }

    }


    public List<Map<String,Object>> getAllUsers(){
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ?, ? FROM user;";

        String[] columns = {"ID_User", "first_name", "last_name", "e-mail", "password_encrypted"};
        String[] values = {};

        return qm.sqlDataQueryMapped(query, columns, values);
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

        String query = "SELECT ? FROM messagecontent WHERE ID_MessageContent = ?;";

        String[] columns = {"ID_MessageContent", "ContentType", "Content"};
        String[] values = {id_messageContent};

        return qm.sqlDataQueryMapped(query, columns, values);
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
    }   //end function


    public List<Map<String, Object>> getUser(String userID) {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ?, ? FROM user WHERE ID_User = ?;";

        String[] columns = {"ID_User", "first_name", "last_name", "e-mail", "password_encrypted"};
        String[] values = {userID};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public List<Map<String, Object>> getCourses() {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ? FROM course JOIN studiengang ON course.ID_Studiengang = studiengang.ID_Studiengang;";

        String[] columns = {"Course_Name", "Studiengang_Name"};
        String[] values = {};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public List<Map<String, Object>> getUserSuggestions(String studiengang_name, String company_name, String course_name,
                                                        String lecture_name, String minimum_lecture_proficiency, String email,
                                                        String first_name, String last_name) {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ?, ?, ?, ? FROM user JOIN student ON user.ID_User = student.ID_User " +
                "JOIN course ON student.Course_Name = course.Course_Name " +
                "JOIN studiengang ON course.ID_Studiengang = studiengang.ID_Studiengang " +
                "JOIN `student_is-employed-by_company` ON student.ID_User = `student_is-employed-by_company`.ID_User " +
                "JOIN company ON `student_is-employed-by_company`.ID_Company = company.ID_Company " +
                "JOIN student_attends_lecture ON student.ID_User = student_attends_lecture.ID_User " +
                "JOIN lecture ON student_attends_lecture.ID_Lecture = lecture.ID_Lecture ";

        String[] columns = {"user.ID_User", "first_name", "last_name", "e-mail", "course.Course_Name", "Studiengang_Name", "Company_Name"};
        String[] values = {};

        StringBuilder queryBuilder = new StringBuilder(query);

        StringBuilder conditionBuilder = new StringBuilder();

        if (!email.equals("")){
            conditionBuilder.append("`e-mail` = ? ");
            values = appendToArray(values, email);
        }
        if (!first_name.equals("")){
            addAnd(conditionBuilder);
            conditionBuilder.append("first_name = ? ");
            values = appendToArray(values, first_name);
        }
        if (!last_name.equals("")){
            addAnd(conditionBuilder);
            conditionBuilder.append("last_name = ? ");
            values = appendToArray(values, last_name);
        }
        if (!studiengang_name.equals("")){
            addAnd(conditionBuilder);
            conditionBuilder.append("Studiengang_Name = ? ");
            values = appendToArray(values, studiengang_name);
        }
        if (!company_name.equals("")){
            addAnd(conditionBuilder);
            conditionBuilder.append("Company_Name = ? ");
            values = appendToArray(values, company_name);
        }
        if (!course_name.equals("")){
            addAnd(conditionBuilder);
            conditionBuilder.append("course.Course_Name = ? ");
            values = appendToArray(values, course_name);
        }
        if (!lecture_name.equals("")){
            addAnd(conditionBuilder);
            conditionBuilder.append("Lecture_Name = ? ");
            values = appendToArray(values, lecture_name);

            if (!minimum_lecture_proficiency.equals("")){
                conditionBuilder.append("AND Proficiency > ? ");
                values = appendToArray(values, minimum_lecture_proficiency);
            }
        }

        if (conditionBuilder.length() != 0){
            queryBuilder.append("WHERE ");
            queryBuilder.append(conditionBuilder.toString());
        }
        queryBuilder.append(";");

        return qm.sqlDataQueryMapped(queryBuilder.toString(), columns, values);
    }

    private void addAnd(StringBuilder conditionBuilder) {
        if (conditionBuilder.length() != 0){
            conditionBuilder.append("AND ");
        }
    }

    private String[] appendToArray(String[] values, String append) {
        String[] out = new String[values.length+1];

        for (int i = 0; i < values.length; i++) {
            out[i] = values[i];
        }

        out[out.length-1] = append;

        return out;
    }

    public List<Map<String, Object>> getLecturesFromUser(String userID) {
        QueryManager qm = new QueryManager();

        String query =  "SELECT ? AS 'student.ID_User', first_name AS ?, last_name AS ?, ?, ?, ? AS 'dozent.ID_User', " +
                        "(SELECT first_name FROM user a WHERE a.ID_User = dozent.ID_User) AS ?, " +
                        "(SELECT last_name FROM user b WHERE b.ID_User = dozent.ID_User) AS ? " +
                "FROM student " +
                    "JOIN user on student.ID_User = user.ID_User " +
                    "JOIN student_attends_lecture on student.ID_User = student_attends_lecture.ID_User " +
                    "JOIN lecture on student_attends_lecture.ID_Lecture = lecture.ID_Lecture " +
                    "JOIN dozent_reads_lecture_for_course ON lecture.ID_Lecture = dozent_reads_lecture_for_course.ID_Lecture " +
                    "JOIN dozent on dozent_reads_lecture_for_course.ID_User = dozent.ID_User " +
                "WHERE student.ID_User = ?;";

        String[] columns = {"student.ID_User", "\'student.first_name\'", "\'student.last_name\'", "Lecture_Name", "Proficiency", "dozent.ID_User",
                            "\'dozent.first_name\'", "\'dozent.last_name\'"};
        String[] values = {userID};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public boolean addDozent(String userID, String biography, String special_role, List<String> titles) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String query1 = "INSERT INTO dozent (ID_User, Biography, Special_Role) VALUES (:userid, :biography, :role);";


            NativeQuery q1 = session.createNativeQuery(query1)
                    .setParameter("userid", userID)
                    .setParameter("biography", biography)
                    .setParameter("role", special_role.length() != 0 ? special_role : "NULL");
            q1.executeUpdate();

            String parameterisedTitleQuery = "INSERT INTO dozent_has_titles (ID_User, Title) VALUES (:userid, :title);";

            for (String title:
                    titles) {
                NativeQuery tq = session.createNativeQuery(parameterisedTitleQuery)
                        .setParameter("userid", userID)
                        .setParameter("title", title);
                tq.executeUpdate();
            }


            transaction.commit();
            session.close();

            return true;
        }
        catch (Exception e){
            throw e;
            //return false;
        }
    }

    public boolean addStudent(String userID, String biography, String course_name, String companyID,
                              String companyLocation, String hochschuleID, Date startDate, Date graduationDate) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String query1 = "INSERT INTO student (ID_User, Biography, Course_Name) VALUES (:userid, :biography, :coursename);";
            String query2 = "INSERT INTO `student_is-employed-by_company` (ID_User, ID_Company, Location) VALUES (:userid, :companyid, :companylocation);";
            String query3 = "INSERT INTO student_takes_studiengang (ID_User, ID_Studiengang, ID_DualeHochschule, Date_Start, Date_Graduation) " +
                                "VALUES (:userid, (SELECT ID_Studiengang FROM course WHERE course.Course_Name = :coursename)," +
                                " :hochschuleid, :startdate, :graduationdate);";

            NativeQuery q1 = session.createNativeQuery(query1)
                    .setParameter("userid", userID)
                    .setParameter("biography", biography)
                    .setParameter("coursename", course_name);
            q1.executeUpdate();

            NativeQuery q2 = session.createNativeQuery(query2)
                    .setParameter("userid", userID)
                    .setParameter("companyid", companyID)
                    .setParameter("companylocation", companyLocation.length() != 0 ? companyLocation : "NULL");
            q2.executeUpdate();

            NativeQuery q3 = session.createNativeQuery(query3)
                    .setParameter("userid", userID)
                    .setParameter("coursename", course_name)
                    .setParameter("hochschuleid", hochschuleID)
                    .setParameter("startdate", startDate)
                    .setParameter("graduationdate", graduationDate);
            q3.executeUpdate();

            String lecturesQuery = "SELECT ? FROM dozent_reads_lecture_for_course WHERE Course_Name = ?";
            String lectureColumnName = "ID_Lecture";
            List<Map<String,Object>> lectureList = new QueryManager()
                    .sqlDataQueryMapped(lecturesQuery, new String[]{lectureColumnName}, new String[]{course_name});

            String parametisedLectureQuery = "INSERT INTO student_attends_lecture (ID_User, ID_Lecture) VALUES (:userid, :lectureid);";

            for (Map<String,Object> lecture:
                 lectureList) {
                NativeQuery lq = session.createNativeQuery(parametisedLectureQuery)
                                    .setParameter("userid", userID)
                                    .setParameter("lectureid", lecture.get(lectureColumnName));
                lq.executeUpdate();
            }

            transaction.commit();
            session.close();

            return true;
        }
        catch (Exception e){
            throw e;
            //return false;
        }
    }

    public boolean addReadLecture(String userID, String lectureName, String course_name) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String query1 = "INSERT INTO lecture (Lecture_Name) VALUES (:lecturename)";

            NativeQuery q1 = session.createNativeQuery(query1)
                    .setParameter("lecturename", lectureName);
            q1.executeUpdate();

            String lectureIDquery = "SELECT LAST_INSERT_ID();";

            NativeQuery lidq = session.createNativeQuery(lectureIDquery);
            Object lid = lidq.getResultList().get(0);

            String query2 = "INSERT INTO dozent_reads_lecture_for_course (ID_User, ID_Lecture, Course_Name) VALUES (:userid, :lectureid, :coursename)";


            NativeQuery q2 = session.createNativeQuery(query2)
                    .setParameter("userid", userID)
                    .setParameter("coursename", course_name)
                    .setParameter("lectureid", lid);
            q2.executeUpdate();


            String studentQuery = "SELECT ? FROM student WHERE Course_Name = ?";
            String idColumnName = "ID_User";
            List<Map<String,Object>> userList = new QueryManager()
                    .sqlDataQueryMapped(studentQuery, new String[]{idColumnName}, new String[]{course_name});

            String parametisedLectureQuery = "INSERT INTO student_attends_lecture (ID_User, ID_Lecture) VALUES (:userid, :lectureid);";

            for (Map<String,Object> user:
                    userList) {
                NativeQuery uq = session.createNativeQuery(parametisedLectureQuery)
                        .setParameter("userid", user.get(idColumnName))
                        .setParameter("lectureid", lid);
                uq.executeUpdate();
            }

            transaction.commit();
            session.close();

            return true;
        }
        catch (Exception e){
            throw e;
            //return false;
        }
    }

    public List<Map<String, Object>> getStudents() {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ?, ?, ?, ? FROM user JOIN student ON user.ID_User = student.ID_User " +
                "JOIN course ON student.Course_Name = course.Course_Name " +
                "JOIN studiengang ON course.ID_Studiengang = studiengang.ID_Studiengang " +
                "JOIN `student_is-employed-by_company` ON student.ID_User = `student_is-employed-by_company`.ID_User " +
                "JOIN company ON `student_is-employed-by_company`.ID_Company = company.ID_Company;";

        String[] columns = {"user.ID_User", "first_name", "last_name", "e-mail", "course.Course_Name", "Studiengang_Name", "Company_Name"};
        String[] values = {};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public List<Map<String, Object>> getDozenten() {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ? FROM user JOIN dozent ON user.ID_User = dozent.ID_User;";

        String[] columns = {"user.ID_User", "first_name", "last_name", "e-mail"};
        String[] values = {};

        return qm.sqlDataQueryMapped(query, columns, values);
    }

    public String[] verifyUser(String verificationKey) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String query1 = "SELECT ?, ?, ?, ? FROM verification WHERE verificationKey = ?;";

            List<Map<String, Object>> verificationList = new QueryManager().sqlDataQueryMapped(query1,
                    new String[]{"ID_User", "verificationKey", "used", "target"},
                    new String[]{verificationKey});

            if (verificationList.size() == 0){

                transaction.commit();
                session.close();
                return new String[]{"Fail", "Invalid Key"};
            } else {
                Map<String, Object> key = verificationList.get(0);

                if (key.get("used").equals(1)){

                    transaction.commit();
                    session.close();
                    return new String[]{"Fail", "Key was already used"};
                } else {

                    String updateKeyAsUsedQueryString = "UPDATE verification SET used = TRUE WHERE verificationKey = :verificationkey;";

                    NativeQuery updateKeyAsUsedQuery = session.createNativeQuery(updateKeyAsUsedQueryString)
                            .setParameter("verificationkey", verificationKey);
                    updateKeyAsUsedQuery.executeUpdate();

                    if (!key.get("target").equals("email")){    //When key target != email, and exists, return valid key + target

                        updateKeyAsUsedQuery.executeUpdate();

                        transaction.commit();
                        session.close();
                        return new String[]{"Valid Key", key.get("target").toString()};
                    }
                    else {  //when key target == email and exists, verify email

                        String verifyUserQueryString = "UPDATE user SET verified = TRUE WHERE ID_User = :userid;";

                        NativeQuery verifyUserQuery = session.createNativeQuery(verifyUserQueryString)
                                .setParameter("userid", key.get("ID_User"));
                        verifyUserQuery.executeUpdate();

                        updateKeyAsUsedQuery.executeUpdate();

                        transaction.commit();
                        session.close();

                        return new String[]{"Valid Key", "email"};
                    }
                }
            }
        }
        catch (Exception e){
            return new String[]{"Fail", e.getStackTrace().toString()};
            //return false;
        }
    }

    public String[] generateVerificationKey(String userID, String target) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String queryGetUser = "SELECT ?, ? FROM user WHERE ID_User = ?;";

            List<Map<String, Object>> userList = new QueryManager().sqlDataQueryMapped(queryGetUser, new String[]{"ID_User", "e-mail"},
                    new String[]{userID});

            if (userList.size() == 0){

                transaction.commit();
                session.close();
                return new String[]{"Invalid User", "", ""};
            } else {
                Map<String, Object> user = userList.get(0);

                String queryGetAllPreviousKeys = "SELECT ? FROM verification;";

                List<Map<String, Object>> keyList = new QueryManager().sqlDataQueryMapped(queryGetAllPreviousKeys,
                        new String[]{"verificationKey"},
                        new String[]{});

                List<String> keys = extractKeys(keyList, "verificationKey");

                String verificationKey = generateKey(255, keys);

                String queryInsertNewKey = "INSERT INTO verification (ID_User, verificationKey, target) VALUES (:userid, :verificationkey, :target)";

                NativeQuery qInsertNewKey = session.createNativeQuery(queryInsertNewKey)
                        .setParameter("userid", userID)
                        .setParameter("verificationkey", verificationKey)
                        .setParameter("target", target);
                qInsertNewKey.executeUpdate();


                transaction.commit();
                session.close();

                return new String[]{"Success", user.get("e-mail").toString(), verificationKey};
            }
        }
        catch (Exception e){
            throw e;
            //return false;
        }
    }

    private String generateKey(int length, List<String> keys) {

        boolean useLetters = true;
        boolean useNumbers = true;

        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        while (keys.contains(generatedString)){
            generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        }

        return generatedString;
    }

    private List<String> extractKeys(List<Map<String, Object>> keyList, String keyName) {
        List<String> keys = new ArrayList<>();

        for (Map<String, Object> k:
             keyList) {
            keys.add(k.get(keyName).toString());
        }

        return keys;
    }

    public String[] updateUser(String userid, String first_name, String last_name, String email, String encrypted_password) {
        QueryManager qm = new QueryManager();

        try{

            Session session = qm.getSession();

            Transaction transaction = session.beginTransaction();

            String queryGetUser = "SELECT ?, ? FROM user WHERE ID_User = ?;";

            List<Map<String, Object>> userList = new QueryManager().sqlDataQueryMapped(queryGetUser, new String[]{"ID_User", "e-mail"},
                    new String[]{userid});

            if (userList.size() == 0){

                transaction.commit();
                session.close();
                return new String[]{"Fail", "Invalid User"};
            } else {
                Map<String, Object> user = userList.get(0);

                if (user.get("e-mail").equals(email)) {

                    String query = "UPDATE user SET first_name = :firstname, last_name = :lastname, password_encrypted = :password " +
                            "WHERE ID_User = :userid;";


                    NativeQuery q = session.createNativeQuery(query)
                            .setParameter("firstname", first_name)
                            .setParameter("lastname", last_name)
                            .setParameter("password", encrypted_password)
                            .setParameter("userid", userid);
                    q.executeUpdate();

                    transaction.commit();
                    session.close();

                    return new String[]{"Success", "User updated"};
                } else {
                    String query = "UPDATE user SET first_name = :firstname, last_name = :lastname, `e-mail` = :email, verified = :verified, " +
                            "password_encrypted = :password " +
                            "WHERE ID_User = :userid;";


                    NativeQuery q = session.createNativeQuery(query)
                            .setParameter("firstname", first_name)
                            .setParameter("lastname", last_name)
                            .setParameter("email", email)
                            .setParameter("verified", "FALSE")
                            .setParameter("password", encrypted_password)
                            .setParameter("userid", userid);
                    q.executeUpdate();

                    transaction.commit();
                    session.close();

                    return new String[]{"Success", "User updated, needs E-Mail verification"};
                }
            }
        }
        catch (Exception e){
            return new String[]{"Fail", e.getStackTrace().toString()};
            //return false;
        }
    }

    public List<Map<String, Object>> getInfos() {
        QueryManager qm = new QueryManager();

        String query = "SELECT ?, ?, ?, ?, ?, ? FROM info";

        String[] columns = {"ID_Info", "Display_Name", "Kurzinfo", "Priority", "Textinfo", "Weblink"};
        String[] values = {};

        return qm.sqlDataQueryMapped(query, columns, values);
    }
}
