package lamzone.mareu.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lamzone.mareu.R;
import lamzone.mareu.model.Meeting;


public abstract class MeetingsGenerator {

    public static List<Meeting> MEETINGS = Arrays.asList(
            new Meeting(1, R.drawable.reunion_e, "Réunion A", "08/06/2020",
                    "14H00", "Peach", "maxime@lamzone.com, alexandra@lamzone.com, francis@lamzone.com"),
            new Meeting(2, R.drawable.reunion_b, "Réunion B", "12/06/2020",
                    "16h00", "Mario", "paul@lamzone.com, viviane@lamzone.com, " +
                    "maxime@lamzone.com, alexandra@lamzone.com"),
            new Meeting(3, R.drawable.reunion_c, "Réunion C", "20/06/2020",
                    "19H00", "Luigi", "amandine@lamzone.com, " +
                    "luc@lamzone.com, maxime@lamzone.com"));


    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }


    public static HashMap<Integer, Integer> Colors() {

        HashMap<Integer, Integer> colors = new HashMap<>();

        colors.put(4, R.drawable.reunion_d);
        colors.put(5, R.drawable.reunion_a);
        colors.put(6, R.drawable.reunion_f);
        colors.put(7, R.drawable.reunion_g);
        colors.put(8, R.drawable.reunion_h);
        colors.put(9, R.drawable.reunion_i);
        colors.put(10, R.drawable.reunion_j);

        return colors;
    }
}

