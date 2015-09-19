package com.yandex.myapplication;

import android.content.Context;
import android.support.annotation.MainThread;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainListFragment extends StatefulListFragment {
    private static final int SIZE = 25;

    private Callbacks mCallbacks;

    public interface Callbacks {
        void onItemSelected(String id);
    }

    public MainListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Activities containing this fragment must implement its callbacks.
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException("Context must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mCallbacks.onItemSelected(String.valueOf(position));
    }

    @MainThread
    public void setAccount(String accountId) {
        List<String> data = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            data.add("Item " + i + " from account " + accountId);
        }
        // TODO: replace with a real list adapter.
        setListAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                data));
    }
}
