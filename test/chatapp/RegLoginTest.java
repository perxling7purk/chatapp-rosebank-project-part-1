/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author paulj
 */
public class RegLoginTest {
    
    public RegLoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
     @Test
    public void testCheckUserNameCorrect() {

        RegLogin user = new RegLogin(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(user.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrect() {

        RegLogin user = new RegLogin(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(user.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityCorrect() {

        RegLogin user = new RegLogin(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(user.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityIncorrect() {

        RegLogin user = new RegLogin(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(user.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberCorrect() {

        RegLogin user = new RegLogin(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(user.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrect() {

        RegLogin user = new RegLogin(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(user.checkCellPhoneNumber("08966553"));
    }
    @Test
public void testRegisterUser() {

    RegLogin user = new RegLogin(
            "kyl_1",
            "Ch&&sec@ke99!",
            "+27838968976",
            "Kyle",
            "Smith"
    );

    assertEquals(
            "Username successfully captured.\n"
            + "Password successfully captured.\n"
            + "Cell phone number successfully added.\n"
            + "User registered successfully.",
            user.registerUser()
    );
}
@Test
public void testLoginSuccessful() {

    RegLogin user = new RegLogin(
            "kyl_1",
            "Ch&&sec@ke99!",
            "+27838968976",
            "Kyle",
            "Smith"
    );

    boolean result = user.loginUser(
            "kyl_1",
            "Ch&&sec@ke99!"
    );

    assertTrue(result);
}
@Test
public void testLoginFailed() {

    RegLogin user = new RegLogin(
            "kyl_1",
            "Ch&&sec@ke99!",
            "+27838968976",
            "Kyle",
            "Smith"
    );

    boolean result = user.loginUser(
            "kyl_1",
            "wrongpassword"
    );

    assertFalse(result);
}
@Test
public void testReturnLoginStatusSuccessful() {

    RegLogin user = new RegLogin(
            "kyl_1",
            "Ch&&sec@ke99!",
            "+27838968976",
            "Kyle",
            "Smith"
    );

    assertEquals(
            "Welcome Kyle, Smith it is great to see you again.",
            user.returnLoginStatus(true)
    );
}
@Test
public void testReturnLoginStatusFailed() {

    RegLogin user = new RegLogin(
            "kyl_1",
            "Ch&&sec@ke99!",
            "+27838968976",
            "Kyle",
            "Smith"
    );

    assertEquals(
            "Username or password incorrect, please try again.",
            user.returnLoginStatus(false)
    );
}
}