package lamzone.mareu.meetingsList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lamzone.mareu.R;
import lamzone.mareu.model.Meeting;
import lamzone.mareu.utils.MeetingDiffCallback;


public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {

    // FOR DATA ---
    private List<Meeting> mMeeting = new ArrayList<>();

    // FOR CALLBACK ---
    private final Listener callback;
    public interface Listener {
        void onClickDelete(Meeting meeting);

    }

    public MeetingsRecyclerViewAdapter(Listener callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeeting.get(position);
        holder.circleMeeting.setImageResource(meeting.getImageResourceId());
        holder.aboutMeeting.setText(meeting.getAboutMeeting());
        holder.hourMeeting.setText(meeting.getHour());
        holder.locationMeeting.setText(meeting.getLocation());
        holder.mailsMeeting.setText(meeting.getMails());

        holder.deleteMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickDelete(meeting);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }


    public void updateList(List<Meeting> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MeetingDiffCallback(newList, this.mMeeting));
        this.mMeeting = new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_list_circle_meeting)
        public ImageView circleMeeting;
        @BindView(R.id.item_list_aboutMeeting)
        public TextView aboutMeeting;
        @BindView(R.id.item_list_hour)
        public TextView hourMeeting;
        @BindView(R.id.item_list_location)
        public TextView locationMeeting;
        @BindView(R.id.item_list_mails)
        public TextView mailsMeeting;
        @BindView(R.id.item_list_delete_button)
        public ImageButton deleteMeeting;



        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}