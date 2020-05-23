package id.go.sukabumikota.clink.pager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import id.go.sukabumikota.clink.R;
import id.go.sukabumikota.clink.ui.recyclerzone.fragment.RcZoneAnorganikFragment;
import id.go.sukabumikota.clink.ui.recyclerzone.fragment.RcZoneOrganikFragment;

public class PagerAdapterRcZone extends FragmentPagerAdapter {

    private final Context mContext;

    public PagerAdapterRcZone(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[] {
            R.string.rczone_organik,
            R.string.rczone_anorganik
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RcZoneOrganikFragment();
                break;
            case 1:
                fragment = new RcZoneAnorganikFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
