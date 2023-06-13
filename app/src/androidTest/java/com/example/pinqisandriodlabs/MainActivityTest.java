package com.example.pinqisandriodlabs;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        // load the EditText as a "ViewInteraction" object
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));

        //tells the testing library to type "12345" into the editText.
        appCompatEditText.perform(replaceText("12345"));

        // find the Login button
        ViewInteraction materialButton = onView( withId(R.id.button));
        //  tells the test software to click on the button
        materialButton.perform(click());

        // checks that the text matches "You shall not pass!"
        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password shall not pass!")));
    }

    /**
     * this is a test for when password entered is missing uppercase character
     */
    @Test
    public void testFindMissingUpperCase(){

        // load the EditText as a "ViewInteraction" object
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));

        //tells the testing library to type "1234test@#" into the editText.
        appCompatEditText.perform(replaceText("1234test@#"));

        // find the Login button
        ViewInteraction materialButton = onView( withId(R.id.button));
        //  tells the test software to click on the button
        materialButton.perform(click());

        // checks that the text matches "You shall not pass!"
        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password shall not pass!")));

    }

    /**
     * this is a test for when password entered is missing Lowercase character
     */

    @Test
    public void testFindMissingLowerCase(){

        // load the EditText as a "ViewInteraction" object
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));

        //tells the testing library to type "1234TEST@#" into the editText.
        appCompatEditText.perform(replaceText("1234TEST@#"));

        // find the Login button
        ViewInteraction materialButton = onView( withId(R.id.button));
        //  tells the test software to click on the button
        materialButton.perform(click());

        // checks that the text matches "You shall not pass!"
        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password shall not pass!")));

    }


    /**
     * this is a test for when password entered is missing number character
     */
    @Test
    public void testFindMissingNumber(){

        // load the EditText as a "ViewInteraction" object
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));

        //tells the testing library to type "Test@#" into the editText.
        appCompatEditText.perform(replaceText("Test@#"));

        // find the Login button
        ViewInteraction materialButton = onView( withId(R.id.button));
        //  tells the test software to click on the button
        materialButton.perform(click());

        // checks that the text matches "You shall not pass!"
        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password shall not pass!")));

    }

    /**
     * this is a test for when password entered is missing special character
     */
    @Test
    public void testFindMissingSpeChar(){

        // load the EditText as a "ViewInteraction" object
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));

        //tells the testing library to type "Test123" into the editText.
        appCompatEditText.perform(replaceText("Test123"));

        // find the Login button
        ViewInteraction materialButton = onView( withId(R.id.button));
        //  tells the test software to click on the button
        materialButton.perform(click());

        // checks that the text matches "You shall not pass!"
        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password shall not pass!")));

    }


    /**
     * this is a test for when password entered is complex enough
     */
    @Test
    public void testComplexPassword(){

        // load the EditText as a "ViewInteraction" object
        ViewInteraction appCompatEditText = onView(withId(R.id.editTextText));

        //tells the testing library to type "Test123" into the editText.
        appCompatEditText.perform(replaceText("Test123@#"));

        // find the Login button
        ViewInteraction materialButton = onView( withId(R.id.button));
        //  tells the test software to click on the button
        materialButton.perform(click());

        // checks that the text matches "You shall not pass!"
        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("Your password meets the requirements.")));

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
