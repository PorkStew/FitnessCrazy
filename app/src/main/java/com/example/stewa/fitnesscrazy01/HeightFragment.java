package com.example.stewa.fitnesscrazy01;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeightFragment extends Fragment {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Button Binsert;
    EditText weight;
    TextView date, weights;

    public HeightFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Swipe right on the left side of the screen!!!", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_height, container, false);
        drawerLayout = view.findViewById(R.id.drawerLayout);

        final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        final SQLiteDatabase database = contactDbHelper.getWritableDatabase();

        contactDbHelper.CreateUserLogHeight(database);
        contactDbHelper.getUserInformationHeightLogs(database);

        date = view.findViewById(R.id.TVdate);
        weights = view.findViewById(R.id.TVweight);
        String dates = "";
        String weightss = "";

        TextView date = (TextView) view.findViewById(R.id.TVdate);
        TextView weights = (TextView) view.findViewById(R.id.TVweight);
        int iloop = 0;
        while(iloop< ContactContact.ContactEntry.NumberOfEntries)
        {
            dates = dates + ContactContact.ContactEntry.getDateArray() [iloop] + "\n";
            weightss = weightss + ContactContact.ContactEntry.getHeightArray() [iloop] + "\n";
            iloop++;
        }
        date.setText(dates);
        weights.setText(weightss);


        navigationView = (NavigationView) view.findViewById(R.id.nav_view);

        weight = view.findViewById(R.id.TXTBweightLost);


        Binsert = view.findViewById(R.id.Binsert);


        Binsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String Weight = weight.getText().toString();
                if (Weight.isEmpty()) {
                    Toast.makeText(getActivity(), "Missing information!!!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Weight entered successfully", Toast.LENGTH_SHORT).show();
            contactDbHelper.addUserHeightLog(Weight, database);

            }
        });


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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

}