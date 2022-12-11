package com.example.myapplication.Layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.myapplication.DBHelper.Bookmarks;
import com.example.myapplication.R;
import com.example.myapplication.Beans.Muscle;
import com.example.myapplication.Adapters.MuscleListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    List<Muscle> list = new ArrayList<Muscle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        //Connection to db
        Bookmarks DB =new Bookmarks(this);
        RelativeLayout emptyLayout=(RelativeLayout)findViewById(R.id.emptyBookmark) ;
        list=DB.getAllBookmarks();

        List<Muscle> image_details_pull = getListData();
        final SwipeMenuListView listViewBookmark = (SwipeMenuListView) findViewById(R.id.bookmarksListView);
        listViewBookmark.setAdapter(new MuscleListAdapter(BookmarkActivity.this, image_details_pull,"Muscle"));
        // When the user clicks on the ListItem
        listViewBookmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent i =new Intent(getApplicationContext(), MusculeDesc.class);
                Object o = listViewBookmark.getItemAtPosition(position);
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

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                //set title
                deleteItem.setTitle("Delete");
                // set item title fontsize
                deleteItem.setTitleSize(12);
                // set item title font
                deleteItem.setTitleColor(R.color.white);

                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listViewBookmark.setMenuCreator(creator);

        listViewBookmark.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Object o = listViewBookmark.getItemAtPosition(position);
                Muscle muscle = (Muscle) o;
                switch (index) {
                    case 0:
                        DB.deleteBookmark(muscle.getId(),"1");
                        list=DB.getAllBookmarks();
                        List<Muscle> image_details_pull = getListData();
                        listViewBookmark.setAdapter(new MuscleListAdapter(BookmarkActivity.this, image_details_pull,"Muscle"));
                        Toast toast=Toast.makeText(getApplicationContext(),""+muscle.getName()+" is deleted from your bookmarks list",Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        //EMpty bookmark
        if(getListData().isEmpty()){
            emptyLayout.setVisibility(View.VISIBLE);
        }
        else{
            emptyLayout.setVisibility(View.INVISIBLE);
        }
        //bottom Nav bar

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.bookmarkActivity);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.calendarActivity:
                        Intent i= new Intent(getApplicationContext(),CalendarViewActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bookmarkActivity:
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
    private List<Muscle> getListData() {

        return list;
    }
}
