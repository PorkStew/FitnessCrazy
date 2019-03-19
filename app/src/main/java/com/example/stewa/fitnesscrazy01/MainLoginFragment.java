package com.example.stewa.fitnesscrazy01;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainLoginFragment extends Fragment {
    //variable declaration
    TextView Blogin, Bregister;
    EditText UserName, Password;

    public MainLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_login, container, false);
        //assigns textviews to variables
        Bregister = view.findViewById(R.id.TXTregister);
        Blogin = view.findViewById(R.id.Blogins);
        Password = view.findViewById(R.id.TXTBpassword);
        UserName = view.findViewById(R.id.TXTBuserName);
        //on click listener to check when the register text is clicked
        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new RegisterFragment()).addToBackStack(null).commit();
            }
        });
        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //takes textview variables and assgins them to other variables which are then check if null and then checks if the user details are correct
                String userName = UserName.getText().toString();
                String password = Password.getText().toString();
                if (userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "unsuccessful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Don't leave anything blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                //opens database for check of user input to allow or not for login
                ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
                SQLiteDatabase database = contactDbHelper.getWritableDatabase();
                contactDbHelper.search(database, userName, password);
                ContactContact.ContactEntry.setUSER_NAMEs(userName);

                //sets fields to null when user successfully logs in
                if (ContactContact.ContactEntry.show == true) {
                    UserName.setText("");
                    Password.setText("");
                    contactDbHelper.close();
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MenuFragment()).addToBackStack(null).commit();
                } else {
                    Toast.makeText(getActivity(), "Incorrect user information", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
