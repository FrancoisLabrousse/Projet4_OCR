package lamzone.mareu.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import lamzone.mareu.R;
import lamzone.mareu.di.DI;
import lamzone.mareu.meetingsList.MainActivity;
import lamzone.mareu.services.ApiService;

public class DateFilter extends AppCompatActivity {

    private static EditText toDate, fromDate;
    private SimpleDateFormat dateFormatter;
    private Button sendButton;
    private String filterToDate, filterFromDate;

    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_filter);
        toDate = (EditText) findViewById(R.id.toDate);
        fromDate = (EditText) findViewById(R.id.fromDate);
        toDate.setInputType(InputType.TYPE_NULL);
        fromDate.setInputType(InputType.TYPE_NULL);
        toDate.requestFocus();
        sendButton = (Button) findViewById(R.id.sendDateBtn);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        toDateListener(toDate);
        fromDateListener(fromDate);
        mApiService = DI.getMeetingsApiService();

        sendButtonFilterDate();
    }

    /**
     * Méthode pour récupérer la date de départ
     *
     * @param toDate
     */
    private void toDateListener(EditText toDate) {
        toDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //todo
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, month, dayOfMonth);
                                toDate.setText(dateFormatter.format(newDate.getTime()));

                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    /**
     * Méthode pour récupérer la date d'arrivée
     *
     * @param fromDate
     */
    private void fromDateListener(EditText fromDate) {
        toDateListener(fromDate);
    }


    private void sendButtonFilterDate() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterToDate = toDate.getText().toString().trim();
                filterFromDate = fromDate.getText().toString().trim();
                Intent intent = new Intent(DateFilter.this, MainActivity.class);
                intent.putExtra("filterToDate", filterToDate);
                intent.putExtra("filterFromDate", filterFromDate);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
