package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.myapplication.Beans.Muscle;
import com.example.myapplication.R;


import java.util.List;

public class MuscleListAdapter  extends BaseAdapter {
    private List<Muscle> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private String page;


    public MuscleListAdapter(Context aContext,  List<Muscle> listData,String apage) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.page =apage;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            if(page.equals("Muscle")){
                convertView = layoutInflater.inflate(R.layout.list_muscules_layout, null);
            }
            else{
                convertView = layoutInflater.inflate(R.layout.list_musculescalendar_layout, null);
            }

            holder =new ViewHolder();
            holder.gifUrl = (ImageView) convertView.findViewById(R.id.imageView);
            holder.name=(TextView) convertView.findViewById(R.id.textView_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Muscle muscle = this.listData.get(position);
        holder.name.setText(muscle.getName());
        Glide.with(context)
                .asBitmap()
                .load(muscle.getGifUrl())
                .into(holder.gifUrl);
        return convertView;
    }
    static class ViewHolder {
        ImageView gifUrl;
        TextView equipment;
        TextView bodyPart;
        TextView id;
        TextView name;
        TextView target;

    }
}
