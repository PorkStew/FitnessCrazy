package com.example.stewa.fitnesscrazy01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.widget.Toast;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ContactDbHelper extends SQLiteOpenHelper {
    //variable declaration
    //database name and version
    public static final String DATABASE_NAME = "Task2.db";
    public static final int DATABASE_VERSION = 1;
    //creation of main table User Information
    public static final String CREATE_TABLE = "create table " + ContactContact.ContactEntry.TABLE_NAME + "(" + ContactContact.ContactEntry.USER_NAME + " text," + ContactContact.ContactEntry.FIRST_NAME + " text,"
            + ContactContact.ContactEntry.LAST_NAME + " text," + ContactContact.ContactEntry.AGE + " text," + ContactContact.ContactEntry.HEIGHT + " text,"
            + ContactContact.ContactEntry.WEIGHT + " text," + ContactContact.ContactEntry.PASSWORD + " text);";
    //table deletion
    public static final String DROP_TABLE = "drop table if exists " + ContactContact.ContactEntry.TABLE_NAME;
    //creates the database
    public ContactDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("database created");
    }
    //creates main table UserInformation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        System.out.println("table created");
    }
    //drops table if an update to scheme
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
    //this gets user information which is used when the user looks at their profile
    public void getUserInformation(SQLiteDatabase sqLiteDatabase) {
        try {
            String query = "select * from USER_INFORMATION where USER_NAME = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "';";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            while (cursor.moveToNext()) {
                ContactContact.ContactEntry.setUSER_NAMEs(cursor.getString(cursor.getColumnIndex("user_name")));
                ContactContact.ContactEntry.setFIRST_NAMEs(cursor.getString(cursor.getColumnIndex("First_Name")));
                ContactContact.ContactEntry.setLAST_NAMEs(cursor.getString(cursor.getColumnIndex("Last_Name")));
                ContactContact.ContactEntry.setHEIGHTs(cursor.getString(cursor.getColumnIndex("height")));
                ContactContact.ContactEntry.setWEIGHTs(cursor.getString(cursor.getColumnIndex("weight")));
                ContactContact.ContactEntry.setAGEs(cursor.getString(cursor.getColumnIndex("age")));
                ContactContact.ContactEntry.setPASSWORDs(cursor.getString(cursor.getColumnIndex("password")));
            }
        } catch (Exception ex) {

        }
    }
    //gets user information for storage when user enters goal steps and weight
    public void getUserInformationGoal(SQLiteDatabase sqLiteDatabase) {
        System.out.println(ContactContact.ContactEntry.getUSER_NAMEs());
        try {
            String query = "select * from Goals where USER_NAME = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "';";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            while (cursor.moveToNext()) {
                ContactContact.ContactEntry.setGoalStep(cursor.getString(cursor.getColumnIndex("goalweight")));
                ContactContact.ContactEntry.setGoalWeights(cursor.getString(cursor.getColumnIndex("goalsteps")));
            }
        } catch (Exception ex) {

        }
    }
    //for user weight which is used in the logs section
    public void getUserInformationWeightLogs(SQLiteDatabase sqLiteDatabase) {

        String[] sDate = new String[100];
        String[] sHeight = new String[100];
       try {
            String query = "select * from WeightLogs where user_name = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "';";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            int iLoop = 0;
            ContactContact.ContactEntry.NumberOfEntries = 0;
            while (cursor.moveToNext()) {
                sDate[iLoop] = cursor.getString(cursor.getColumnIndex("DATE"));
                sHeight[iLoop] = cursor.getString(cursor.getColumnIndex("weight"));
                ContactContact.ContactEntry.setDateArray(sDate);
                ContactContact.ContactEntry.setWeightArray(sHeight);
                ContactContact.ContactEntry.NumberOfEntries = ContactContact.ContactEntry.NumberOfEntries +1;
                iLoop++;
           }
       }catch(Exception ex){

        }
    }
    //for user height which is used in the logs section
    public void getUserInformationHeightLogs(SQLiteDatabase sqLiteDatabase) {

        String[] sDate = new String[100];
        String[] sWeight = new String[100];
        try {
        String query = "select * from HeightLogs where user_name = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "';";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int iLoop = 0;
        ContactContact.ContactEntry.NumberOfEntries = 0;
        while (cursor.moveToNext()) {
            sDate[iLoop] = cursor.getString(cursor.getColumnIndex("DATE"));
            sWeight[iLoop] = cursor.getString(cursor.getColumnIndex("height"));
            ContactContact.ContactEntry.setDateArray(sDate);
            ContactContact.ContactEntry.setHeightArray(sWeight);
            ContactContact.ContactEntry.NumberOfEntries = ContactContact.ContactEntry.NumberOfEntries +1;
            iLoop++;

            }
            }catch(Exception ex){

        }
    }
    //checks user name and password if they are correct
    public void search(SQLiteDatabase sqLiteDatabase, String userName, String password) {
        String sql = "SELECT * FROM " + ContactContact.ContactEntry.TABLE_NAME + " WHERE user_name = '" + userName + "'" + "AND password = '" + password + "'";
        //sqLiteDatabase.execSQL(sql);
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() <= 0) {
            System.out.println(cursor.getCount());
            cursor.close();
            //if they enter wrong information
            ContactContact.ContactEntry.show = false;

        } else {
            ContactContact.ContactEntry.show = true;
        }
        cursor.close();
    }
    //checks if user has any goals entered
    public void searchForGoal(SQLiteDatabase sqLiteDatabase) {
        String sql = "SELECT * FROM Goals" + " WHERE user_name = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "'";
        //sqLiteDatabase.execSQL(sql);
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() <= 0) {
            System.out.println(cursor.getCount());
            cursor.close();
            //if they enter wrong information
            ContactContact.ContactEntry.show = false;
        } else {
            ContactContact.ContactEntry.show = true;
        }
        cursor.close();
    }
    //checks if user has any steps done for the day
    public void searchForStepsDoneByUser(SQLiteDatabase sqLiteDatabase) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String sql = "SELECT * FROM Steps" + " WHERE user_name = '" + ContactContact.ContactEntry.getUSER_NAMEs() + "' AND Date = '" + date +"'"  ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            //if they enter wrong information
            ContactContact.ContactEntry.showSteps = false;
        } else {
            ContactContact.ContactEntry.showSteps = true;
        }
        cursor.close();
    }
    //puts goals entered by user into goals table
    public void addGoal(String Username, String GoalWeight, String GoalSteps, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", Username);
        contentValues.put("goalweight", GoalWeight);
        contentValues.put("goalsteps", GoalSteps);
        database.insert("Goals", null, contentValues);
    }
    //creates table for the weight log section
    public void CreateUserLogWeight(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("create table WeightLogs" + "(" + ContactContact.ContactEntry.USER_NAME + " text," + "DATE" + " text," + ContactContact.ContactEntry.WEIGHT + " text);");
            System.out.println(" Weightlog table created");
        } catch (Exception e) {
            System.out.println("already created");
        }
    }
    //creates table for the height log section
    public void CreateUserLogHeight(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("create table HeightLogs" + "(" + ContactContact.ContactEntry.USER_NAME + " text," + "DATE" + " text," + ContactContact.ContactEntry.HEIGHT + " text);");
            System.out.println(" Heightlog table created");
        } catch (Exception e) {
            System.out.println("already created");
        }
    }
    //adds user input for current weight into the table weighlogs
    public void addUserWeightLog(String weight, SQLiteDatabase database) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", ContactContact.ContactEntry.getUSER_NAMEs());
        contentValues.put("DATE", date);
        contentValues.put("weight", weight);
        database.insert("WeightLogs", null, contentValues);
    }
    //adds user input for current height into the table heighlogs
    public void addUserHeightLog(String height, SQLiteDatabase database) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", ContactContact.ContactEntry.getUSER_NAMEs());
        contentValues.put("DATE", date);
        contentValues.put("height", height);
        database.insert("HeightLogs", null, contentValues);
    }
    //takes steps taken by user an puts them into the table AmountOfSteps
    public void addUserSteps(String steps, SQLiteDatabase database) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", ContactContact.ContactEntry.getUSER_NAMEs());
        contentValues.put("Date", date);
        contentValues.put("AmountOfSteps", steps);
        database.insert("Steps", null, contentValues);
    }
    //information the user wants to change will be changed within the user table
    public void updateUser(String FName, String LName, String UName, String UserPassword, String UserHeight, String UserWeight, String UserAge, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContact.ContactEntry.FIRST_NAME, FName);
        contentValues.put(ContactContact.ContactEntry.LAST_NAME, LName);
        contentValues.put(ContactContact.ContactEntry.USER_NAME, UName);
        contentValues.put(ContactContact.ContactEntry.PASSWORD, UserPassword);
        contentValues.put(ContactContact.ContactEntry.HEIGHT, UserHeight);
        contentValues.put(ContactContact.ContactEntry.WEIGHT, UserWeight);
        contentValues.put(ContactContact.ContactEntry.AGE, UserAge);
        database.update("USER_INFORMATION", contentValues, "user_name=?", new String[]{ContactContact.ContactEntry.getUSER_NAMEs()});
    }
    //changes the user makes to goals will be change in the table
    public void updateUserGoals(String GoalSteps, String GoalWeight, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("goalsteps", GoalSteps);
        contentValues.put("goalweight", GoalWeight);
        String userName = ContactContact.ContactEntry.getUSER_NAMEs();
        database.update("Goals", contentValues, "user_name=?", new String[]{ContactContact.ContactEntry.getUSER_NAMEs()});
    }
    //creates table goals
    public void MakeTableGoals(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("create table Goals" + "(" + ContactContact.ContactEntry.USER_NAME + " text," + ContactContact.ContactEntry.GoalSteps + " text," + ContactContact.ContactEntry.GoalWeight + " text);");
        } catch (Exception e) {

        }
    }
    //creates table steps
    public void MakeTableSteps(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("create table Steps" + "(" + ContactContact.ContactEntry.USER_NAME + " text," + ContactContact.ContactEntry.AmountOfSteps + " text," + ContactContact.ContactEntry.StepsDate + " text);");
            System.out.println(" step table created");
        } catch (Exception e) {
            System.out.println("already created steps");
        }
    }
    //adds the user who registers to the system in the table USER_INFORMATION
    public void addUser(String userName, String firstName, String lastName, String age, String weight, String height, String password, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContact.ContactEntry.USER_NAME, userName);
        contentValues.put(ContactContact.ContactEntry.FIRST_NAME, firstName);
        contentValues.put(ContactContact.ContactEntry.LAST_NAME, lastName);
        contentValues.put(ContactContact.ContactEntry.AGE, age);
        contentValues.put(ContactContact.ContactEntry.WEIGHT, weight);
        contentValues.put(ContactContact.ContactEntry.HEIGHT, height);
        contentValues.put(ContactContact.ContactEntry.PASSWORD, password);
        database.insert(ContactContact.ContactEntry.TABLE_NAME, null, contentValues);
        System.out.println("data inserted");
    }
}
