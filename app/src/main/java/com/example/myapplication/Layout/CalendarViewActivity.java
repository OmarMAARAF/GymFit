package com.example.myapplication.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Beans.Muscle;
import com.example.myapplication.Adapters.MuscleListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CalendarViewActivity extends AppCompatActivity {


    List<Muscle> pushList = new ArrayList<Muscle>();
    List<Muscle> pullList = new ArrayList<Muscle>();
    List<Muscle> legsList = new ArrayList<Muscle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        //init
        TextView selectedDay=(TextView) findViewById(R.id.selectedDay);
        TextView selectedDay1=(TextView) findViewById(R.id.selectedDay1);
        TextView selectedDay2=(TextView) findViewById(R.id.selectedDay2);
        TextView selectedDay3=(TextView) findViewById(R.id.selectedDay3);
        MaterialCalendarView mcv=(MaterialCalendarView)findViewById(R.id.calendarView);
        String dayNames[] = new DateFormatSymbols().getWeekdays();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Format f = new SimpleDateFormat("EEEE");
        //layouts
        RelativeLayout pushLayout =(RelativeLayout)findViewById(R.id.pushLayout);
        RelativeLayout pullLayout =(RelativeLayout)findViewById(R.id.pullLayout);
        RelativeLayout legsLayout =(RelativeLayout)findViewById(R.id.legsLayout);
        RelativeLayout restLayout =(RelativeLayout)findViewById(R.id.restLayout);
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull @NotNull MaterialCalendarView widget, @NonNull @NotNull CalendarDay date, boolean selected) {
                if(f.format(date.getDate().getTime()).equals("Monday") || f.format(date.getDate().getTime()).equals("Thursday")){
                    selectedDay.setText(f.format(date.getDate().getTime()));
                    pushLayout.setVisibility(View.VISIBLE);
                    pullLayout.setVisibility(View.INVISIBLE);
                    legsLayout.setVisibility(View.INVISIBLE);
                    restLayout.setVisibility(View.INVISIBLE);
                }
                else if(f.format(date.getDate().getTime()).equals("Tuesday") || f.format(date.getDate().getTime()).equals("Friday")){
                    selectedDay1.setText(f.format(date.getDate().getTime()));
                    pushLayout.setVisibility(View.INVISIBLE);
                    pullLayout.setVisibility(View.VISIBLE);
                    legsLayout.setVisibility(View.INVISIBLE);
                    restLayout.setVisibility(View.INVISIBLE);
                }
                else if(f.format(date.getDate().getTime()).equals("Wednesday") || f.format(date.getDate().getTime()).equals("Saturday")){
                    selectedDay2.setText(f.format(date.getDate().getTime()));
                    pushLayout.setVisibility(View.INVISIBLE);
                    pullLayout.setVisibility(View.INVISIBLE);
                    legsLayout.setVisibility(View.VISIBLE);
                    restLayout.setVisibility(View.INVISIBLE);
                }
                else if(f.format(date.getDate().getTime()).equals("Sunday")){
                    selectedDay3.setText(f.format(date.getDate().getTime()));
                    pushLayout.setVisibility(View.INVISIBLE);
                    pullLayout.setVisibility(View.INVISIBLE);
                    legsLayout.setVisibility(View.INVISIBLE);
                    restLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        mcv.setSelectedDate(CalendarDay.today());
        Log.i("today",f.format(CalendarDay.today().getDate().getTime()));
        if(f.format(CalendarDay.today().getDate().getTime()).equals("Monday") || f.format(CalendarDay.today().getDate().getTime()).equals("Thursday")){
            selectedDay.setText(f.format(CalendarDay.today().getDate().getTime()));
            pushLayout.setVisibility(View.VISIBLE);
            pullLayout.setVisibility(View.INVISIBLE);
            legsLayout.setVisibility(View.INVISIBLE);
            restLayout.setVisibility(View.INVISIBLE);

        }
        if(f.format(CalendarDay.today().getDate().getTime()).equals("Tuesday") || f.format(CalendarDay.today().getDate().getTime()).equals("Friday")){
            selectedDay1.setText(f.format(CalendarDay.today().getDate().getTime()));
            pushLayout.setVisibility(View.INVISIBLE);
            pullLayout.setVisibility(View.VISIBLE);
            legsLayout.setVisibility(View.INVISIBLE);
            restLayout.setVisibility(View.INVISIBLE);

        }
        if(f.format(CalendarDay.today().getDate().getTime()).equals("Wednesday") || f.format(CalendarDay.today().getDate().getTime()).equals("Saturday")){
            selectedDay2.setText(f.format(CalendarDay.today().getDate().getTime()));
            pushLayout.setVisibility(View.INVISIBLE);
            pullLayout.setVisibility(View.INVISIBLE);
            legsLayout.setVisibility(View.VISIBLE);
            restLayout.setVisibility(View.INVISIBLE);

        }
        if(f.format(CalendarDay.today().getDate().getTime()).equals("Sunday")){
            selectedDay3.setText(f.format(CalendarDay.today().getDate().getTime()));
            pushLayout.setVisibility(View.INVISIBLE);
            pullLayout.setVisibility(View.INVISIBLE);
            legsLayout.setVisibility(View.INVISIBLE);
            restLayout.setVisibility(View.VISIBLE);

        }
        //pushDay program
        pushList.add(new Muscle("cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3220.gif", "3220","astride jumps (male)", "cardiovascular system"));
        pushList.add(new Muscle( "cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3360.gif", "3360","bear crawl", "cardiovascular system"));
        pushList.add(new Muscle( "cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/1160.gif","1160",  "burpee", "cardiovascular system"));
        pushList.add(new Muscle( "chest", "barbell", "http://d205bpvrqc9yn1.cloudfront.net/0025.gif", "25", "barbell bench press", "pectorals"));
        pushList.add(new Muscle( "chest", "barbell", "http://d205bpvrqc9yn1.cloudfront.net/0033.gif", "33", "barbell decline bench press", "pectorals"));
        pushList.add(new Muscle( "chest", "barbell", "http://d205bpvrqc9yn1.cloudfront.net/0047.gif", "47", "barbell incline bench press", "pectorals"));
        pushList.add(new Muscle( "upper arms", "leverage machine", "http://d205bpvrqc9yn1.cloudfront.net/0019.gif","19",  "assisted triceps dip (kneeling)", "triceps"));
        pushList.add(new Muscle( "upper arms", "cable", "http://d205bpvrqc9yn1.cloudfront.net/0231.gif", "231","cable standing one arm triceps extension", "triceps"));
        pushList.add(new Muscle("upper arms", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/0283.gif","283",  "diamond push-up", "triceps"));
        pushList.add(new Muscle( "back", "band", "http://d205bpvrqc9yn1.cloudfront.net/1018.gif","1018", "band shrug", "traps"));
        pushList.add(new Muscle( "back", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/0767.gif","767",  "smith shrug", "traps"));

        List<Muscle> image_details = getPushListData();
        final ListView listView = (ListView) findViewById(R.id.calendarPushlistView);

        listView.setAdapter(new MuscleListAdapter(CalendarViewActivity.this, image_details,"Muscle"));
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
                i.putExtra("id",((Muscle) o).getId());
                startActivity(i);

            }
        });
        //pull Programe
        pullList.add(new Muscle("cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3318.gif", "3318","swing 360", "cardiovascular system"));
        pullList.add(new Muscle( "cardio", "elliptical machine", "http://d205bpvrqc9yn1.cloudfront.net/2141.gif", "2141","walk elliptical cross trainer", "cardiovascular system"));
        pullList.add(new Muscle( "cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3655.gif","3655",  "walking high knees lunge", "cardiovascular system"));
        pullList.add(new Muscle(  "back", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3297.gif", "3297","Back lever", "upper back" ));
        pullList.add(new Muscle(   "back", "barbell", "https://d205bpvrqc9yn1.cloudfront.net/0027.gif", "27","barbell bent over row", "upper back"));
        pullList.add(new Muscle(   "back", "cable", "http://d205bpvrqc9yn1.cloudfront.net.0159.gif", "160","Cable decline seated wide-grip row", "upper back"));
        pullList.add(new Muscle( "upper arms", "barbell", "http://d205bpvrqc9yn1.cloudfront.net/0023.gif", "23","barbell alternate biceps curl", "biceps" ));
        pullList.add(new Muscle(   "upper arms", "body weight", "https://d205bpvrqc9yn1.cloudfront.net/1770.gif","1770", "biceps leg concentration curl", "biceps"));
        pullList.add(new Muscle(  "upper arms", "cable", "https://d205bpvrqc9yn1.cloudfront.net/1642.gif", "1642","cable seated one arm concentration curl", "biceps" ));
        pullList.add(new Muscle( "back", "band", "http://d205bpvrqc9yn1.cloudfront.net/1018.gif","1018", "band shrug", "traps"));
        pullList.add(new Muscle( "back", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/0767.gif","767",  "smith shrug", "traps"));
        List<Muscle> image_details_pull = getPullListData();
        final ListView listViewPull = (ListView) findViewById(R.id.calendarPulllistView);

        listViewPull.setAdapter(new MuscleListAdapter(CalendarViewActivity.this, image_details_pull,"Muscle"));
        // When the user clicks on the ListItem
        listViewPull.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent i =new Intent(getApplicationContext(), MusculeDesc.class);
                Object o = listViewPull.getItemAtPosition(position);
                Muscle muscle = (Muscle) o;
                i.putExtra("name",((Muscle) o).getName());
                i.putExtra("target",((Muscle) o).getTarget());
                i.putExtra("equipement",((Muscle) o).getEquipment());
                i.putExtra("gifURL",((Muscle) o).getGifUrl());
                i.putExtra("bodyPart",((Muscle) o).getBodyPart());
                i.putExtra("id",((Muscle) o).getId());
                startActivity(i);

            }
        });
        //legs Program
        legsList.add(new Muscle("cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3219.gif", "3219","scissor jumps (male)", "cardiovascular system"));
        legsList.add(new Muscle( "cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3222.gif", "3222","semi squat jump (male)", "cardiovascular system"));
        legsList.add(new Muscle( "cardio", "body weight", "http://d205bpvrqc9yn1.cloudfront.net/3656.gif","3656",  "short stride run", "cardiovascular system"));
        legsList.add(new Muscle(   "upper legs", "barbell", "http://d205bpvrqc9yn1.cloudfront.net/0024.gif", "24", "barbell bench front squat", "quads"));
        legsList.add(new Muscle(  "upper legs", "dumbbell", "http://d205bpvrqc9yn1.cloudfront.net/1757.gif", "1757","dumbbell single leg deadlift", "glutes" ));
        legsList.add(new Muscle( "upper legs", "body weight", "https://d205bpvrqc9yn1.cloudfront.net/1604.gif","1604", "world greatest stretch", "hamstrings"  ));
        legsList.add(new Muscle(  "upper legs", "barbell", "https://d205bpvrqc9yn1.cloudfront.net/0032.gif", "32", "barbell deadlift", "glutes" ));
        legsList.add(new Muscle(  "upper legs", "trap bar", "http://d205bpvrqc9yn1.cloudfront.net/0811.gif", "811", "trap bar deadlift", "glutes" ));
        legsList.add(new Muscle(  "waist", "body weight", "https://d205bpvrqc9yn1.cloudfront.net/0001.gif", "1", "3/4 sit-up", "abs" ));
        legsList.add(new Muscle(  "waist", "body weight", "https://d205bpvrqc9yn1.cloudfront.net/0003.gif", "3", "air bike", "abs" ));

        List<Muscle> image_details_legs = getLegsListData();
        final ListView listViewLegs = (ListView) findViewById(R.id.calendarLegslistView);

        listViewLegs.setAdapter(new MuscleListAdapter(CalendarViewActivity.this, image_details_legs,"Muscle"));
        // When the user clicks on the ListItem
        listViewLegs.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent i =new Intent(getApplicationContext(), MusculeDesc.class);
                Object o = listViewLegs.getItemAtPosition(position);
                Muscle muscle = (Muscle) o;
                i.putExtra("name",((Muscle) o).getName());
                i.putExtra("target",((Muscle) o).getTarget());
                i.putExtra("equipement",((Muscle) o).getEquipment());
                i.putExtra("gifURL",((Muscle) o).getGifUrl());
                i.putExtra("bodyPart",((Muscle) o).getBodyPart());
                i.putExtra("id",((Muscle) o).getId());
                startActivity(i);

            }
        });

        //bottom Nav bar

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.calendarActivity);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.bookmarkActivity:
                        Intent i= new Intent(getApplicationContext(),BookmarkActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendarActivity:
                        return true;
                    case R.id.profilActivity:
                        Intent i1= new Intent(getApplicationContext(),ProfileActivity.class);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.homeActivity:
                        Intent i2= new Intent(getApplicationContext(), Home.class);
                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i2);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }

    private List<Muscle> getPushListData() {
        return pushList;
    }
    private List<Muscle> getPullListData() {
        return pullList;
    }
    private List<Muscle> getLegsListData() {
        return legsList;
    }
}