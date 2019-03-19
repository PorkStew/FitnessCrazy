package com.example.stewa.fitnesscrazy01;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment implements SensorEventListener {

        //Variable Declaration
        private DrawerLayout drawerLayout;
        NavigationView navigationView;
        private Button Bstart, Bstop;
        TextView Lsteps, LalreadyDone;
        double mLastX = 0;
        double mLastZ = 0;
        double mLastY = 0;
        private final float NOISE = (float) 1.0;
        int stepsCount = 0;
        private boolean mInitialized; // used for initializing sensor only once
        private SensorManager mSensorManager;
        private Sensor mAccelerometer;

    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        //create connection to database and make table and search table
        final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        final SQLiteDatabase database = contactDbHelper.getWritableDatabase();
        contactDbHelper.MakeTableSteps(database);
        contactDbHelper.searchForStepsDoneByUser(database);

        //link buttons and labels to variables
        LalreadyDone = (TextView) view.findViewById(R.id.LalreadyGotData);
        Lsteps = (TextView) view.findViewById(R.id.stepslabel);
        Bstart = view.findViewById(R.id.Bstart);
        Bstop = view.findViewById(R.id.Bstop);

        //inizalize sensors for data collection
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        startSensor();

        //check if user has collected their steps for the day
        if(ContactContact.ContactEntry.showSteps == true)
        {
            Bstop.setEnabled(false);
            Bstart.setEnabled(false);
            LalreadyDone.setText("Steps Already Collected Today");
        }
        //gets data when start is clicked
        Bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove this when done application used on real phone
                //ContactContact.ContactEntry.setUSER_NAMEs("Pork");
              if(ContactContact.ContactEntry.showSteps == false) {
                 mSensorManager.registerListener(StepsFragment.this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                  Toast.makeText(getActivity(), "Tracking has started!!", Toast.LENGTH_SHORT).show();
                  Bstart.setEnabled(false);
                }
            }
        });
        Bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSensorManager.unregisterListener(StepsFragment.this);
                String steps = Lsteps.getText().toString();
                System.out.println(steps);
                // make 0 when implemeting but 1 for test on emulator
                if(steps.contentEquals("1"))
                {
                    Toast.makeText(getActivity(), "Not enough steps, press start!!!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    contactDbHelper.addUserSteps(steps, database);
                    LalreadyDone.setText("Steps Collected Today");
                    Toast.makeText(getActivity(), "Steps for today have been collected!", Toast.LENGTH_SHORT).show();
                    Bstart.setEnabled(false);
                    Bstop.setEnabled(false);
                }
            }
        });
        //inizalizes navigation drawer
        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);

        //navigation drawer listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.toString().equals("Home")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MenuFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Profile")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ProfileFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Track Steps")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new StepsFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Weight Progress")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WeightProgressFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Height Progress")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HeightProgressFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Goals")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GoalsFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Weight Log")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LogsFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Height Log")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HeightFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Photos")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PhotoFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("Logout")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MainLoginFragment()).addToBackStack(null).commit();
                }
                if (menuItem.toString().equals("About")) {
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AboutFragment()).addToBackStack(null).commit();
                }
                menuItem.setChecked(true);
                return false;
            }
        });
        return view;
    }
    private void startSensor() {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
// event object contains values of acceleration, read those
        double x = event.values[0];
        double y = event.values[1];
        double z = event.values[2];
        final double alpha = 0.8; // constant for our filter below

        double[] gravity = {0,0,0};

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

// Remove the gravity contribution with the high-pass filter.
        x = event.values[0] - gravity[0];
        y = event.values[1] - gravity[1];
        z = event.values[2] - gravity[2];
        if (!mInitialized) {
            // sensor is used for the first time, initialize the last read values
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            mInitialized = true;
        } else {
            // sensor is already initialized, and we have previously read values.
            // take difference of past and current values and decide which
            // axis acceleration was detected by comparing values
            double deltaX = Math.abs(mLastX - x);
            double deltaY = Math.abs(mLastY - y);
            double deltaZ = Math.abs(mLastZ - z);
            if (deltaX < NOISE)
                deltaX = (float) 0.0;
            if (deltaY < NOISE)
                deltaY = (float) 0.0;
            if (deltaZ < NOISE)
                deltaZ = (float) 0.0;
            mLastX = x;
            mLastY = y;
            mLastZ = z;

            if (deltaX > deltaY) {
                // Horizontal shake
                // do something here if you like

            } else if (deltaY > deltaX) {
                // Vertical shake
                // do something here if you like

            } else if ((deltaZ > deltaX) && (deltaZ > deltaY)) {
                // Z shake
                stepsCount = stepsCount + 1;
                if (stepsCount > 0) {
                    Lsteps.setText(String.valueOf(stepsCount));
                }
        }
    }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
