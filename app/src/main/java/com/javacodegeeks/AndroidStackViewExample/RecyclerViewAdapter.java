package com.javacodegeeks.AndroidStackViewExample;


import android.app.Activity;
import android.content.Intent;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    ArrayList<StackItems> mData;
    StackAdapter.ViewHolder holder = null;
    Activity activity;

    // data is passed into the constructor
    RecyclerViewAdapter(Activity context, ArrayList<StackItems> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.activity=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final StackItems objStackItem = mData.get(position);

        holder.name.setText(objStackItem.getSharedTextViewId());
        holder.image.setBackgroundResource(objStackItem.getImage());
        holder.title.setText(objStackItem.getTitleText());
        holder.sample_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionToActivity(SharedElementActivity.class,objStackItem,holder);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,title;
        LinearLayout sample_layout;
        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.sample_icon);
            name = itemView.findViewById(R.id.sample_name);
            title = itemView.findViewById(R.id.titleText);
            sample_layout= itemView.findViewById(R.id.sample_layout);
        }
    }

    public StackItems getItem(int id) {
        return mData.get(id);
    }
    private void transitionToActivity(Class target , StackItems stackObj, ViewHolder viewHolder) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(viewHolder.image, activity.getString(R.string.square_blue_name)),
                new Pair<>(viewHolder.name, activity.getString(R.string.square_green_name)),
                new Pair<>(viewHolder.title, activity.getString(R.string.sample_blue_title)));

        startActivity(target, pairs, stackObj);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, StackItems stackObj) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", stackObj);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

}