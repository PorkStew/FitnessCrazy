package com.example.stewa.fitnesscrazy01;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button BtakePics;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        BtakePics = view.findViewById(R.id.BtakePic);
        BtakePics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
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