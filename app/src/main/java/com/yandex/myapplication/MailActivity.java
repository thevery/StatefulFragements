package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by thevery on 18/09/15.
 */
public class MailActivity extends AppCompatActivity
        implements MainListFragment.Callbacks, MainAccountFragment.Callbacks {
    public static final String TAG1 = "TAG1";
    public static final String TAG2 = "TAG2";

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        twoPane = findViewById(R.id.container_2) != null;
        if (savedInstanceState == null) {
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction transaction = fm.beginTransaction();

            transaction.add(R.id.container_1, new MainListFragment(), TAG1);
            if (twoPane) {
                transaction.add(R.id.container_2, new MainDetailFragment(), TAG2);
            }

            transaction.commit();
        }
    }

    @Override
    public void onItemSelected(String id) {
        Toast.makeText(this, "MailActivity.onItemSelected: " + id, Toast.LENGTH_SHORT).show();

        final MainDetailFragment fragment = (MainDetailFragment) getSupportFragmentManager().findFragmentByTag(TAG2);
        fragment.setText("set from activity: " + id);
    }

    @Override
    public void onAccountSelected(String id) {
        final FragmentManager fm = getSupportFragmentManager();
        final MainListFragment fragment = (MainListFragment) fm.findFragmentByTag(TAG1);
        fragment.setAccount(id);
    }
}
