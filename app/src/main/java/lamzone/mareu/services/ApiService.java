package lamzone.mareu.services;

import java.util.List;

import lamzone.mareu.model.Meeting;

public interface ApiService {

    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    void addLocationFilter(Meeting meeting);

    List<Meeting> getMeetingsLocationFilters();

    void deleteLocationFilter(Meeting meeting);

    void addDateFilter(Meeting meeting);

    List<Meeting> getMeetingsDate();

    void deleteDateFilter(Meeting meeting);


}
