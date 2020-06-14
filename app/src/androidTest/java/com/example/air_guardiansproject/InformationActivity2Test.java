package com.example.air_guardiansproject;

import android.app.Instrumentation;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class InformationActivity2Test {

    @Rule
    public ActivityTestRule<InformationActivity2> informationActivityTestRule = new ActivityTestRule<InformationActivity2>(InformationActivity2.class);
    private InformationActivity2 informationActivity = null;

    @Before
    public void setUp() throws Exception {
        informationActivity = informationActivityTestRule.getActivity();
    }

    @Test
    public void testInfoScreen(){
        assertNotNull(informationActivity.findViewById(R.id.team));
        assertNotNull(informationActivity.findViewById(R.id.motivation));
        assertNotNull(informationActivity.findViewById(R.id.technology));
        assertNotNull(informationActivity.findViewById(R.id.information));
    }

    @After
    public void tearDown() throws Exception {
    }
}