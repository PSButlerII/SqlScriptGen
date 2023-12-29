//package com.recondev.userinteraction;
//
//import com.recondev.helpers.Enums;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//class UserInteractionTest {
//
//    @Test
//    void getDatabaseType() {
//
//    }
//
//    @Test
//    void getTableName() {
//    }
//
//    @Test
//    void getColumns() {
//    }
//
//    @Test
//    void getConstraints() {
//    }
//
//    @Test
//    void simulateUserInputForCreatingTable() {
//        String simulatedUserInput =
//                "1\n" +          // Database type: PostgreSQL
//                        "numbers\n" +    // Table name: numbers
//                        "3\n" +          // Number of columns: 3
//                        "id\n" +         // Column 1: name
//                        "serial\n" +     // Column 1: type
//                        "section\n" +    // Column 2: name
//                        "number\n" +     // Column 2: type
//                        "value\n" +      // Column 3: name
//                        "decimal\n" +    // Column 3: type
//                        "yes\n" +        // Add constraints?
//                        "1\n" +          // Number of constraints: 1
//                        "FOREIGN KEY (value) REFERENCES member(membernumber)\n";  // Constraint
//
//        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
//        UserInteraction ui = new UserInteraction();
//
//        assertEquals(Enums.DatabaseType.POSTGRESQL, ui.getDatabaseType());
//        assertEquals("numbers", ui.getTableName());
//
//        Map<String, String> columns = ui.getColumns(Enums.DatabaseType.POSTGRESQL);
//        assertEquals(3, columns.size());
//        assertEquals("serial", columns.get("id"));
//        assertEquals("number", columns.get("section"));
//        assertEquals("decimal", columns.get("value"));
//
//        List<String> constraints = ui.getConstraints();
//        assertEquals(1, constraints.size());
//        assertEquals("FOREIGN KEY (value) REFERENCES member(membernumber)", constraints.get(0));
//
//        // Reset System.in to its original
//
//    }
//}
//
//
