package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

/**
 * Created by thevery on 20/09/15.
 */
public class StatefulListFragment extends ListFragment {
    public static final String HIDDEN = "hidden";

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.isHidden()) {
            bundle.putBoolean(HIDDEN, true);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            if (bundle.getBoolean(HIDDEN, false)) {
                getFragmentManager()
                        .beginTransaction()
                        .hide(this)
                        .commit();
            }
        }
    }
}
