package com.yandex.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainDetailFragment extends StatefulFragment {
    public static final String ARG_ITEM_ID = "item_id";
    private TextView detailView;

    public MainDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        detailView = (TextView) view.findViewById(R.id.item_detail);
        return view;
    }

    public void setText(String details) {
        detailView.setText(details);
    }
}
