package com.example.air_guardiansproject;

import android.app.Activity;
import android.app.Instrumentation;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


public class RegisterTest {
    @Rule
    public ActivityTestRule<Register> RegisterTestRule = new ActivityTestRule<Register>(Register.class);

    private Register register = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LogInActivity.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        register = RegisterTestRule.getActivity();
    }

    @Test
    public void testRegisterEmpty(){
        assertNotNull(register.findViewById(R.id.button_register));
        onView(withId((R.id.button_register))).perform(click());
        Activity logInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNull(logInActivity);
    }

    @Test
    public void testLaunchHomeCorrect(){
        assertNotNull(register.findViewById(R.id.button_register));
        onView(withId(R.id.email_register)).perform(typeText("some_email@yahoo.com"));
        onView(withId(R.id.password_register)).perform(typeText("123"));
        onView(withId(R.id.confirm_password_register)).perform(typeText("1234"));
        onView(withId((R.id.button_register))).perform(click());
        Activity logInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNull(logInActivity);
    }

    @After
    public void tearDown() throws Exception {
    }
}