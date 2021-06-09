package de.dhbw_mannheim.pfu_server.restservice;

import de.dhbw_mannheim.pfu_server.mailservice.EmailServiceImpl;
import de.dhbw_mannheim.pfu_server.native_queries.Queries;
import de.dhbw_mannheim.pfu_server.native_queries.QueryManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {


    @PostMapping(path="/addUser") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String first_name, @RequestParam String last_name
            , @RequestParam String email, @RequestParam String encrypted_password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Queries q = new Queries();

        boolean success = q.createUser(first_name, last_name, email, encrypted_password);

        String out = success ? "Success" : "Fail";

        //return first_name + " " + last_name + " " + email + " " + encrypted_password + " " + out;
        return out;
    }

    @GetMapping(path="/getLastID")
    public @ResponseBody String last_insert_id() {
        QueryManager qm = new QueryManager();
        String query = "SELECT LAST_INSERT_ID();";

        List<Object[]> result = qm.sqlDataQuery(query);

        Object[] array = result.stream().toArray();

        StringBuilder sb = new StringBuilder();

        for (Object o:
             array) {
            sb.append(o);
        }

        return sb.toString();
    }

    @GetMapping(path="/selectUsers")
    public @ResponseBody List<Map<String,Object>> getAllUsers() {
        Queries q = new Queries();

        return q.getAllUsers();
    }

    @GetMapping(path="/getUserIDbyMail")
    public @ResponseBody List<Map<String,Object>> getUserIDFromEmail(@RequestParam String email) {
        Queries q = new Queries();

        return q.getUserIDFromEmail(email);
    }

    @GetMapping(path="/getMessages")
    public @ResponseBody List<Map<String,Object>> getMessages(@RequestParam String UserID_1, @RequestParam String UserID_2) {
        Queries q = new Queries();

        return q.getMessageIDs(UserID_1, UserID_2);
    }

    @GetMapping(path="/getMessageContent")
    public @ResponseBody List<Map<String,Object>> getMessageContent(@RequestParam String ID_MessageContent) {
        Queries q = new Queries();

        return q.getMessageContent(ID_MessageContent);
    }

    @PostMapping(path="/addTextMessage") // Map ONLY POST Requests
    public @ResponseBody String addTextMessage (@RequestParam String senderID, @RequestParam String receiverID
            , @RequestParam String content) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Queries q = new Queries();

        boolean success = q.addTextMessage(senderID, receiverID, content);

        String out = success ? "Success" : "Fail";

        return out;
    }

    @GetMapping(path="/getUserByID")
    public @ResponseBody List<Map<String,Object>> getUser(@RequestParam String userID) {
        Queries q = new Queries();

        return q.getUser(userID);
    }

    @GetMapping(path="/getCourses")
    public @ResponseBody List<Map<String,Object>> getCourses() {
        Queries q = new Queries();

        return q.getCourses();
    }

    @GetMapping(path="/getUserSuggestions")
    public @ResponseBody List<Map<String,Object>> getUserSuggestions(@RequestParam String studiengang_name, @RequestParam String company_name,
                                                                     @RequestParam String course_name, @RequestParam String lecture_name,
                                                                     @RequestParam String minimum_lecture_proficiency, @RequestParam String email,
                                                                     @RequestParam String first_name, @RequestParam String last_name) {
        Queries q = new Queries();

        return q.getUserSuggestions(studiengang_name, company_name, course_name,
                lecture_name, minimum_lecture_proficiency, email, first_name, last_name);
    }

    @GetMapping(path="/getLecturesFromUser")
    public @ResponseBody List<Map<String,Object>> getLecturesFromUser(@RequestParam String userID) {
        Queries q = new Queries();

        return q.getLecturesFromUser(userID);
    }

    @PostMapping(path="/addDozent") // Map ONLY POST Requests
    public @ResponseBody String addDozent (@RequestParam String userID, @RequestParam String Biography
            , @RequestParam String Special_Role, @RequestParam List<String> titles) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Queries q = new Queries();

        boolean success = q.addDozent(userID, Biography, Special_Role, titles);

        String out = success ? "Success" : "Fail";

        return out;
    }

    @PostMapping(path="/addStudent") // Map ONLY POST Requests
    public @ResponseBody String addStudent (@RequestParam String userID, @RequestParam String Biography,
                                            @RequestParam String Course_Name, @RequestParam String companyID,
                                            @RequestParam String companyLocation, @RequestParam String hochschuleID,
                                            @RequestParam Date startDate, @RequestParam Date graduationDate) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Queries q = new Queries();

        boolean success = q.addStudent(userID, Biography, Course_Name, companyID, companyLocation, hochschuleID, startDate, graduationDate);

        String out = success ? "Success" : "Fail";

        return out;
    }

    @PostMapping(path="/addReadLecture") // Map ONLY POST Requests
    public @ResponseBody String addReadLecture (@RequestParam String userID, @RequestParam String lectureName, @RequestParam String Course_Name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Queries q = new Queries();

        boolean success = q.addReadLecture(userID, lectureName, Course_Name);

        String out = success ? "Success" : "Fail";

        return out;
    }

    @GetMapping(path="/getStudents")
    public @ResponseBody List<Map<String,Object>> getStudents() {
        Queries q = new Queries();

        return q.getStudents();
    }

    @GetMapping(path="/getDozenten")
    public @ResponseBody List<Map<String,Object>> getDozenten() {
        Queries q = new Queries();

        return q.getDozenten();
    }

    @PostMapping(path="/verifyUser")
    public @ResponseBody String[] verifyUser(@RequestParam String verificationKey){ //returns Array [Success "Valid Key"/"Fail", target/reason]
        Queries q = new Queries();

        return q.verifyUser(verificationKey);
    }

    @PostMapping(path="/generateVerificationKey")
    public @ResponseBody String generateVerificationKey(@RequestParam String userID, @RequestParam String target){
        Queries q = new Queries();

        String[] successMailKey = q.generateVerificationKey(userID, target);

        boolean mailSuccess = false;

        if(successMailKey[0].equals("Success")) {
            mailSuccess = sendVerificationEmail(successMailKey[1], successMailKey[2], target);
        }

        return mailSuccess ? successMailKey[0] : successMailKey[0]+"; "+"Email failed to send";
    }

    @Autowired
    private EmailServiceImpl esi;

    private boolean sendVerificationEmail(String email, String key, String target) {
        return esi.sendTemplatedMessage(email, "StudConnect Verification", target, key);
    }

    @PostMapping(path="/updateUser") // Map ONLY POST Requests
    public @ResponseBody String[] updateUser (@RequestParam String userID, @RequestParam String first_name, @RequestParam String last_name
            , @RequestParam String email, @RequestParam String encrypted_password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Queries q = new Queries();


        return q.updateUser(userID, first_name, last_name, email, encrypted_password);
    }

    @GetMapping(path="/getInfos")
    public @ResponseBody List<Map<String,Object>> getInfos() {
        Queries q = new Queries();

        return q.getInfos();
    }

}
