package com.javacodegeeks.AndroidStackViewExample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class SharedElementFragment extends Fragment {

    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment newInstance(StackItems stackObj) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SAMPLE, stackObj);
        SharedElementFragment fragment = new SharedElementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shared_element_fragment, container, false);
        final StackItems sample = (StackItems) getArguments().getSerializable(EXTRA_SAMPLE);

        final ImageView imgView = (ImageView) view.findViewById(R.id.imageViewId);
        final TextView itemname = (TextView)view.findViewById(R.id.itemname) ;

        imgView.setBackgroundResource(sample.getImage());
        itemname.setText(sample.getSharedTextViewId());

        return view;
    }
}
