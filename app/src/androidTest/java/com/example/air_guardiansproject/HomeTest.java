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


public class HomeTest {

    @Rule
    public ActivityTestRule<Home> HomeTestRule = new ActivityTestRule<Home>(Home.class);

    private Home home = null;

    Instrumentation.ActivityMonitor monitorFavorites = getInstrumentation().addMonitor(FavouritesActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorInformation = getInstrumentation().addMonitor(InformationActivity2.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorExit = getInstrumentation().addMonitor(LogInActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        home = HomeTestRule.getActivity();
    }
    @Test
    public void testMap(){
        assertNotNull(home.findViewById(R.id.map));
    }

    @Test
    public void testLaunchFavorites(){
        assertNotNull(home.findViewById(R.id.favourites));
        onView(withId((R.id.favourites))).perform(click());
        Activity FavoritesActivity = getInstrumentation().waitForMonitorWithTimeout(monitorFavorites, 5000);
        assertNotNull(FavoritesActivity);
    }

    @Test
    public void testLaunchInfo(){
        assertNotNull(home.findViewById(R.id.info));
        onView(withId((R.id.info))).perform(click());
        Activity InformationActivity2 = getInstrumentation().waitForMonitorWithTimeout(monitorInformation, 5000);
        assertNotNull(InformationActivity2);
    }

    @Test
    public void testLaunchExit(){
        assertNotNull(home.findViewById(R.id.exit));
        onView(withId((R.id.exit))).perform(click());
        Activity ExitActivity = getInstrumentation().waitForMonitorWithTimeout(monitorExit, 5000);
        assertNotNull(ExitActivity);
    }



    @After
    public void tearDown() throws Exception {
    }
}