package lamzone.mareu.utils;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import lamzone.mareu.model.Meeting;

public class MeetingDiffCallback extends DiffUtil.Callback {

    private final List<Meeting> oldMeeting;
    private final List<Meeting> newMeeting;

    public MeetingDiffCallback(List<Meeting> newMeeting, List<Meeting> oldMeeting) {
        this.newMeeting = newMeeting;
        this.oldMeeting = oldMeeting;
    }

    @Override
    public int getOldListSize() {
        return oldMeeting.size();
    }

    @Override
    public int getNewListSize() {
        return newMeeting.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMeeting.get(oldItemPosition).getId() == newMeeting.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMeeting.get(oldItemPosition).equals(newMeeting.get(newItemPosition));
    }
}

