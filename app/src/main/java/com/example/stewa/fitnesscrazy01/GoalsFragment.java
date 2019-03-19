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
public class GoalsFragment extends Fragment {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Button BmakeGoal, Bupdate;
    EditText GoalWeightss, Goalstepsss;

    public GoalsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "Swipe right on the left side of the screen!!!", Toast.LENGTH_SHORT).show();
        setHasOptionsMenu(true);
        //database creation and information retrieval
        final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        final SQLiteDatabase database = contactDbHelper.getWritableDatabase();
        contactDbHelper.getUserInformationGoal(database);
        contactDbHelper.MakeTableGoals(database);

        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        //variable and button linking
        BmakeGoal = view.findViewById(R.id.BmakeGoal);
        Bupdate = view.findViewById(R.id.Bupdate);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        Goalstepsss = view.findViewById(R.id.TXTBgoalSteps);
        GoalWeightss = view.findViewById(R.id.TXTBgoalWeight);
        TextView GoalSteps = (TextView) view.findViewById(R.id.TXTBgoalSteps);
        TextView GoalWeight = (TextView) view.findViewById(R.id.TXTBgoalWeight);
        GoalSteps.setText(ContactContact.ContactEntry.getGoalStep());
        GoalWeight.setText(ContactContact.ContactEntry.getGoalWeights());
        String goalsteps = GoalSteps.getText().toString();
        String goalweight = GoalWeight.getText().toString();
        //onclicklistener for making the goal and checks if they havent left any goal blank and inserts the data into the database
        BmakeGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDbHelper.searchForGoal(database);
                if (ContactContact.ContactEntry.show == true) {
                    Toast.makeText(getActivity(), "you already have a goal to update plz click update", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String goalSteps = Goalstepsss.getText().toString();
                    String goalWeight = GoalWeightss.getText().toString();
                    if (goalSteps.isEmpty() || goalWeight.isEmpty()) {
                        Toast.makeText(getActivity(), "Nothing can be left empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    contactDbHelper.addGoal(ContactContact.ContactEntry.getUSER_NAMEs(), goalWeight, goalSteps, database);
                    Toast.makeText(getActivity(), "Goals Created", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //onclicklistener for updating goals and checks if nothing is left blank and then inserts into the database
        Bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDbHelper.searchForGoal(database);
                if (ContactContact.ContactEntry.show == true) {
                    final String goalsteps = Goalstepsss.getText().toString();
                    final String goalWeight = GoalWeightss.getText().toString();
                    if (goalsteps.isEmpty() || goalWeight.isEmpty()) {
                        Toast.makeText(getActivity(), "Nothing can be left empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    contactDbHelper.updateUserGoals(goalsteps, goalWeight, database);
                    Toast.makeText(getActivity(), "Goal update made", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "you dont have a goal plz click other button", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
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