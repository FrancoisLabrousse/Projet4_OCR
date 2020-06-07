package lamzone.mareu.di;

import lamzone.mareu.services.MeetingsApiService;

public class DI {

    private static MeetingsApiService service = new MeetingsApiService();

    public static MeetingsApiService getMeetingsApiService() {
        return service;
    }

    public static MeetingsApiService getNewInstanceApiService() {
        return new MeetingsApiService();
    }
}
