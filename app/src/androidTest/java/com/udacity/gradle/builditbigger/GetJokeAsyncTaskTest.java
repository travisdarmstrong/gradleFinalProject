package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class GetJokeAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testAsyncTaskGetJoke(){
        onView(withId(R.id.tell_joke_button)).check(matches(isDisplayed()));
        onView(withId(R.id.tell_joke_button)).perform(click());

        onView(withId(R.id.joke_display_text)).check(matches(isDisplayed()));
        onView(withId(R.id.joke_display_text)).check(matches(not(withText(""))));
    }
}
