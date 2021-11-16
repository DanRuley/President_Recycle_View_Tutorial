package com.example.presidentrecycleviewtutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List<President> presidents;
    Context ctx;

    public RecyclerViewAdapter(List<President> presidents, Context _ctx) {
        this.presidents = presidents;
        this.ctx = _ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflater parameters: the layout you want to inflate, the parent, and attach to root T/F
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.president_recyl_line, parent, false);
        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // create a ProgressDrawable object which we will show as placeholder
        CircularProgressDrawable drawable = new CircularProgressDrawable(this.ctx);
        drawable.setColorSchemeColors(R.color.design_default_color_primary, R.color.design_default_color_primary_dark, R.color.teal_700);
        drawable.setCenterRadius(30f);
        drawable.setStrokeWidth(5f);
        // set all other properties as you would see fit and start it
        drawable.start();

        holder.presName.setText(presidents.get(position).getName());
        holder.presDate.setText(String.valueOf(presidents.get(position).getDateOfElection()));
        Glide.with(this.ctx).load(presidents.get(position).getImageURL()).placeholder(drawable).into(holder.presPicture);
    }

    @Override
    public int getItemCount() {
        return presidents.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView presPicture;
        TextView presName;
        TextView presDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            presPicture = itemView.findViewById(R.id.presRecImage);
            presName = itemView.findViewById(R.id.nameRecText);
            presDate = itemView.findViewById(R.id.dateRecText);
        }
    }
}
