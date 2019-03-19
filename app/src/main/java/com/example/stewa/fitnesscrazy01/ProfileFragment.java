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
public class ProfileFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private Button Bregister;
    NavigationView navigationView;
    EditText Username, First_Name, Last_Name, ages, weights, heights, passwords, ConfirmPassword;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "Swipe right on the left side of the screen!!!", Toast.LENGTH_SHORT).show();

        setHasOptionsMenu(true);
        final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        final SQLiteDatabase database = contactDbHelper.getWritableDatabase();
        contactDbHelper.getUserInformation(database);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Username = view.findViewById(R.id.TXTBuserName);
        First_Name = view.findViewById(R.id.TXTBfirstName);
        Last_Name = view.findViewById(R.id.TXTBlastName);
        ages = view.findViewById(R.id.TXTBage);
        heights = view.findViewById(R.id.TXTBgoalHeight);
        weights = view.findViewById(R.id.TXTBweight);
        passwords = view.findViewById(R.id.TXTBpassword);
        ConfirmPassword = view.findViewById(R.id.TXTBConfirmPassword);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        Bregister = view.findViewById(R.id.Bregister);
        //sets text
        TextView UserName = (TextView) view.findViewById(R.id.TXTBuserName);
        TextView FirstName = (TextView) view.findViewById(R.id.TXTBfirstName);
        TextView LastName = (TextView) view.findViewById(R.id.TXTBlastName);
        TextView Age = (TextView) view.findViewById(R.id.TXTBage);
        TextView Height = (TextView) view.findViewById(R.id.TXTBgoalHeight);
        TextView Weight = (TextView) view.findViewById(R.id.TXTBweight);
        TextView Password = (TextView) view.findViewById(R.id.TXTBpassword);
        UserName.setText(ContactContact.ContactEntry.getUSER_NAMEs());
        FirstName.setText(ContactContact.ContactEntry.getFIRST_NAMEs());
        LastName.setText(ContactContact.ContactEntry.getLAST_NAMEs());
        Age.setText(ContactContact.ContactEntry.getAGEs());
        Height.setText(ContactContact.ContactEntry.getHEIGHTs());
        Weight.setText(ContactContact.ContactEntry.getWEIGHTs());
        Password.setText(ContactContact.ContactEntry.getPASSWORDs());

        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = Username.getText().toString();
                final String firstName = First_Name.getText().toString();
                final String lastName = Last_Name.getText().toString();
                final String age = ages.getText().toString();
                final String height = heights.getText().toString();
                final String weight = weights.getText().toString();
                String password = passwords.getText().toString();
                if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()) {
                    Toast.makeText(getActivity(), "Missing information", Toast.LENGTH_SHORT).show();
                    return;
                }
                contactDbHelper.updateUser(firstName, lastName, username, password, height, weight, age, database);
                ContactContact.ContactEntry.setUSER_NAMEs(username);
                Toast.makeText(getActivity(), "Your profile has been updated!!!", Toast.LENGTH_SHORT).show();

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

