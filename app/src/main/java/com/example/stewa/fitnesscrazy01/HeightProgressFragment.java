package com.example.stewa.fitnesscrazy01;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeightProgressFragment extends Fragment {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    BarChart barChart;

    public HeightProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("we in to the worlds biggest place where you can find the best friend and the world and the world");
        View view = inflater.inflate(R.layout.fragment_height_progress, container, false);
        barChart= (BarChart)view.findViewById(R.id.barGraph);
        final ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        final SQLiteDatabase database = contactDbHelper.getWritableDatabase();

        ArrayList<String> arrHeight = new ArrayList<>();
        ArrayList<String> arrDate = new ArrayList<>();

        String query = "select * from HeightLogs where user_name = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "';";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            arrDate.add(cursor.getString(cursor.getColumnIndex("DATE")));
            arrHeight.add(cursor.getString(cursor.getColumnIndex("height")));

        }
        float week1WeightLoss = (float) 0;
        float week2WeightLoss = (float) 0;
        float week3WeightLoss = (float) 0;
        float week4WeightLoss = (float) 0;
        float week5WeightLoss = (float) 0;
        float week6WeightLoss = (float) 0;
        float week7WeightLoss = (float) 0;
        for(int i=0;i<arrHeight.size();i++){
            System.out.println(arrHeight.get(arrHeight.size()-1));
        }
        try {
            week7WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size() - 7));
            System.out.println(week7WeightLoss);
        }
        catch(Exception e)
        {
            System.out.println("No 7th Value");
        }

        try {
            week6WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size() - 6));
            System.out.println(week6WeightLoss);
        }
        catch(Exception e)
        {
            System.out.println("No 6th Value");
        }
        try{
            week5WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size()-5));
            System.out.println(week5WeightLoss);
        }
        catch(Exception e)
        {
            System.out.println("No 5th Value");
        }
        try
        {
            week4WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size()-4));
            System.out.println(week4WeightLoss);
        }
        catch(Exception e)
        {
            System.out.println("No 4th Value");
        }
        try{
            week3WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size()-3));
            System.out.println(week3WeightLoss);
        }
        catch(Exception e)
        {
            System.out.println("No 3th Value");
        }
        try{
            week2WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size()-2));
            System.out.println(week2WeightLoss);
        }
        catch(Exception e)
        {
            System.out.println("No 2th Value");
        }
        try
        {
            week1WeightLoss = Float.parseFloat(arrHeight.get(arrHeight.size()-1));
            System.out.println(week1WeightLoss);
        }

        catch(Exception e)
        {
            System.out.println("No 1th Value");
        }
        if(week1WeightLoss == 0 && week2WeightLoss == 0 && week3WeightLoss == 0 && week4WeightLoss == 0 && week5WeightLoss == 0 && week6WeightLoss == 0 && week7WeightLoss == 0){
            Toast.makeText(getActivity(), "No data to show, please enter your weight changes in the logs section", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HeightFragment()).addToBackStack(null).commit();
            return null;
        }
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, week1WeightLoss));
        entries.add(new BarEntry(2f, week2WeightLoss));
        entries.add(new BarEntry(3f, week3WeightLoss));
        entries.add(new BarEntry(4f, week4WeightLoss));
        // gap of 2f
        entries.add(new BarEntry(5f, week5WeightLoss));
        entries.add(new BarEntry(6f, week6WeightLoss));
        entries.add(new BarEntry(7f, week7WeightLoss));

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("0");
        xLabel.add("Day 1");
        xLabel.add("Day 2");
        xLabel.add("Day 3");
        xLabel.add("Day 4");
        xLabel.add("Day 5");
        xLabel.add("Day 6");
        xLabel.add("Day 7");


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(16);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int)value);
            }
        });
        YAxis left = barChart.getAxisLeft();
        left.setTextSize(16);
        barChart.getAxisRight().setEnabled(false);
        BarDataSet set = new BarDataSet(entries, "User Wieght Over 7 Days");
        set.setValueTextSize(20);
        set.setColor(Color.RED);

        BarData data = new BarData(set);
        data.setBarWidth(0.4f); // set custom bar width
        barChart.getDescription().setEnabled(false);
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh


















        drawerLayout = view.findViewById(R.id.drawerLayout);
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