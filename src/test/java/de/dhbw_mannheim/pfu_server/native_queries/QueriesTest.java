package de.dhbw_mannheim.pfu_server.native_queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueriesTest {



    @Test
    void createUser() {
        Queries queries = new Queries();
        assertTrue(queries.createUser("abc", "def", "abcdef@student.dhbw-mannheim.de", "12345"));
    }

    @Test
    void createStudent() {
    }
}