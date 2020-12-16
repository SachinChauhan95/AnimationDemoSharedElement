package com.javacodegeeks.AndroidStackViewExample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class StackAdapter extends BaseAdapter {

    ArrayList<StackItems> arrayList;
    LayoutInflater inflater;
    ViewHolder holder = null;
    final Activity activity;

    public StackAdapter(Activity context, ArrayList<StackItems> arrayList) {
        this.activity = context;
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public StackItems getItem(int pos) {
        return arrayList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.item, parent, false);

        holder = new ViewHolder();

        holder.image = (ImageView) view.findViewById(R.id.imageViewId);
        holder.name = (TextView) view.findViewById(R.id.sharedTextViewId);
        holder.title = (TextView) view.findViewById(R.id.titleText);
        holder.sample_layout=(LinearLayout)view.findViewById(R.id.sample_layout);

        final StackItems objStackItem = arrayList.get(pos);
        holder.image.setBackgroundResource(arrayList.get(pos).getImage());
        holder.name.setText(arrayList.get(pos).getSharedTextViewId());
        holder.title.setText(arrayList.get(pos).getTitleText());

       /*
        holder.sample_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionToActivity(SharedElementActivity.class,objStackItem,holder);
            }
        });*/
        return view;
    }

    private void transitionToActivity(Class target , StackItems stackObj,ViewHolder viewHolder) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(viewHolder.image, activity.getString(R.string.square_blue_name)),
                new Pair<>(viewHolder.name, activity.getString(R.string.square_blue_name)));

        startActivity(target, pairs, stackObj);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, StackItems stackObj) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", stackObj);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    public class ViewHolder {
        ImageView image;
        TextView name;
        TextView title;
        LinearLayout sample_layout;


    }



}
