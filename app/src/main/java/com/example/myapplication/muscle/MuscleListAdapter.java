package com.example.myapplication.muscle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class MuscleListAdapter  extends BaseAdapter {
    private List<Muscle> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public MuscleListAdapter(Context aContext,  List<Muscle> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
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
            convertView = layoutInflater.inflate(R.layout.list_muscules_layout, null);
            holder =new ViewHolder();
            holder.gifUrl = (ImageView) convertView.findViewById(R.id.imageView);
            holder.name=(TextView) convertView.findViewById(R.id.textView_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Muscle muscle = this.listData.get(position);
        holder.name.setText(muscle.getName());
        /*holder.equipment.setText(muscle.getEquipment());
        holder.bodyPart.setText("bodyPart: " + muscle.getBodyPart());
        holder.id.setText("id : "+muscle.getId());

        holder.target.setText("id : "+muscle.getTarget());*/
        //holder.gifUrl.setImageResource(R.drawable.abs);
        //Uri uri = Uri.parse("http://d205bpvrqc9yn1.cloudfront.net/0007.gif");
       // holder.gifUrl.setImageURI(uri);
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
