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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(LogInActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchLoginActivity(){
        assertNotNull(mainActivity.findViewById(R.id.button_login));

        onView(withId(R.id.button_login)).perform(click());

        Activity LogInActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(LogInActivity);
    }

    @After
    public void tearDown() throws Exception {
    }
}