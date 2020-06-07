package lamzone.mareu.services;

import java.util.ArrayList;
import java.util.List;

import lamzone.mareu.model.Meeting;

public class MeetingsApiService implements ApiService{

    private List<Meeting> mMeetings = MeetingsGenerator.generateMeetings();


    public List<Meeting> getMeetings(){ return mMeetings;}

    public void createMeeting(Meeting meeting) { mMeetings.add(meeting);}

    public void deleteMeeting(Meeting meeting) { mMeetings.remove(meeting);}


    /**
     * creation of a list for the location filter
     */
    private List<Meeting> mMeetingsLocation = new ArrayList<>();

    /**
     * adding the meeting to the location filter list
     * @param meeting
     */
    public void addLocationFilter(Meeting meeting) { mMeetingsLocation.add(meeting);}

    /**
     * get the new list by location
     * @return
     */
    public List<Meeting> getMeetingsLocationFilters(){ return mMeetingsLocation;}

    /**
     * remove the item from the list of location filters
     * @param meeting
     */
    public void deleteLocationFilter(Meeting meeting) { mMeetingsLocation.remove(meeting);}

    /**
     * creation of a list for the date filter
     */
    private List<Meeting> mMeetingsDate = new ArrayList<>();

    /**
     * adding the meeting to the date filter list
     * @param meeting
     */
    public void addDateFilter(Meeting meeting){ mMeetingsDate.add(meeting);}

    /**
     * get the new list by date
     * @return
     */
    public List<Meeting> getMeetingsDate() { return mMeetingsDate;}

    /**
     * remove the item from the list of date filters
     * @param meeting
     */
    public void deleteDateFilter(Meeting meeting){ mMeetingsDate.remove(meeting);}








}
