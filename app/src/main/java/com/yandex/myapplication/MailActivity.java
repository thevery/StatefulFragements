package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * Created by thevery on 18/09/15.
 */
public class MailActivity extends AppCompatActivity
        implements MainListFragment.Callbacks, MainAccountFragment.Callbacks {
    public static final String TAG_MASTER = "TAG1";
    public static final String TAG_DETAIL = "TAG2";

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2); // switch to main for 1 pane only
        twoPane = findViewById(R.id.container_1).getParent() instanceof LinearLayout;
        if (savedInstanceState == null) {
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction transaction = fm.beginTransaction();

            final MainListFragment masterFragment = new MainListFragment();
            transaction.add(R.id.container_1, masterFragment, TAG_MASTER);

            final MainDetailFragment detailFragment = new MainDetailFragment();
            transaction.add(R.id.container_2, detailFragment, TAG_DETAIL);
            transaction.hide(detailFragment);

            transaction.commit();
        } else {
            final FragmentManager fm = getSupportFragmentManager();
            MainListFragment masterFragment = getMasterFragment(fm);
            MainDetailFragment detailFragment = getDetailFragment(fm);
            final FragmentTransaction transaction = fm.beginTransaction();
            ensureVisibility(transaction, masterFragment, detailFragment);
            transaction.commit();
        }
    }

    @Override
    public void onItemSelected(String id) {
        final FragmentManager fm = getSupportFragmentManager();
        MainListFragment masterFragment = getMasterFragment(fm);
        MainDetailFragment detailFragment = getDetailFragment(fm);

        if (detailFragment.isReallyHidden()) {
            final FragmentTransaction transaction = fm.beginTransaction();
            transaction.show(detailFragment);
            detailFragment.setIsReallyHidden(false);
            ensureVisibility(transaction, masterFragment, detailFragment);
            transaction.commit();
        }

        detailFragment.setText("set from activity: " + id);
    }

    private void ensureVisibility(FragmentTransaction transaction, MainListFragment masterFragment, MainDetailFragment detailFragment) {
        final boolean detailVisible = !detailFragment.isReallyHidden();
        if (twoPane) {
            transaction.show(masterFragment); // always visible in two pane mode
        } else if (detailVisible) {
            transaction.hide(masterFragment); // hide if detail is visible in single pane mode
        }
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
