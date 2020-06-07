package lamzone.mareu.meetingsList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import lamzone.mareu.R;
import lamzone.mareu.di.DI;
import lamzone.mareu.events.AddNewMeeting;
import lamzone.mareu.model.Meeting;
import lamzone.mareu.services.ApiService;
import lamzone.mareu.utils.DateFilter;


public class MainActivity extends AppCompatActivity implements MeetingsRecyclerViewAdapter.Listener {

    private static final int REQUEST_CODE = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ApiService mApiService;
    private RecyclerView mRecyclerView;
    private MeetingsRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingsApiService();
        configureFab();
        configureRecyclerView();
        setSupportActionBar(toolbar);
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return True if one of the defined items was clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = mApiService.getMeetings().size();
        switch (item.getItemId()) {
            case R.id.date_filter:
                Intent intent = new Intent(MainActivity.this, DateFilter.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.peach:
                itemMenu(item, i, "Peach");
                break;
            case R.id.mario:
                itemMenu(item, i, "Mario");
                break;
            case R.id.luigi:
                itemMenu(item, i, "Luigi");
                break;
            case R.id.bowser:
                itemMenu(item, i, "Bowser");
                break;
            case R.id.yoshi:
                itemMenu(item, i, "Yoshi");
                break;
            case R.id.toad:
                itemMenu(item, i, "Toad");
                break;
            case R.id.wario:
                itemMenu(item, i, "Wario");
                break;
            case R.id.daisy:
                itemMenu(item, i, "Daisy");
                break;
            case R.id.walmigi:
                itemMenu(item, i, "Walmigi");
                break;
            case R.id.toadette:
                itemMenu(item, i, "Toadette");
                break;
            case R.id.reset_btn:
                resetFilter();
                break;
            case R.id.reset_filter:
                resetFilter();
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetFilter() {
        resetFilterDate();
        adapter.updateList(mApiService.getMeetings());
    }

    private void itemMenu(MenuItem item, int i, String name) {
        item.setChecked(true);
        locationMenu(name);
        adapter.updateList(mApiService.getMeetingsLocationFilters());
        messageFilter();
        if (mApiService.getMeetings().size() == i) {
            resetFilterLocation(name);
        }
    }

    /**
     * retrieve the dates in the date filter and launch the conditional filter function
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String filterToDate = data.getStringExtra("filterToDate");
            String filterFromDate = data.getStringExtra("filterFromDate");
            dateComparator(filterToDate, filterFromDate);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void configureRecyclerView() {
        mRecyclerView = findViewById(R.id.meeting_list);
        adapter = new MeetingsRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * Bouton de création de réunion
     * Intent vers la classe AddNewMeeting pour récupérer l'ID afin d'envoyer un cercle coloré
     * avec une condition if en cas de suppression des trois premiers elements.
     */
    private void configureFab() {
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddNewMeeting.class);

            if (mApiService.getMeetings().size() < 3) {
                intent.putExtra("id", 3);
            } else {
                intent.putExtra("id", mApiService.getMeetings().size());
            }
            startActivity(intent);
        });
    }

    /**
     * update the list according to the date filter, otherwise display the initial meetings
     */
    private void loadData() {
        if (mApiService.getMeetingsDate().size() != 0) {
            adapter.updateList(mApiService.getMeetingsDate());
        } else {
            adapter.updateList(mApiService.getMeetings());
        }
    }

    /**
     * delete meeting
     *
     * @param meeting
     */
    @Override
    public void onClickDelete(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
        loadData();
    }

    /**
     * adding the meeting to the dedicated list for the filter
     *
     * @param meeting
     */
    public void addLocationFilter(Meeting meeting) {
        mApiService.addLocationFilter(meeting);
    }

    /**
     * if the place sought corresponds to an existing meeting,
     * then we add the meeting in the list dedicated to the filter
     *
     * @param text
     */
    private void locationMenu(String text) {
        for (int i = 0; i < mApiService.getMeetings().size(); i++) {
            if (mApiService.getMeetings().get(i).getLocation().equals(text)) {
                addLocationFilter(mApiService.getMeetings().get(i));
            }
        }
    }

    /**
     * message if no meeting requested in the filter exists
     */
    private void messageFilter() {
        if (mApiService.getMeetingsLocationFilters().isEmpty()) {
            Toast.makeText(this, "Aucune réunion n'existe pour le lieu demandé",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * initialize meeting place filter list
     *
     * @param text
     */
    private void resetFilterLocation(String text) {
        for (int i = 0; i < mApiService.getMeetingsLocationFilters().size(); i++) {
            if (mApiService.getMeetingsLocationFilters().get(i).getLocation().equals(text)) {
                mApiService.deleteLocationFilter(mApiService.getMeetingsLocationFilters().get(i));
            }
        }
    }

    /**
     * add the meeting to the meeting list if filter ok
     *
     * @param
     */
    private void addDateFilter(Meeting meeting) {
        mApiService.addDateFilter(meeting);
    }

    private void dateComparator(String filterToDate, String filterFromDate) {

        for (int i = 0; i < mApiService.getMeetings().size(); i++) {

            String getDate = mApiService.getMeetings().get(i).getDate();
            String getDay = getDate.substring(0, 2);
            Integer getIntDay = Integer.parseInt(getDay);
            String getMonth = getDate.substring(3, 5);
            Integer getIntMonth = Integer.parseInt(getMonth);
            String getDayFilterToDate = filterToDate.substring(0, 2);
            Integer getIntDayFilterToDate = Integer.parseInt(getDayFilterToDate);
            String getMonthFilterToDate = filterToDate.substring(3, 5);
            Integer getIntMonthFilterToDate = Integer.parseInt(getMonthFilterToDate);
            String getDayFilterFromDate = filterFromDate.substring(0, 2);
            Integer getIntDayFilterFromDate = Integer.parseInt(getDayFilterFromDate);
            String getMonthFilterFromDate = filterFromDate.substring(3, 5);
            Integer getIntMonthFilterFromDate = Integer.parseInt(getMonthFilterFromDate);

            if (getIntDay > getIntDayFilterToDate && getIntMonth == getIntMonthFilterToDate &&
                    getIntMonth == getIntMonthFilterFromDate && getIntDay < getIntDayFilterFromDate) {
                addDateFilter(mApiService.getMeetings().get(i));
            } else if (getIntMonth > getIntMonthFilterToDate && getIntMonth <= getIntDayFilterFromDate) {
                addDateFilter(mApiService.getMeetings().get(i));
            }
        }
    }

    private void resetFilterDate() {
        for (int i = 0; i < mApiService.getMeetingsDate().size(); i++)
            if (mApiService.getMeetingsDate().size() != 0) {
                mApiService.deleteDateFilter(mApiService.getMeetingsDate().get(i));
            }
    }
}



