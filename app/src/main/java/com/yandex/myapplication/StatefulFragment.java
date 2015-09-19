package com.yandex.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by thevery on 20/09/15.
 */
public class StatefulFragment extends Fragment {
    public static final String HIDDEN = "hidden";
    private boolean explicitHidden = false;

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
    }

    public boolean isExplicitHidden() {
        return explicitHidden;
    }

    public void setExplicitHidden(FragmentTransaction transaction, boolean explicitHidden) {
        this.explicitHidden = explicitHidden;
        if (explicitHidden) {
            transaction.hide(this);
        } else {
            transaction.show(this);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            final FragmentTransaction transaction = getFragmentManager().beginTransaction();
            setExplicitHidden(transaction, bundle.getBoolean(HIDDEN, false));
            transaction.commit();
        }
    }
}
