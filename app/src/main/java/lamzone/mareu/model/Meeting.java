package lamzone.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;


/**
 * Model object representing a Neighbour
 */
public class Meeting implements Parcelable{

    /** Identifier */
    private long id;

    private int imageResourceId;

    /** about Meeting */
    private String aboutMeeting;

    /** Date meeting */
    private String date;

    /** Hour meeting */
    private String hour;

    /** Adress */
    private String location;

    /** mails */
    private String mails;

    /**
     * Constructor
     * @param id
     * @param imageResourceId
     * @param aboutMeeting
     * @param date
     * @param hour
     * @param location
     * @param mails
     */
    public Meeting(long id, int imageResourceId,String aboutMeeting,
                   String date, String hour, String location, String mails) {
        this.id = id;
        this.imageResourceId = imageResourceId;
        this.aboutMeeting = aboutMeeting;
        this.date = date;
        this.hour = hour;
        this.location = location;
        this.mails = mails;
    }

    public Meeting(long id, int imageResourceId){
        this.id = id;
        this.imageResourceId = imageResourceId;
    }

    protected Meeting(Parcel in) {
        id = in.readLong();
        imageResourceId = in.readInt();
        aboutMeeting = in.readString();
        date = in.readString();
        hour = in.readString();
        location = in.readString();
        mails = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImageResourceId() { return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getAboutMeeting() {
        return aboutMeeting;
    }

    public void setAboutMeeting(String aboutMeeting) {
        this.aboutMeeting = aboutMeeting;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date;}

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(imageResourceId);
        dest.writeString(aboutMeeting);
        dest.writeString(date);
        dest.writeString(hour);
        dest.writeString(location);
        dest.writeString(mails);
    }
}
