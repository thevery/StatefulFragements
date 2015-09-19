package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

            final MainDetailFragment mainDetailFragment = new MainDetailFragment();
            transaction.add(R.id.container_2, mainDetailFragment, TAG2);
            transaction.hide(mainDetailFragment);

            transaction.commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        System.out.println("MailActivity.onAttachFragment: " + fragment);
        super.onAttachFragment(fragment);
    }

    @Override
    public void onItemSelected(String id) {
        final FragmentManager fm = getSupportFragmentManager();
        MainListFragment fragment1 = getFragment1(fm);
        MainDetailFragment fragment2 = getFragment2(fm);
        fm.beginTransaction()
                .show(fragment2)
                .hide(fragment1)
                .commit();
        fragment2.setText("set from activity: " + id);
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fm = getSupportFragmentManager();
        MainListFragment fragment1 = getFragment1(fm);
        MainDetailFragment fragment2 = getFragment2(fm);
        if (fragment1.isHidden()) {
            fm.beginTransaction()
                    .show(fragment1)
                    .hide(fragment2)
                    .commit();
        } else {
            super.onBackPressed();
        }
    }

    private MainDetailFragment getFragment2(FragmentManager fm) {
        return (MainDetailFragment) fm.findFragmentByTag(TAG2);
    }

    private MainListFragment getFragment1(FragmentManager fm) {
        return (MainListFragment) fm.findFragmentByTag(TAG1);
    }

    @Override
    public void onAccountSelected(String id) {
        final FragmentManager fm = getSupportFragmentManager();
        final MainListFragment fragment = getFragment1(fm);
        fragment.setAccount(id);
    }
}
