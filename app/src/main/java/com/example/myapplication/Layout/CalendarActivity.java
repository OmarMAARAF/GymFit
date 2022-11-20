package com.example.myapplication.Layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.muscle.Muscle;
import com.example.myapplication.muscle.MuscleListAdapter;
import com.example.myapplication.muscleActivity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    List<Muscle> list = new ArrayList<Muscle>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // Define the variable of CalendarView type
        // and TextView type;
        CalendarView calendar;
        TextView date_view;
        TextView pushDay =(TextView)findViewById(R.id.pushDay) ;
        TextView pullDay =(TextView)findViewById(R.id.pullDay) ;
        TextView legsDay =(TextView)findViewById(R.id.legsDay) ;

            calendar = (CalendarView)
                    findViewById(R.id.calendar);
            date_view = (TextView)
                    findViewById(R.id.date_view);
            TextView selectedDay =(TextView)findViewById(R.id.selectedDay) ;
            //list view
       // list.add(new Muscle(rec.getString("bodyPart"),rec.getString("equipment"),rec.getString("gifUrl"),rec.getString("id"),rec.getString("name"),rec.getString("target")));
        List<Muscle> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.calendarPushlistView);
        listView.setAdapter(new MuscleListAdapter(CalendarActivity.this, image_details));
        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent i =new Intent(getApplicationContext(), MusculeDesc.class);
                Object o = listView.getItemAtPosition(position);
                Muscle muscle = (Muscle) o;
                i.putExtra("name",((Muscle) o).getName());
                i.putExtra("target",((Muscle) o).getTarget());
                i.putExtra("equipement",((Muscle) o).getEquipment());
                i.putExtra("gifURL",((Muscle) o).getGifUrl());
                i.putExtra("bodyPart",((Muscle) o).getBodyPart());
                startActivity(i);

            }
        });


            // Add Listener in calendar
            calendar
                    .setOnDateChangeListener(
                            new CalendarView
                                    .OnDateChangeListener() {
                                @Override

                                // In this Listener have one method
                                // and in this method we will
                                // get the value of DAYS, MONTH, YEARS
                                public void onSelectedDayChange(
                                        @NonNull CalendarView view,
                                        int year,
                                        int month,
                                        int dayOfMonth)
                                {

                                    // Store the value of date with
                                    // format in String type Variable
                                    // Add 1 in month because month
                                    // index is start with 0
                                    String Date
                                            = dayOfMonth + "-"
                                            + (month + 1) + "-" + year;

                                    // set this date in TextView for Display
                                    date_view.setText(Date);
                                    String dayNames[] = new DateFormatSymbols().getWeekdays();
                                    String mounthNames[] = new DateFormatSymbols().getMonths();
                                    Calendar selected = Calendar.getInstance();
                                    selected.set(year, month, dayOfMonth);

                                    int dayOfWeek = selected.get(Calendar.DAY_OF_WEEK);
                                    selectedDay.setText(dayNames[dayOfWeek]+" "+(dayOfMonth)+" "+mounthNames[(month)]+" :");
                                    if(dayOfWeek==2 || dayOfWeek==5){
                                        pushDay.setVisibility(View.VISIBLE);
                                        pullDay.setVisibility(View.INVISIBLE);
                                        legsDay.setVisibility(View.INVISIBLE);

                                    }
                                    if(dayOfWeek==3 || dayOfWeek==6){
                                        pushDay.setVisibility(View.INVISIBLE);
                                        pullDay.setVisibility(View.VISIBLE);
                                        legsDay.setVisibility(View.INVISIBLE);

                                    }
                                    if(dayOfWeek==4 || dayOfWeek==7){
                                        pushDay.setVisibility(View.INVISIBLE);
                                        pullDay.setVisibility(View.INVISIBLE);
                                        legsDay.setVisibility(View.VISIBLE);

                                    }
                                }
                            });
    }
    private List<Muscle> getListData() {
        return list;
    }
}