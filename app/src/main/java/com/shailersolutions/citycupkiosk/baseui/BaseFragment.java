package com.shailersolutions.citycupkiosk.baseui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public Fragment getCurrentFragment() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getCurrentFragment();
        }
        return null;
    }

    public void startActivity(Class<?> destinationActivity) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).switchActivity(destinationActivity);
        }
    }
    public void startActivity(Class<?> destinationActivity, Bundle bundle) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).switchActivity(destinationActivity, bundle);
        }
    }
}
