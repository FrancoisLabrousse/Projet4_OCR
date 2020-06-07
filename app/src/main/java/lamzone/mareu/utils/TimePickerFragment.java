/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lamzone.mareu.utils;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import lamzone.mareu.events.AddNewMeeting;


/**
 * A simple {@link Fragment} subclass for the time picker.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {


    /**
     * Creates the time picker dialog with the current time from Calendar.
     *
     * @param savedInstanceState    Saved instance state bundle.
     
     * @return TimePickerDialog     The time picker dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    /**
     * Grabs the time and converts it to a string to pass
     * to the Main Activity in order to show it with processTimePickerResult().
     *
     * @param timePicker    The time picker view
     * @param hourOfDay     The hour chosen
     * @param minute        The minute chosen
     */
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        AddNewMeeting activity = (AddNewMeeting) getActivity();
        activity.processTimePickerResult(hourOfDay, minute);
    }
}
