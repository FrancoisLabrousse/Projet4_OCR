package lamzone.mareu;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import lamzone.mareu.di.DI;
import lamzone.mareu.model.Meeting;
import lamzone.mareu.services.ApiService;
import lamzone.mareu.services.MeetingsGenerator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private ApiService mService;
    private Meeting mMeeting;

    @Before
    public void setup() { mService = DI.getNewInstanceApiService();}

    /**
     * test to check the display of the initial meetings in the list
     */
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = mService.getMeetings();
        List<Meeting> expectedMeetings = MeetingsGenerator.MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     *  test to verify the addition of a new meeting to the list
     */
    @Test
    public void getAddMeetingWithSuccess(){
        // for example :
        Meeting addMeeting = new Meeting(4, R.drawable.reunion_j, "Réunion J",
                "15/06/2020", "10h00", "Toad", "francois@lamzone.com");

        mService.createMeeting(addMeeting);
        assertTrue(mService.getMeetings().contains(addMeeting));
    }

    /**
     * test to check the deletion of a meeting in the list
     */
    @Test
    public void deleteMeetingsWithSuccess(){
        Meeting meetingToDelete = mService.getMeetings().get(0);
        mService.deleteMeeting(meetingToDelete);
        assertFalse(mService.getMeetings().contains(meetingToDelete));
    }

    /**
     * test to check the location filter
     */
    @Test
    public void getLocationFilterWithSuccess(){
        // verification of the location of meeting A. We must find the location "Peach"
        Meeting meetingLocationFilter = mService.getMeetings().get(0);
        mService.addLocationFilter(meetingLocationFilter);
        assertTrue(mService.getMeetingsLocationFilters().contains(meetingLocationFilter));
        assertTrue(mService.getMeetingsLocationFilters().get(0).getLocation().equals("Peach"));
    }

    /**
     * test to check the date filter
     */
    @Test
    public void getDateFilterWithSuccess() {
        // verification of the date of meeting A. We must find the date : 08/06/2020
        Meeting meetingDateFilter = mService.getMeetings().get(0);
        mService.addDateFilter(meetingDateFilter);
        assertTrue(mService.getMeetingsDate().contains(meetingDateFilter));
        assertTrue(mService.getMeetingsDate().get(0).getDate().equals("08/06/2020"));
    }
}