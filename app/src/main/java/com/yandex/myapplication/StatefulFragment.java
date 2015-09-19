package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by thevery on 20/09/15.
 */
public class StatefulFragment extends Fragment {
    public static final String HIDDEN = "hidden";
    private boolean isReallyHidden = false;

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.isHidden()) {
            bundle.putBoolean(HIDDEN, true);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isReallyHidden = hidden;
    }

    public boolean isReallyHidden() {
        return isReallyHidden;
    }

    public void setIsReallyHidden(boolean isReallyHidden) {
        this.isReallyHidden = isReallyHidden;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            isReallyHidden = bundle.getBoolean(HIDDEN, false);
            if (isReallyHidden) {
                getFragmentManager()
                        .beginTransaction()
                        .hide(this)
                        .commit();
            }
        }
    }
}
