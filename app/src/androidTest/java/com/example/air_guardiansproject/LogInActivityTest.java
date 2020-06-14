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


public class LogInActivityTest {

    @Rule
    public ActivityTestRule<LogInActivity> logInActivityTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);

    private LogInActivity logInActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Home.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        logInActivity = logInActivityTestRule.getActivity();
    }


    @Test
    public void testLaunchHomeEmpty(){
        assertNotNull(logInActivity.findViewById(R.id.button));
       onView(withId((R.id.button))).perform(click());
        Activity Home = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNull(Home);
    }

    @Test
    public void testLaunchHomeCorrect(){
        assertNotNull(logInActivity.findViewById(R.id.button));
        onView(withId(R.id.email_login)).perform(typeText("flavius@yahoo.com"));
        onView(withId(R.id.password_login)).perform(typeText("123"));
        onView(withId((R.id.button))).perform(click());
        Activity Home = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(Home);
    }


    @After
    public void tearDown() throws Exception {
    }
}