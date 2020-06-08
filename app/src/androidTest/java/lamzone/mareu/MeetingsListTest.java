package lamzone.mareu;

import android.content.Context;
import android.view.Display;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import lamzone.mareu.meetingsList.MainActivity;
import lamzone.mareu.meetingsList.MeetingsRecyclerViewAdapter;
import lamzone.mareu.utils.DeleteViewAction;
import lamzone.mareu.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.openLinkWithText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static lamzone.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Test class for list of meetings
 */
@RunWith(AndroidJUnit4.class)
public class MeetingsListTest {

    private MainActivity mActivity;
<<<<<<< HEAD
    private static int ITEMS_COUNT = 4;
=======
    private static int ITEMS_COUNT = 3;
>>>>>>> 58cf40698b6f2f074d6872a3705dc190e19a9372

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingsList_shouldNotBeEmpty() {

        onView(withId(R.id.meeting_list))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void meetingList_deleteAction_shouldRemoveItem() {

        onView(withId(R.id.meeting_list)).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.meeting_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.meeting_list)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * Test qui vérifie la création d'une réunion et l'affichage correct des informations
     * saisies dans la liste des réunions.
     */
    @Test
    public void addNewMeetingWithSuccess(){

        onView(withId(R.id.meeting_list));
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.activity_add_meeting)).check(matches(isDisplayed()));
        onView(withId(R.id.about_Meeting)).perform(replaceText("Réunion D"));
<<<<<<< HEAD
        onView(withId(R.id.meeting_location)).perform(click(replaceText("Bowser")));
=======
        onView(withId(R.id.meeting_localisation)).perform(click(replaceText("Bowser")));
>>>>>>> 58cf40698b6f2f074d6872a3705dc190e19a9372
        onView(withId(R.id.hour_button)).perform(click());
        setTime(10, 0);
        onView(withId(R.id.maxime_chip)).perform(click());
        onView(withId(R.id.create)).perform(click());

        onView(withId(R.id.item_list_aboutMeeting)).equals("Réunion D");
<<<<<<< HEAD
        onView(withId(R.id.item_list_location)).equals("Bowser");
=======
        onView(withId(R.id.item_list_localisation)).equals("Bowser");
>>>>>>> 58cf40698b6f2f074d6872a3705dc190e19a9372
        onView(withId(R.id.item_list_hour)).equals("10H00");
        onView(withId(R.id.item_list_mails)).equals("maxime@lamzone.com");
    }

}
