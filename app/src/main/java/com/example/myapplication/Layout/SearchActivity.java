package com.example.myapplication.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Beans.Muscle;
import com.example.myapplication.Adapters.MuscleListAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    List<Muscle> list = new ArrayList<Muscle>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //get search input and replace the spaces by %20
        String search = getIntent().getStringExtra("search");
        TextView searchText=(TextView)findViewById(R.id.searchText) ;
        searchText.setText("Result for \""+search+"\" :");
        search = search.trim();
        search = search.replaceAll("\\s", "%20");


        ImageButton SearchBackBtn=(ImageButton)findViewById(R.id.SearchBackBtn);
        SearchBackBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.onBackPressed();
            }
        }));
        //search
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://exercisedb.p.rapidapi.com/exercises/name/"+search)
                .get()
                .addHeader("X-RapidAPI-Key", "23d1c2af11msha06cfac7c98760dp100742jsn512292d57b35")
                .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String myresponse = response.body().string();
                    Log.i("here",myresponse);

                    try {
                        JSONArray jsonArray = new JSONArray(myresponse);
                        Log.i("here json",jsonArray.toString());
                        if (jsonArray != null) {

                            //Iterating JSON array
                            for (int i=0;i<10;i++){

                                JSONObject rec =jsonArray.getJSONObject(i);
                                list.add(new Muscle(rec.getString("bodyPart"),rec.getString("equipment"),rec.getString("gifUrl"),rec.getString("id"),rec.getString("name"),rec.getString("target")));

                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    SearchActivity.this.runOnUiThread(() -> {
                        List<Muscle> image_details = getListData();
                        final ListView listView = (ListView) findViewById(R.id.searchListView);
                        listView.setAdapter(new MuscleListAdapter(SearchActivity.this, image_details,"Muscle"));
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

                    });
                }
            }
        });
    }
    private List<Muscle> getListData() {

        return list;
    }
}