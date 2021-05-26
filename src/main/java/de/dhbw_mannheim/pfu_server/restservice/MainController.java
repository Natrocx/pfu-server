package de.dhbw_mannheim.pfu_server.restservice;

import de.dhbw_mannheim.pfu_server.native_queries.Queries;
import de.dhbw_mannheim.pfu_server.native_queries.QueryManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
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
    public @ResponseBody List<Object[]> getAllUsers() {
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

        //return first_name + " " + last_name + " " + email + " " + encrypted_password + " " + out;
        return out;
    }


    /*
    Nachrichten abrufen: alle, ab einer Nachrichten-ID (also alles was danach kommt)
    DONE: Nachrichten schicken

    Registrierung,
    Login (Login-Daten überprüfen),
    Email-Verifizierung? (code verschicken und überprüfen).

    User info abrufen,
    random Vorschlag abrufen,
    KursListe abrufen
     */

}
