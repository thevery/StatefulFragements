package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by thevery on 18/09/15.
 */
public class MailActivity extends AppCompatActivity
        implements MainListFragment.Callbacks, MainAccountFragment.Callbacks {
    public static final String TAG_MASTER = "TAG1";
    public static final String TAG_DETAIL = "TAG2";

//    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
//        twoPane = findViewById(R.id.container_2) != null;
        if (savedInstanceState == null) {
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction transaction = fm.beginTransaction();

            transaction.add(R.id.container_1, new MainListFragment(), TAG_MASTER);

            final MainDetailFragment mainDetailFragment = new MainDetailFragment();
            transaction.add(R.id.container_2, mainDetailFragment, TAG_DETAIL);
            transaction.hide(mainDetailFragment);

            transaction.commit();
        }
    }

    @Override
    public void onItemSelected(String id) {
        final FragmentManager fm = getSupportFragmentManager();
        MainListFragment masterFragment = getMasterFragment(fm);
        MainDetailFragment detailFragment = getDetailFragment(fm);

        if (detailFragment.isHidden()) {
            fm.beginTransaction()
                    .show(detailFragment)
                    .hide(masterFragment)
                    .commit();
        }

        detailFragment.setText("set from activity: " + id);
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fm = getSupportFragmentManager();
        MainListFragment masterFragment = getMasterFragment(fm);
        MainDetailFragment detailFragment = getDetailFragment(fm);

        if (masterFragment.isHidden()) {
            fm.beginTransaction()
                    .show(masterFragment)
                    .hide(detailFragment)
                    .commit();
        } else {
            super.onBackPressed();
        }
    }

    private MainDetailFragment getDetailFragment(FragmentManager fm) {
        return (MainDetailFragment) fm.findFragmentByTag(TAG_DETAIL);
    }

    private MainListFragment getMasterFragment(FragmentManager fm) {
        return (MainListFragment) fm.findFragmentByTag(TAG_MASTER);
    }

    @Override
    public void onAccountSelected(String id) {
        final FragmentManager fm = getSupportFragmentManager();
        final MainListFragment fragment = getMasterFragment(fm);
        fragment.setAccount(id);
    }
}
