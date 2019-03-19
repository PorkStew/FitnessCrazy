package com.example.stewa.fitnesscrazy01;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    //variable declarations
    private Button Bregister;
    EditText UserName, First_Name, Last_Name, Age, Weight, Height, Password, ConfirmPassword;

    public RegisterFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        //assign user input to variables
        Bregister = view.findViewById(R.id.Bregister);
        UserName = view.findViewById(R.id.TXTBuserName);
        First_Name = view.findViewById(R.id.TXTBfirstName);
        Last_Name = view.findViewById(R.id.TXTBlastName);
        Age = view.findViewById(R.id.TXTBage);
        Height = view.findViewById(R.id.TXTBgoalHeight);
        Weight = view.findViewById(R.id.TXTBweight);
        Password = view.findViewById(R.id.TXTBpassword);
        ConfirmPassword = view.findViewById(R.id.TXTBConfirmPassword);

        //on click listener for the registering proccess and adding user information to the database with error checking
        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Id.getText().toString()
                String username = UserName.getText().toString();
                String firstName = First_Name.getText().toString();
                String lastName = Last_Name.getText().toString();
                String age = Age.getText().toString();
                String height = Height.getText().toString();
                String weight = Weight.getText().toString();
                String password = Password.getText().toString();
                String confirmpassword = ConfirmPassword.getText().toString();
                if (password.equals(confirmpassword)) {

                } else {
                    Toast.makeText(getActivity(), "unsuccessful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Password and confirmpassword need to match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "unsuccessful", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Don't leave anything blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
                SQLiteDatabase database = contactDbHelper.getWritableDatabase();
                contactDbHelper.addUser(username, firstName, lastName, age, weight, height, password, database);
                contactDbHelper.close();
                Toast.makeText(getActivity(), "You are now registered", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new MainLoginFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}
