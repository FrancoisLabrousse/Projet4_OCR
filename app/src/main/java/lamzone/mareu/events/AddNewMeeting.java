package lamzone.mareu.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

import lamzone.mareu.R;
import lamzone.mareu.di.DI;

import lamzone.mareu.model.Meeting;
import lamzone.mareu.services.ApiService;
import lamzone.mareu.services.MeetingsGenerator;
import lamzone.mareu.utils.DatePickerFragment;
import lamzone.mareu.utils.TimePickerFragment;

public class AddNewMeeting extends AppCompatActivity {

    @BindView(R.id.about_Meeting)
    TextInputEditText aboutMeeting;
    @BindView(R.id.meeting_location)
    AutoCompleteTextView location;
    @BindView(R.id.date)
    TextView dateMeeting;
    @BindView(R.id.time)
    TextView hourMeeting;
    @BindView(R.id.mails_chip)
    ChipGroup mailsChip;
    @BindView(R.id.maxime_chip)
    Chip chipMaxime;
    @BindView(R.id.viviane_chip)
    Chip chipViviane;
    @BindView(R.id.amandine_chip)
    Chip chipAmandine;
    @BindView(R.id.alexandra_chip)
    Chip chipAlexandra;
    @BindView(R.id.luc_chip)
    Chip chipLuc;
    @BindView(R.id.francois_chip)
    Chip chipFrancois;
    @BindView(R.id.mails)
    EditText mEditTextChip;
    @BindView(R.id.create)
    Button addMeeting;

    private Meeting meeting;
    private ApiService mApiService;
    private View.OnClickListener mClickListener;


    /**
     * Handles the button click to create a new date picker fragment and
     * * show it.
     *
     * @param view
     */
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Process the date picker result into strings that can be displayed in
     * * a Toast.
     *
     * @param year
     * @param month
     * @param dayOfMonth
     */
    public void processDatePickerResult(int year, int month, int dayOfMonth) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(dayOfMonth);
        String year_string = Integer.toString(year);

        if ( dayOfMonth < 10) {
            day_string = "0" + day_string;
        }

        if ( month < 10 ) {
            month_string = "0"+ month_string;
        }

        String dateMessage = (day_string + "-" + month_string + "-" + year_string);

        Toast.makeText(this, "Date :" + dateMessage, Toast.LENGTH_SHORT).show();

        dateMeeting.setText(dateMessage);
    }

    /**
     * Handles the button click to create a new time picker fragment and
     * show it.
     *
     * @param view View that was clicked
     */
    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.timepicker));
    }

    /**
     * Process the time picker result into strings that can be displayed in
     * a Toast.
     *
     * @param hourOfDay Chosen hour
     * @param minute    Chosen minute
     */
    public void processTimePickerResult(int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.

        if ( minute < 10){
            minute_string = "0"+ minute_string;
        }

        if (hourOfDay < 10){
            hour_string = "0"+ hour_string;
        }

        String timeMessage = (hour_string + "H" + minute_string);

        Toast.makeText(this, getString(R.string.time_toast)
                + timeMessage, Toast.LENGTH_SHORT).show();

        hourMeeting.setText(timeMessage);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getMeetingsApiService();

        chipListener();
        spinnerLocation();
        addMeeting();

    }

    /**
     * creation of a new meeting
     */
    private void addMeeting() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0) + 1;

        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                meeting = new Meeting((long) id, MeetingsGenerator.Colors().get(id),
                        aboutMeeting.getText().toString(), dateMeeting.getText().toString(),
                        hourMeeting.getText().toString(),
                        location.getText().toString(), mEditTextChip.getText().toString());

                mApiService.createMeeting(meeting);
                finish();
            }
        });
    }

    /**
     * spinner for the choice of the meeting place
     */
    private void spinnerLocation() {
        String[] MEETINGLOCATION = new String[]{"Peach", "Mario", "Luigi", "Bowser", "Yoshi", "Toad", "Wario", "Daisy", "Walmigi", "Toadette"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        R.layout.support_simple_spinner_dropdown_item,
                        MEETINGLOCATION);

        AutoCompleteTextView editTextFilledExposedDropdown =
                this.findViewById(R.id.meeting_location);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Listener mails chips
     */
    private void chipListener() {
        this.mClickListener = new ClickListenerChip();
        this.chipMaxime.setOnClickListener(mClickListener);
        this.chipViviane.setOnClickListener(mClickListener);
        this.chipAmandine.setOnClickListener(mClickListener);
        this.chipAlexandra.setOnClickListener(mClickListener);
        this.chipLuc.setOnClickListener(mClickListener);
        this.chipFrancois.setOnClickListener(mClickListener);
    }

    private void appendChip(String text) {
        this.mEditTextChip.append(text);
        this.mEditTextChip.append(",");
    }

    /**
     * retrieve the email address by clicking on chip
     */
    private class ClickListenerChip implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Chip chip = (Chip) v;
            appendChip(chip.getText().toString());

        }
    }
}
